package com.example.mpprojectmtvtracker.util;

public class TokenManager {
    private static TokenManager instance;

    private String accessToken;

    private TokenManager(String accessToken) {
        this.accessToken = accessToken;
    }

    public static TokenManager getInstance() {
        if (instance == null) {
            instance = new TokenManager("dummyToken123");
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
