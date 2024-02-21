package com.example.FirebaseInitializer.model;

import jakarta.validation.constraints.NotBlank;

public class FCMRequest {
    @NotBlank(message = "Message is required")
    private String message;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Token is required")
    private String token;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
