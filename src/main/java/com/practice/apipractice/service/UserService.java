package com.practice.apipractice.service;

import com.practice.apipractice.domain.User;
import com.practice.apipractice.dto.UserDto;
import com.practice.apipractice.dto.UserRequest;
import com.practice.apipractice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserDto createUser(UserRequest request) {
    String name = request.name();
    String email = request.email();
    User user = User.builder()
        .name(name)
        .email(email)
        .build();
    return toDto(userRepository.save(user));
  }

  public UserDto getUser(Long userId) {
    return toDto(userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")));
  }

  private UserDto toDto(User user) {
    return new UserDto(user.getId(), user.getName(), user.getName());
  }
}

