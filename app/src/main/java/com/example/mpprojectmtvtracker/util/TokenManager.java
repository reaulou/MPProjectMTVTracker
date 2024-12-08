package com.example.mpprojectmtvtracker.util;

public class TokenManager {
    private static TokenManager instance;

    private String accessToken;

    private TokenManager(String accessToken) {
        this.accessToken = accessToken;
    }

    public static TokenManager getInstance(String accessToken) {
        if (instance == null) {
            instance = new TokenManager(accessToken);
        }
        return instance;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
