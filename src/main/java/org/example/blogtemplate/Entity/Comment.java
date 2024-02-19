package org.example.blogtemplate.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity()
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name="author_id")
    private int authorId;

    @Column(name="post_id")
    private int postId;

    @Column(name="text")
    private String text;

    @Column(name="date")
    private Date date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", referencedColumnName = "id", insertable=false, updatable=false)
    private User user;
    public Comment(int id, int authorId, int postId, String text, Date date) {
        this.id = id;
        this.authorId = authorId;
        this.postId = postId;
        this.text = text;
        this.date = date;
    }

    public Comment(){

    }

    public Comment(int authorId, int postId, String text, Date date) {
        this.authorId = authorId;
        this.postId = postId;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
