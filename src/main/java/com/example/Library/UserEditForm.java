package com.example.Library;

import com.example.Library.entities.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UserEditForm {

    public String firstname;
    public String lastname;
    public String username;
    public String street;
    public String house;
    public String phonenumber;
    public String book;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date borrowdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date duedate;
    public String returned;
    public String blablabla;



}
