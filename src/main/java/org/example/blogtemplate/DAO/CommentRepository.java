package org.example.blogtemplate.DAO;

import org.example.blogtemplate.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

}
