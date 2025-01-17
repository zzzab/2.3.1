package MyApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import MyApp.models.User;
import MyApp.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/create")
    public String createUsersForm(User user) {
        return "create";
    }

    @PostMapping("/create")
    public String saveUser(User user) {
        userService.saveUser(user);
        return "redirect:/";
    }
    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id){
        userService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String edit(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "/update";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam("id") int id) {
        userService.updateUser(id, user);
        return "redirect:/";
    }
}