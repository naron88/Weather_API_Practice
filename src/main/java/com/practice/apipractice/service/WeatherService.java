package com.practice.apipractice.service;

import com.practice.apipractice.dto.WeatherDto;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Service
@RequiredArgsConstructor
public class WeatherService {
  private final WebClient.Builder webClientBuilder;

  @Value("${weather.api.url}")
  private String apiUrl;

  @Value("${weather.api.key}")
  private String apiKey;

  public List<WeatherDto> getAllWeather() {
    String url = String.format("%s/%s/xml/ListAirQualityByDistrictService/1/25/", apiUrl, apiKey);
    String xmlResponse = webClientBuilder.build()
        .get()
        .uri(url)
        .retrieve()
        .bodyToMono(String.class)
        .block();
    return parseXmlToDtoList(xmlResponse);
  }

  public WeatherDto getWeather(String districtCode) {
    String url = String.format("%s/%s/xml/ListAirQualityByDistrictService/1/5/%s/", apiUrl, apiKey, districtCode);

    String xmlResponse = webClientBuilder.build()
        .get()
        .uri(url)
        .retrieve()
        .bodyToMono(String.class)
        .block();
    return parseXmlToDtoList(xmlResponse).get(0);
  }

  private List<WeatherDto> parseXmlToDtoList(String xmlResponse) {
    List<WeatherDto> weatherList = new ArrayList<>();

    try {
      if (xmlResponse == null || !xmlResponse.trim().startsWith("<?xml")) {
        System.out.println("Invalid XML response received.");
        return weatherList;
      }

      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(new org.xml.sax.InputSource(new java.io.StringReader(xmlResponse)));

      NodeList rowNodes = document.getElementsByTagName("row");

      for (int i = 0; i < rowNodes.getLength(); i++) {
        Element row = (Element) rowNodes.item(i);
        weatherList.add(new WeatherDto(
            getTagValue("MSRSTENAME", row),
            Integer.parseInt(getTagValue("MAXINDEX", row)),
            getTagValue("GRADE", row),
            getTagValue("POLLUTANT", row),
            Double.parseDouble(getTagValue("NITROGEN", row)),
            Double.parseDouble(getTagValue("OZONE", row)),
            Double.parseDouble(getTagValue("CARBON", row)),
            Double.parseDouble(getTagValue("SULFUROUS", row)),
            Integer.parseInt(getTagValue("PM10", row)),
            Integer.parseInt(getTagValue("PM25", row))
        ));
      }
    } catch (Exception e) {
      System.out.println("XML Parsing Error: " + e.getMessage());
    }
    return weatherList;
  }

  private String getTagValue(String tag, Element element) {
    NodeList nodeList = element.getElementsByTagName(tag);
    return nodeList.getLength() > 0 ? nodeList.item(0).getTextContent() : "0"; // 기본값 제공
  }

  private WeatherDto getDefaultWeatherDto() {
    return new WeatherDto("N/A", 0, "N/A", "N/A", 0.0, 0.0, 0.0, 0.0, 0, 0);
  }
}
