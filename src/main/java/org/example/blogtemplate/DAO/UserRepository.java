package org.example.blogtemplate.DAO;

import org.example.blogtemplate.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    public User findUserByUserName(String username);

    public User findUserByEmail(String email);
}
