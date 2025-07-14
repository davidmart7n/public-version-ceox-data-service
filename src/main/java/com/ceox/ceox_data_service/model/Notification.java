package com.ceox.ceox_data_service.model;

public class Notification {
    private String title;
    private String body;
    private String timestamp; 

    public Notification() {}

    public Notification(String title, String body, String timestamp) {
        this.title = title;
        this.body = body;
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
