package com.example.datlichkham.dto;

public class CategoriesDTO {
    private int id;
    private String name;

    public static final String nameTable = "tbCategories";
    public static final String colNameCategories = "name";

    public CategoriesDTO() {
    }

    public CategoriesDTO(int id, String name) {
        this.id = id;
        this.name = name;
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
}
