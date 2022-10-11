package com.example.websession.sessionsport;

public enum SessionConstants {
    AUTH_TOKEN("SessionID");

    String tokenName;
    private SessionConstants(String tokenName) {
        this.tokenName = tokenName;
    }
}
