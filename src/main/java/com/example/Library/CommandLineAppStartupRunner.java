package com.example.Library;

import com.example.Library.data.UserRepository;
import com.example.Library.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CommandLineAppStartupRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String...args) throws Exception {
        if(userRepository.findByUsername("admin") == null)
            userRepository.save(new User("1",
                    "1",
                    "admin", passwordEncoder.encode("admin"),
                    "1",
                    "1",
                    "1",
                    new Date(),
                    null,
                    User.Role.ADMIN));
    }
}