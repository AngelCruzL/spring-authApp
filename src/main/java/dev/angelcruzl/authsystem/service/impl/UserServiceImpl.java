package dev.angelcruzl.authsystem.service.impl;

import dev.angelcruzl.authsystem.dto.UserDto;
import dev.angelcruzl.authsystem.entity.Role;
import dev.angelcruzl.authsystem.entity.User;
import dev.angelcruzl.authsystem.repository.RoleRepository;
import dev.angelcruzl.authsystem.repository.UserRepository;
import dev.angelcruzl.authsystem.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public UserServiceImpl(
      UserRepository userRepository,
      RoleRepository roleRepository
  ) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  @Override
  public void saveUser(UserDto userDto) {
    User user = new User();
    user.setName(userDto.getFirstName() + " " + userDto.getLastName());
    user.setEmail(userDto.getEmail());
    // TODO: Hash password
    user.setPassword(userDto.getPassword());

    Role role = roleRepository.findByName("ROLE_ADMIN");
    if (role == null) {
      role = checkIfRoleExists();
    }

    user.setRoles(List.of(role));
    userRepository.save(user);
  }

  @Override
  public User findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public List<UserDto> findAllUsers() {
    List<User> users = userRepository.findAll();

    return users.stream()
        .map(this::mapToUserDto)
        .collect(Collectors.toList());
  }

  private UserDto mapToUserDto(User user) {
    UserDto userDto = new UserDto();
    String[] str = user.getName().split(" ");
    userDto.setFirstName(str[0]);
    userDto.setLastName(str[1]);
    userDto.setEmail(user.getEmail());

    return userDto;
  }

  private Role checkIfRoleExists() {
    Role role = new Role();
    role.setName("ROLE_ADMIN");

    return roleRepository.save(role);
  }
}
