package com.pk.projekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    return "login";
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

    return "login";
  }

  @GetMapping("/login")
  public String processLogin(Model model) {
    model.addAttribute("user", new User());
//    User userDatabse = repo.findByEmail(user.getEmail());
//    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//    String encodedPassword = encoder.encode(user.getPassword());
//    user.setPassword(encodedPassword);
//    if (userDatabse.getPassword().equals(user.getPassword())) {
//
//    }
//    else {
//
//    }
//
//    return "index";
    return "login";
  }

  @GetMapping("/index")
  public String viewUserPage()
  {
    return "index";
  }

  @RequestMapping("/plant")
  public String viewPlantsPage()
  {
    return "Rośliny";
  }

  @RequestMapping("/task")
  public String viewTasksPage()
  {
    return "Zadania";
  }

  @RequestMapping("/employee")
  public String viewEmployeesPage()
  {
    return "Pracownicy";
  }

  @RequestMapping("/garden")
  public String viewGardensPage()
  {
    return "Moje-Ogrody";
  }

  @GetMapping("/list_users")
  public String viewUsersList(Model model) {
    List<User> listUsers = repo.findAll();
    model.addAttribute("listUsers", listUsers);

    return "users";
  }

}
