package com.example.demo.DTO;

public class LoginResponse {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;
    public LoginResponse(String token){
        this.token = token;
    }
}
