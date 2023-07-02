package dev.angelcruzl.authsystem.service;

import dev.angelcruzl.authsystem.dto.UserDto;

public interface UserService {
  void saveUser(UserDto userDto);
}
