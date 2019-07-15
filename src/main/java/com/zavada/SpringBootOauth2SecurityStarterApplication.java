package com.zavada;

import com.zavada.entity.RoleEntity;
import com.zavada.entity.UserEntity;
import com.zavada.repository.RoleRepository;
import com.zavada.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class SpringBootOauth2SecurityStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOauth2SecurityStarterApplication.class, args);
    }

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            saveRole();
            saveUser();
        };
    }

    private void saveRole() {
        if (roleRepository.count() == 0) {
            Arrays.asList("ADMIN", "USER").stream().forEach(r -> {
                RoleEntity role = new RoleEntity(r);
                roleRepository.save(role);
            });
        }
    }

    private void saveUser() {
        if (userRepository.count() == 0) {
            RoleEntity roleEntityAdmin = roleRepository.findByNameIgnoreCase("ADMIN").get();

            UserEntity userEntity = new UserEntity();
            userEntity.setUsername("admin");
            userEntity.setPassword(passwordEncoder.encode("123"));
            userEntity.setRoles(new HashSet<RoleEntity>(Arrays.asList(roleEntityAdmin)));
            userRepository.save(userEntity);


            RoleEntity roleEntityUser = roleRepository.findByNameIgnoreCase("USER").get();
            userEntity = new UserEntity();
            userEntity.setUsername("zavada");
            userEntity.setPassword(passwordEncoder.encode("zavada"));
            userEntity.setRoles(new HashSet<RoleEntity>(Arrays.asList(roleEntityUser)));

            userRepository.save(userEntity);
        }
    }
}
