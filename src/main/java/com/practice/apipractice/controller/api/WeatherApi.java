package com.practice.apipractice.controller.api;

import com.practice.apipractice.dto.WeatherDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "Weather", description = "Weather API")
public interface WeatherApi {

  @Operation(summary = "서울 날씨 조회")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200", description = "서울 전체 날씨 조회 성공!",
          content = @Content(schema = @Schema(implementation = WeatherDto.class))
      )
  })
  ResponseEntity<List<WeatherDto>> getAllWeather();

  @Operation(summary = "특정 도시 날씨 조회")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200", description = "특정 도시 날씨 조회 성공!",
          content = @Content(schema = @Schema(implementation = WeatherDto.class))
      ),
      @ApiResponse(
          responseCode = "404", description = "조회 실패",
          content = @Content(examples = @ExampleObject(value = "City not found: {districtCode}"))
      )
  })
  ResponseEntity<WeatherDto> getWeather(
      @Parameter(description = "도시 코드") String districtCode
  );
}
