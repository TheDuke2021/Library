package com.example.Library.controllers;


import com.example.Library.data.BookRepository;
import com.example.Library.entities.Book;
import com.example.Library.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class BookController {

    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping("/books/{id}")
    public String showBooks(@PathVariable(value = "id")Integer id, Model model) {
        Iterable<Book> foundBooks = bookRepository.findAll();
        Iterator<Book> iterator = foundBooks.iterator();
        List<Book> books = new ArrayList<>();
        for (int i =  1; i < id; i++) {
            for(int j = 0; j < 9; j++) {
                iterator.next();
            }
        }

        for(int j = 0; j < 9; j++) {
            if(iterator.hasNext())
                books.add(iterator.next());
        }


        model.addAttribute("books", books);
        model.addAttribute("bookcount", bookRepository.count());
        model.addAttribute("pageid", id);
        model.addAttribute("imgUtil", new ImageUtil());
        return "books";
    }



    public byte[] imageToByteArray(String path) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (InputStream in = BookController.class.getResourceAsStream(path)) {
            int length;
            byte[] buffer = new byte[1024];
            while ((length = in.read(buffer)) != -1)
                out.write(buffer, 0, length);
        } catch(IOException e) {
        }

        return out.toByteArray();
    }


    @GetMapping("/book/{id}")
    public String showBook(@PathVariable(value = "id")Integer id, Model model) {
        Book book = bookRepository.findById(id);

        model.addAttribute("book", book);
        model.addAttribute("imgUtil", new ImageUtil());

        return "book";
    }
}
