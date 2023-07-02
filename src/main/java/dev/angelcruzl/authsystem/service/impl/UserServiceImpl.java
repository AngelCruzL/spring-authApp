package dev.angelcruzl.authsystem.service.impl;

import dev.angelcruzl.authsystem.dto.UserDto;
import dev.angelcruzl.authsystem.entity.Role;
import dev.angelcruzl.authsystem.entity.User;
import dev.angelcruzl.authsystem.repository.RoleRepository;
import dev.angelcruzl.authsystem.repository.UserRepository;
import dev.angelcruzl.authsystem.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

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

  private Role checkIfRoleExists() {
    Role role = new Role();
    role.setName("ROLE_ADMIN");

    return roleRepository.save(role);
  }
}
