package com.example.spring_hiber.controller;

import com.example.spring_hiber.model.User;
import com.example.spring_hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(@RequestParam(value = "id", required = false) Long id, Model model) {
        if(id != null) {
            model.addAttribute("user", userService.findById(id));
            return "show";
        }
        else {
            model.addAttribute("users", userService.findAll());
            return "index";
        }
    }

    @GetMapping("/add")
    public String addPage(@ModelAttribute("user") User user) {
        return "add";
    }

    @PostMapping
    public String add(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") Long id,
                           Model model) {
        model.addAttribute("user", userService.findById(id));
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/users?id=" + user.getId();
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
