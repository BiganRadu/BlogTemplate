package org.example.blogtemplate.Entity;


import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Date;

@Component
public class Blog {
    private int id;

    private String title;

    private URI imageHeader;

    private String description;

    private String text;

    private String userName;

    private Date date;


    public Blog(String title, URI imageHeader, String text, String description, String userName, Date date) {
        this.title = title;
        this.imageHeader = imageHeader;
        this.text = text;
        this.description  = description;
        this.userName = userName;
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

    public URI getImageHeader() {
        return imageHeader;
    }

    public void setImageHeader(URI imageHeader) {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
