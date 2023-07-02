package dev.angelcruzl.authsystem.controller;

import dev.angelcruzl.authsystem.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
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
}
