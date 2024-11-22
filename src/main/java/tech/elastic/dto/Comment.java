package tech.elastic.dto;

import java.time.LocalDateTime;

public class Comment {
    private String user;
    private String text;
    private LocalDateTime timestamp;

    public Comment() {
    }

    public Comment(String user, String text, LocalDateTime timestamp) {
        this.user = user;
        this.text = text;
        this.timestamp = timestamp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "user='" + user + '\'' +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
