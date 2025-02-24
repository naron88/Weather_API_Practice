# 날씨 API 서비스

## 개요
Spring Boot 기반의 날씨 서비스로, `WebClient`를 사용하여 서울 열린데이터광장의 대기질 정보를 가져오고 XML 데이터를 JSON 형식으로 변환하여 제공합니다.

## 기능
- 서울시 전체 대기질 정보를 조회
- 특정 지역(자치구)의 대기질 정보 조회
- XML 응답을 `WeatherDto`를 통해 JSON으로 변환

## 사용 기술
- **Spring Boot** (Spring WebFlux `WebClient` 사용)
- **Lombok** (`@RequiredArgsConstructor`, `@Value` 설정값 주입)
- **Jackson** (JSON 변환)
- **H2 Database** (테스트용 DB)
- **XML 파싱** (`DocumentBuilderFactory` 활용)
## WeatherDto
```java
public record WeatherDto(
    String district,         // 측정소명
    int maxIndex,            // 통합대기환경지수
    String airQualityGrade,  // 통합대기환경지수 등급
    String pollutant,        // 주 오염물질
    double nitrogen,         // 이산화질소 (ppm)
    double ozone,            // 오존 (ppm)
    double carbon,           // 일산화탄소 (ppm)
    double sulfurous,        // 아황산가스 (ppm)
    int pm10,                // 미세먼지 (㎍/㎥)
    int pm25                 // 초미세먼지 (㎍/㎥)
) {}
```

## API
### 1. 서울시 전체 대기질 정보 조회
**요청:**
```
GET /api/weather
```
**응답 예시:**
```json
[
  {
    "district": "종로구",
    "maxIndex": 66,
    "airQualityGrade": "보통",
    "pollutant": "PM-2.5",
    "nitrogen": 0.010,
    "ozone": 0.043,
    "carbon": 0.4,
    "sulfurous": 0.003,
    "pm10": 37,
    "pm25": 24
  }
]
```

### 2. 특정 지역(자치구)의 대기질 정보 조회
**요청:**
```
GET /api/weather/{districtCode}
```
**응답 예시:**
```json
{
  "district": "중구",
  "maxIndex": 60,
  "airQualityGrade": "보통",
  "pollutant": "O3",
  "nitrogen": 0.008,
  "ozone": 0.043,
  "carbon": 0.4,
  "sulfurous": 0.003,
  "pm10": 30,
  "pm25": 22
}
```

## Postman test
[Uploading weather practice.postman_collection.json…]()

## Swagger test
[Uploading weather=practice=api-docs.json…]()

