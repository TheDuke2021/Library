package com.example.Library.controllers;

import com.example.Library.data.UserRepository;
import com.example.Library.entities.Book;
import com.example.Library.entities.BorrowedBook;
import com.example.Library.entities.FavoriteBook;
import com.example.Library.entities.User;
import com.example.Library.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    UserRepository userRepository;

    @Autowired
    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String showProfile(Model model) {
        model.addAttribute("imgUtil", new ImageUtil());
        return "profile";
    }

    @ModelAttribute
    public void loadUserInfo(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName());
        List<BorrowedBook> borrowedBookList = user.getBorrowedBooks();
        List<FavoriteBook> favoriteBookList = user.getFavoriteBooks();


        model.addAttribute("user", user);
        model.addAttribute("borrowedBooks", borrowedBookList);
        model.addAttribute("favoriteBooks", favoriteBookList);
    }
}
