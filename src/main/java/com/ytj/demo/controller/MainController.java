package com.ytj.demo.controller;

import com.ytj.demo.entity.User;
import com.ytj.demo.mapper.UserMapper;
import com.ytj.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/demo")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

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

    @GetMapping(path = "/addThroughMybatis")
    @ResponseBody
    public String addThroughMybatis(@RequestParam String name, @RequestParam String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userMapper.insert(user);
        return String.format("%s's information has been saved through Mybatis.", name);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAll() {
        return userRepository.findAll();
    }
}
