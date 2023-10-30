package com.seminario.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "user")
@Getter
@NoArgsConstructor
public class Users {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column()
    private String name;

    @Column()
    private String email;

    @Column()
    private String username;

    @Column()
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Contact> contacts;

    @OneToMany(mappedBy = "user")
    private Set<Task> taks;

    public Users(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
