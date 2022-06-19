package com.example.Library.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User implements UserDetails {


    public enum Role {
        USER, ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "users_id_seq")
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String street;
    private String house;
    private String phonenumber;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registrationdate;
    private byte[] profilepicture;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "userid")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<BorrowedBook> borrowedBooks;

    @OneToMany
    @JoinColumn(name = "userid")
    private List<FavoriteBook> favoriteBooks;

    public User(String firstname,
                String lastname,
                String username,
                String password,
                String street,
                String house,
                String phonenumber,
                Date registrationdate,
                byte[] profilepicture,
                Role role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.street = street;
        this.house = house;
        this.phonenumber = phonenumber;
        this.registrationdate = registrationdate;
        this.profilepicture = profilepicture;
        this.role = role;
    }

    public void addBorrowedBook(BorrowedBook book) {
        this.borrowedBooks.add(book);
    }

    public void addFavoriteBook(FavoriteBook book) {
        this.favoriteBooks.add(book);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        if(this.role == Role.USER)
            list.add(new SimpleGrantedAuthority("ROLE_USER"));
        if(this.role == Role.ADMIN)
            list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return list;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
