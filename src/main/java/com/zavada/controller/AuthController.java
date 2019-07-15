package com.zavada.controller;

import com.zavada.entity.UserEntity;
import com.zavada.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("signup")
    public void save(@RequestBody UserEntity userEntity) {
        System.out.println(userEntity.getUsername() + " " + userEntity.getPassword());
        userService.save(userEntity);
    }

}
