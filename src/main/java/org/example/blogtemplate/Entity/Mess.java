package org.example.blogtemplate.Entity;

public class Mess {
    String subject, sender, content,senderEmail;

    public Mess(){

    }

    public Mess(String subject, String sender, String senderEmail, String content) {
        this.subject = subject;
        this.sender = sender;
        this.content = content;
        this.senderEmail = senderEmail;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }


    public void setContent(String content) {
        this.content = content;
    }
    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSubject() {
        return subject;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }
    public String getSenderEmail() {
        return senderEmail;
    }

}
