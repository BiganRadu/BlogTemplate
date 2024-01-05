package org.example.blogtemplate.Entity;


import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Date;

@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="title")
    private String title;
    @Column(name="image_header")
    private String imageHeader;
    @Column(name = "description")
    private String description;
    @Column(name = "text")
    private String text;
    @Column(name="author_id")
    private int authorId;
    @Column(name="date")
    private Date date;


    public Blog(String title, String imageHeader, String text, String description, int authorID, Date date) {
        this.title = title;
        this.imageHeader = imageHeader;
        this.text = text;
        this.description  = description;
        this.authorId = authorId;
        this.date = date;
    }

    public Blog() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageHeader() {
        return imageHeader;
    }

    public void setImageHeader(String imageHeader) {
        this.imageHeader = imageHeader;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
