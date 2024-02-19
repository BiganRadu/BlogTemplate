package org.example.blogtemplate.Service;

import org.example.blogtemplate.DAO.CommentRepository;
import org.example.blogtemplate.Entity.Comment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class CommentServiceImpl implements  CommentService{
    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> getCommentById(int id) {
        return commentRepository.findById(id);
    }

    @Override
    public void deleteCommentById(int id){
        commentRepository.deleteById(id);
    }
}
