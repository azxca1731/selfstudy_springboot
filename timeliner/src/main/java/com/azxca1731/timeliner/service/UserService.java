package com.azxca1731.timeliner.service;

import com.azxca1731.timeliner.domain.Role;
import com.azxca1731.timeliner.domain.User;
import com.azxca1731.timeliner.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveUser(User user, String[] roles) {
        Set<Role> rolesSet = new HashSet<>();

        for(String role:roles) {
            rolesSet.add(new Role(role));
        }


        userRepository.save(  User.builder()
                .email(user.getEmail())
                .password(bCryptPasswordEncoder.encode(user.getPassword()))
                .roles(rolesSet)
                .build());
    }

    public User findByUsername(String email) {
        return userRepository.findByEmail(email);
    }
}
