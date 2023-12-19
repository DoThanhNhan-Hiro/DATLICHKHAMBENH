package com.example.datlichkham.dto;

public class TimeWorkDTO {
    private int id;
    private String session;

    public TimeWorkDTO() {
    }

    public static final  String nameTable = "tbTimeWork";

    public TimeWorkDTO(int id, String session) {
        this.id = id;
        this.session = session;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
