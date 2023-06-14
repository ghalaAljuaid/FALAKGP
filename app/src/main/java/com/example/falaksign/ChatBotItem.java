package com.example.falaksign;

public class ChatBotItem {

    private String message;
    private String type;

    public ChatBotItem(String message, String type) {
        this.message = message;
        this.type = type;
    }

    public ChatBotItem() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
