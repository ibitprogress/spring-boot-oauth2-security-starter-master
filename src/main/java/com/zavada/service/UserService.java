package com.zavada.service;

import com.zavada.entity.UserEntity;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {

    UserEntity findByUsername(String username);

    void save(UserEntity user);

    List<UserEntity> findAll();

    UserEntity findById(Long id);

}
