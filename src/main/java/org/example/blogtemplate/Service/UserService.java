package org.example.blogtemplate.Service;

import org.example.blogtemplate.Entity.User;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(int id);

    User findUserByEmail(String email);

    void saveUser(User user);
}
