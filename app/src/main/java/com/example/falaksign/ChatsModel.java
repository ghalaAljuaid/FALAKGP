package com.example.falaksign;

public class ChatsModel {

    private String message; //user
    private String sender;  //Bot

    public ChatsModel(String message, String sender) {
        this.message = message;
        this.sender = sender;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
