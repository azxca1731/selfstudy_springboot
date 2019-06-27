package com.azxca1731.timeliner.runner;

import com.azxca1731.timeliner.domain.User;
import com.azxca1731.timeliner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class AdminMakeRunner implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User admin = User.builder().email("azxca1731@naver.com")
                .password("1234")
                .build();

        String[] roles = new String[1];
        roles[0] = "User";

        userService.saveUser(admin,roles);
    }
}
