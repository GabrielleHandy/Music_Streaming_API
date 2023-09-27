package com.example.musicstreamingapi.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Column
    private String name;
    @Column
    private String emailAddress;
    @Column(unique = true)
    private String passWord;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // UserProfile

    public User(){

    }

    public User(String name, String emailAddress, String passWord) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}