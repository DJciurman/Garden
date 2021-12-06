package com.pk.projekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.ConnectException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class AppController {

  @Autowired
  private UserRepository repo;

  @GetMapping("")
  public String viewHomePage(Model model) {
    model.addAttribute("user", new User());
    return "LogIn";
  }

  @PostMapping("/register")
  public String processRegistration(User user, Model model) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String encodedPassword = encoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
    try {
      repo.save(user);
    } catch (Exception e) {

    }
    model.addAttribute("user", new User());

    return "LogIn";
  }

  @PostMapping("/login")
  public String processLogin(User user, Model model) {
    User userDatabse = repo.findByEmail(user.getEmail());
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String encodedPassword = encoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
    if (userDatabse.getPassword().equals(user.getPassword())) {

    }
    else {

    }

    return "index";
  }

  @GetMapping("/list_users")
  public String viewUsersList(Model model) {
    List<User> listUsers = repo.findAll();
    model.addAttribute("listUsers", listUsers);

    return "users";
  }

}
