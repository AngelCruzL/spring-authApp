package dev.angelcruzl.authsystem.controller;

import dev.angelcruzl.authsystem.dto.UserDto;
import dev.angelcruzl.authsystem.entity.User;
import dev.angelcruzl.authsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
  public String handleRegisterForm(
      @Valid @ModelAttribute("user") UserDto userDto,
      BindingResult result,
      Model model
  ) {
    User existingUser = userService.findUserByEmail(userDto.getEmail());
    if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
      result.rejectValue(
          "email",
          null,
          "There is already an account registered with that email"
      );
    }

    if (result.hasErrors()) {
      model.addAttribute("user", userDto);
      return "/register";
    }

    userService.saveUser(userDto);

    return "redirect:/register?success";
  }
}
