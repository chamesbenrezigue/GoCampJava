/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoCampJavaFX.com.esprit.Entite;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Subject {
    private int id;
    private String subject;
    private LocalDateTime time;
    private User user;
    private List<Comment> comments = new ArrayList<>();

    public Subject() {
    }

    public Subject(int id, String subject, User user) {
        this.id = id;
        this.subject = subject;
        this.time = LocalDateTime.now();
        this.user = user;
    }

    public Subject(int id) {
        this.id = id;
    }
    
    public Subject(String subject, User user) {
        this.subject = subject;
        this.time = LocalDateTime.now();
        this.user = user;
    }

    public Subject(int id, String subject) {
        this.id = id;
        this.subject = subject;
        this.time = LocalDateTime.now();
    }
    
    

    public Subject(String subject) {
        this.subject = subject;
        this.time = LocalDateTime.now();
    }

    public Subject(int id, String subject, LocalDateTime time, User user) {
        this.id = id;
        this.subject = subject;
        this.time = time;
        this.user = user;
    }
public Subject(String subject, LocalDateTime time, User user) {
        this.subject = subject;
        this.time = time;
        this.user = user;
    }

    public Subject(String subject, LocalDateTime time) {
        this.subject = subject;
        this.time = time;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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



    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Subject{" + "id=" + id + ", subject=" + subject + ", time=" + time + ", user=" + user.getIdUser() + ", comments=" + comments + '}';
    }

    
    
}
