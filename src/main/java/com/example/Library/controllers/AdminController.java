package com.example.Library.controllers;


import com.example.Library.BookAddForm;
import com.example.Library.RegistrationForm;
import com.example.Library.data.BookRepository;
import com.example.Library.data.BorrowedBookRepository;
import com.example.Library.data.UserRepository;
import com.example.Library.entities.Book;
import com.example.Library.entities.BorrowedBook;
import com.example.Library.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
public class AdminController {

    UserRepository userRepository;
    BookRepository bookRepository;
    BorrowedBookRepository borrowedBookRepository;
    PasswordEncoder passwordEncoder;

    public AdminController(UserRepository userRepository, BookRepository bookRepository, BorrowedBookRepository borrowedBookRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.borrowedBookRepository = borrowedBookRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin")
    @ModelAttribute
    public String showAdminPanel(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("genres", Book.Genre.values());

        return "admin";
    }


    @GetMapping("/admin/user/edit")
    public String userLoad(@RequestParam("id")String id, Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());

        id = id.substring(id.indexOf("id: ") + 4, id.indexOf(","));
        User user = userRepository.findById(Integer.parseInt(id)).get();
        model.addAttribute("user", user);

        return "admin";
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(@ModelAttribute("newUser") User newUser, Model model) {
        User user = userRepository.findById(newUser.getId()).get();

        user.setFirstname(newUser.getFirstname());
        user.setLastname(newUser.getLastname());
        user.setUsername(newUser.getUsername());
        user.setStreet(newUser.getStreet());
        user.setHouse(newUser.getHouse());
        user.setPhonenumber(newUser.getPhonenumber());
        userRepository.save(user);
        List<BorrowedBook> newBorrowedBooks = newUser.getBorrowedBooks();
        Iterator<BorrowedBook> iterator = newBorrowedBooks.iterator();
        if(newBorrowedBooks != null) {
            Iterator<BorrowedBook> udalit = user.getBorrowedBooks().iterator();
            while(udalit.hasNext()){
                borrowedBookRepository.delete(udalit.next());
            }
        }


        while(iterator.hasNext()) {
            BorrowedBook current = iterator.next();
            BorrowedBook temp = new BorrowedBook();
            temp.setUserid(newUser.getId());

            String bookId = current.getBook().getName();
            bookId = bookId.substring(bookId.indexOf("id: ") + 4, bookId.indexOf(","));
            temp.setBook(bookRepository.findById(Integer.parseInt(bookId)));

            temp.setBorrowdate(current.getBorrowdate());
            temp.setDuedate(current.getDuedate());
            temp.setReturned(current.getReturned());

            borrowedBookRepository.save(temp);
            user.addBorrowedBook(temp);
        }

//        userRepository.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/user/edit/new")
    public String userEditNew(@ModelAttribute("newUser") User newUser, Model model) {
        Book book = bookRepository.findAll().iterator().next();
        borrowedBookRepository.save(new BorrowedBook(newUser.getId(), book, new Date(), new Date(), false));

        return "redirect:/admin";
    }

    @PostMapping("/admin/user/add")
    public String userAdd(RegistrationForm form) {
        User user = form.toUser(passwordEncoder);
        userRepository.save(user);

        return "redirect:/admin";
    }

    @PostMapping("/admin/book/add")
    public String bookAdd(@ModelAttribute("book") BookAddForm bookForm) {
        Book book = new Book();
        book.setIsbn(bookForm.getIsbn());
        book.setName(bookForm.getName());
        book.setGenre(bookForm.getGenre());
        book.setAuthor(bookForm.getAuthor());
        book.setPublisher(bookForm.getPublisher());
        book.setPages(bookForm.getPages());
        book.setQuantity(bookForm.getQuantity());
        book.setIssueyear(bookForm.getIssueyear());
        book.setDescription(bookForm.getDescription());
        if(!bookForm.getFile().isEmpty())
            book.setCover(imageToByteArray(bookForm.getFile()));
        else
            book.setCover(imageToByteArray("/static/images/common/default_cover.png"));

        bookRepository.save(book);

        return "redirect:/admin";
    }

    public byte[] imageToByteArray(MultipartFile file) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (InputStream in = file.getInputStream()) {
            int length;
            byte[] buffer = new byte[1024];
            while ((length = in.read(buffer)) != -1)
                out.write(buffer, 0, length);
        } catch(IOException e) {
        }

        return out.toByteArray();
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
}
