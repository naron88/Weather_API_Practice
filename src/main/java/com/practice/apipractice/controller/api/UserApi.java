package com.practice.apipractice.controller.api;

import com.practice.apipractice.dto.UserDto;
import com.practice.apipractice.dto.UserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "User", description = "User API")
public interface UserApi {

  @Operation(summary = "User 등록")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "201", description = "User 생성 성공!",
          content = @Content(schema = @Schema(implementation = UserDto.class))
      )
  })
  ResponseEntity<UserDto> createUser(
      @Parameter(description = "User 생성 정보")UserRequest request
  );

  @Operation(summary = "User 조회")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200", description = "User 조회 성공!",
          content = @Content(schema = @Schema(implementation = UserDto.class))
      ),
      @ApiResponse(
          responseCode = "404", description = "User를 찾을 수 없음",
          content = @Content(examples = @ExampleObject(value = "User not found: {userId}"))
      )
  })
  ResponseEntity<UserDto> getUser(
    @Parameter(description = "UserId") Long userId
  );
}
