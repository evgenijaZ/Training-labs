package edu.training.loginform.controller;

import edu.training.loginform.model.User;
import edu.training.loginform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("users")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    String getAll(Model model) {
        List<User> users = service.getAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/{id}")
    User one(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("/new")
    String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping
    String save(@ModelAttribute User user, Model model) {
        service.save(user);
        return getAll(model);
    }
}
