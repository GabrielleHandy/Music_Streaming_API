package com.example.musicstreamingapi.model.request;

public class LoginRequest {
    private String emailAddress;
    private String password;

    public LoginRequest() {
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public String getPassword() {
        return password;
    }
}
