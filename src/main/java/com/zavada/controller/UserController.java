package com.zavada.controller;

import com.zavada.entity.UserEntity;
import com.zavada.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> findByUsername() {
        return userService.findAll();
    }

    @GetMapping("{username}")
    public UserEntity findByUsername(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }


}
