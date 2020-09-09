package com.sofiaq.shiritai.models;

import com.sofiaq.shiritai.security.model.User;
import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table (name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String question_body;
    @Type(type = "numeric_boolean")
    private boolean status;
    @ManyToOne
    private User from_user;
    @ManyToOne
    @NotNull
    private User to_user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion_body() {
        return question_body;
    }

    public void setQuestion_body(String question_body) {
        this.question_body = question_body;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getFrom_user() {
        return from_user;
    }

    public void setFrom_user(User from_user) {
        this.from_user = from_user;
    }

    public User getTo_user() {
        return to_user;
    }

    public void setTo_user(User to_user) {
        this.to_user = to_user;
    }
    
    
}
