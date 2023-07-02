package dev.angelcruzl.authsystem.service;

import dev.angelcruzl.authsystem.dto.UserDto;
import dev.angelcruzl.authsystem.entity.User;

import java.util.List;

public interface UserService {
  void saveUser(UserDto userDto);

  User findUserByEmail(String email);

  List<UserDto> findAllUsers();
}
