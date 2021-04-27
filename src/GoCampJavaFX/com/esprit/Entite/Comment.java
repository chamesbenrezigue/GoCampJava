/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.Entite;

import java.time.LocalDateTime;

public class Comment {
    private int id;
    private String text;
    private LocalDateTime time;
    private User user;
    private Subject subject;

    public Comment(int id, String text, User user, Subject subject) {
        this.id = id;
        this.text = text;
        this.time = LocalDateTime.now();
        this.user = user;
        this.subject = subject;
    }

    public Comment(int id, String text, LocalDateTime time, User user, Subject subject) {
        this.id = id;
        this.text = text;
        this.time = time;
        this.user = user;
        this.subject = subject;
    } 
      public Comment( String text, LocalDateTime time, User user, Subject subject) {
        this.text = text;
        this.time = time;
        this.user = user;
        this.subject = subject;
    }

    public Comment(int id, String text) {
        this.id = id;
        this.text = text;
        this.time = LocalDateTime.now();
    }
    
    

    public Comment(int id) {
        this.id = id;
    }
    
    

    public Comment(String text, User user, Subject subject) {
        this.text = text;
        this.time = LocalDateTime.now();
        this.user = user;
        this.subject = subject;
    }

    public Comment() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", text=" + text + ", time=" + time + ", userId=" + user.getIdUser() + ", subjectId=" + subject.getId() + '}';
    }
    
    
    
    
    
}
