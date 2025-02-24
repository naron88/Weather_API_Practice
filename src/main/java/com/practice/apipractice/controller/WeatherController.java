package com.practice.apipractice.controller;

import com.practice.apipractice.dto.WeatherDto;
import com.practice.apipractice.service.WeatherService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weather")
public class WeatherController {
  private final WeatherService weatherService;

  @GetMapping
  public ResponseEntity<List<WeatherDto>> getAllWeather() {
    return ResponseEntity.ok(weatherService.getAllWeather());
  }

  @GetMapping("/{districtCode}")
  public ResponseEntity<WeatherDto> getWeather(@PathVariable String districtCode) {
    return ResponseEntity.ok(weatherService.getWeather(districtCode));
  }
}
