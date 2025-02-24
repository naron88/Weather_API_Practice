package com.practice.apipractice.dto;

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

