package com.pk.projekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AppController {

  @Autowired private UserRepository userRepo;

  @Autowired private GardenRepository gardenRepo;

  @Autowired private TaskRepository taskRepo;

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
  public String viewTasksPage(Model model) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userRepo.findByEmail(email);
    List<Task> myTasks = taskRepo.findByUserIdGardenASC(user);
    model.addAttribute("myTasks", myTasks);
    List<Task> tasks = taskRepo.findAll(Sort.by(Sort.Direction.ASC, "garden"));
    for (int i = 0; i < tasks.size(); i++)
    {
      if (tasks.get(i).getUser().equals(user))
      {
        tasks.remove(i);
        i--;
      }
    }
    model.addAttribute("tasksInMyGardens", tasks);
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
  public String processAddGarden(Garden garden, Model model) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userRepo.findByEmail(email);
    garden.setUser(user);
    try {
      gardenRepo.save(garden);
    } catch (Exception e) {

    }
    model.addAttribute("garden", new Garden());
    return "AddGarden";
  }

  @GetMapping("/addPlant")
  public String viewAddPlantPage(Model model) {
    model.addAttribute("plant", new Plant());
    return "AddPlant";
  }

  @GetMapping("/addTask")
  public String viewAddTaskPage(Model model) {
    model.addAttribute("task", new Task());
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userRepo.findByEmail(email);
    List<Garden> gardens = gardenRepo.findByUserId(user);
    model.addAttribute("listGarden", gardens);
    List<User> users = userRepo.findAll(Sort.by(Sort.Direction.ASC, "email"));
    model.addAttribute("listUser", users);
    return "AddTask";
  }

  @PostMapping("/addTask")
  public String processAddTask(
      @RequestParam("task") String description,
      @RequestParam("garden") Long gardenID,
      @RequestParam("worker") Long userID,
      Model model) {
    Task task = new Task();
    Garden gardenTask = gardenRepo.findByGardenId(gardenID);
    User userTask = userRepo.findByUserId(userID);
    task.setGarden(gardenTask);
    task.setUser(userTask);
    task.setDescription(description);
    try {
      taskRepo.save(task);
    } catch (Exception e) {

    }
//    model.addAttribute("task", new Task());
//    String email = SecurityContextHolder.getContext().getAuthentication().getName();
//    User user = userRepo.findByEmail(email);
//    List<Garden> gardens = gardenRepo.findByUserId(user);
//    model.addAttribute("listGarden", gardens);
//    List<User> users = userRepo.findAll();
//    model.addAttribute("listUser", users);
    return "AddTask";
  }

  @RequestMapping("/account")
  public String viewManageAccountPage(Model model) {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userRepo.findByEmail(email);
    model.addAttribute("user", user);
    return "ManageAccount";
  }
}
