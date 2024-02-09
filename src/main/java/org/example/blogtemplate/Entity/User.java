package org.example.blogtemplate.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="username")
    private String userName;

    @Column(unique = true, nullable = false,name="email")
    private String email;

    @Column(name="password")
    private String password;

}
