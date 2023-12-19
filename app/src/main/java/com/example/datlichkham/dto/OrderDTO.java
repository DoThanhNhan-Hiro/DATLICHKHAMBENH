package com.example.datlichkham.dto;

public class OrderDTO {
    private int id;
    private int file_id;
    private String order_time;
    private String order_date;
    private String status;
    private String note;

    public static final String nameTable = "tbOrders";
    public static final String colFile_id = "file_id";
    public static final String colOrder_time = "order_time";
    public static final String colOrder_date = "order_date";
    public static final String colOrder_status = "status";
    public static final String colNote="note";


    public OrderDTO() {
    }

    public OrderDTO(int id, int file_id, String order_time, String order_date) {
        this.id = id;
        this.file_id = file_id;
        this.order_time = order_time;
        this.order_date = order_date;
    }

    public OrderDTO(int id, int file_id, String order_time, String order_date, String status) {
        this.id = id;
        this.file_id = file_id;
        this.order_time = order_time;
        this.order_date = order_date;
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }
}
