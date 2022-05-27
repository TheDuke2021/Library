package com.example.Library;

import com.example.Library.entities.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@Data
public class RegistrationForm {

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String street;
    private String house;
    private String phonenumber;
    private Date registrationdate = new Date();


    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(firstname, lastname, username,
                passwordEncoder.encode(password), street, house,
                phonenumber, registrationdate, null,
                User.Role.USER);
    }

}
