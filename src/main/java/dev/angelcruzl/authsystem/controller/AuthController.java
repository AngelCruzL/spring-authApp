package dev.angelcruzl.authsystem.controller;

import dev.angelcruzl.authsystem.dto.UserDto;
import dev.angelcruzl.authsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/register")
  public String showRegistrationForm(Model model) {
    UserDto user = new UserDto();
    model.addAttribute("user", user);

    return "register";
  }

  @PostMapping("/register/save")
  public String handleRegisterForm(@ModelAttribute("user") UserDto userDto) {
    userService.saveUser(userDto);

    return "redirect:/register?success";
  }
}
