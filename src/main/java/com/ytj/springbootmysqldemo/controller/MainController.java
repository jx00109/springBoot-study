package com.ytj.springbootmysqldemo.controller;

import com.ytj.springbootmysqldemo.entity.User;
import com.ytj.springbootmysqldemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/demo")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping(path = "/greet", method = RequestMethod.GET)
    public String greeting() {
        return "hello, springBoot.";
    }

    @GetMapping(path = "/add")
    public @ResponseBody
    String add(@RequestParam String name, @RequestParam String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
        return String.format("%s's information has been saved", name);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAll() {
        return userRepository.findAll();
    }
}
