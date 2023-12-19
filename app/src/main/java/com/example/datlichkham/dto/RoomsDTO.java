package com.example.datlichkham.dto;

public class RoomsDTO {
    private int id;
    private String name;
    private String location;

    public static final String nameTable = "tbRooms";
    public static final String colName = "name";
    public static final String colLocaton = "location";

    public RoomsDTO(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public RoomsDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
