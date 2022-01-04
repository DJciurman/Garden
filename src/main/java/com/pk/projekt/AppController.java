package com.pk.projekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericApplicationContextExtensionsKt;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AppController {

  @Autowired private UserRepository userRepo;

  @Autowired private GardenRepository gardenRepo;

  @GetMapping("")
  public String viewHomePage(Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      String email = SecurityContextHolder.getContext().getAuthentication().getName();
      User user = userRepo.findByEmail(email);
      model.addAttribute("user", user);
      return "ManageAccount";
    }
    model.addAttribute("user", new User());
    return "login";
  }

  @GetMapping("/register")
  public String processRegistration(Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      String email = SecurityContextHolder.getContext().getAuthentication().getName();
      User user = userRepo.findByEmail(email);
      model.addAttribute("user", user);
      return "ManageAccount";
    }
    return "login";
  }

  @PostMapping("/register")
  public String processRegistration(User user, Model model) {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String encodedPassword = encoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
    try {
      userRepo.save(user);
    } catch (Exception e) {

    }
    model.addAttribute("user", new User());

    return "login";
  }

  @GetMapping("/login")
  public String processLogin(Model model) {
    model.addAttribute("user", new User());

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      String email = SecurityContextHolder.getContext().getAuthentication().getName();
      User user = userRepo.findByEmail(email);
      model.addAttribute("user", user);
      return "ManageAccount";
    }

    return "login";
  }

  @GetMapping("/index")
  public String viewUserPage(Model model) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userRepo.findByEmail(email);
    model.addAttribute("user", user);
    return "ManageAccount";
  }

  @RequestMapping("/plant")
  public String viewMyPlantsPage() {
    return "MyPlants";
  }

  @RequestMapping("/task")
  public String viewTasksPage() {
    return "Tasks";
  }

  @RequestMapping("/employee")
  public String viewWorkersPage() {
    return "Workers";
  }

  @RequestMapping("/garden")
  public String viewMyGardensPage(Model model) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userRepo.findByEmail(email);
    List<Garden> listGarden = gardenRepo.findByUserId(user);
    model.addAttribute("listGarden", listGarden);
    return "MyGardens";
  }

  @RequestMapping("/addNote")
  public String viewAddNotePage(Model model) {
    return "AddNote";
  }

  @GetMapping("/addGarden")
  public String viewAddGardenPage(Model model) {
    model.addAttribute("garden", new Garden());
    return "AddGarden";
  }

  @PostMapping("/addGarden")
  public String processAddGarden (Garden garden, Model model) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userRepo.findByEmail(email);
    garden.setUser(user);
    garden.setNote(null);
    try {
      gardenRepo.save(garden);
    } catch (Exception e) {

    }
    model.addAttribute("garden", new Garden());
    return "AddGarden";
  }

  @RequestMapping("/account")
  public String viewManageAccountPage(Model model) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userRepo.findByEmail(email);
    model.addAttribute("user", user);
    return "ManageAccount";
  }
}
