package org.example.blogtemplate.Service;

import org.example.blogtemplate.Entity.Comment;

import java.util.Optional;

public interface CommentService {
    void saveComment(Comment comment);

    Optional<Comment> getCommentById(int id);

    void deleteCommentById(int id);
}
