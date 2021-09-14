package com.javaweb.router.bean;

public class Message {
    private int id;
    private String string;

    public Message() {

    }

    public Message(int id, String string) {
        this.id = id;
        this.string = string;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", string='" + string + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
