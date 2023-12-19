package com.example.datlichkham.dto;

public class OrderDoctorDTO {
    private int id;
    private int file_id;
    private int doctor_id;
    private String start_time;
    private String start_date;
    private float total;
    private int order_id;
    private String status;

    public static final String nameTable = "tbOrderDoctor";
    public static final String colFileId = "file_id";
    public static final String colDoctorId = "doctor_id";
    public static final String colStartTime = "start_time";
    public static final String colStartDate = "start_date";
    public static final String colTotal = "total";
    public static final String colStatus ="status";
    public OrderDoctorDTO() {
    }
    public OrderDoctorDTO(int id, int file_id, int doctor_id, String start_time, String start_date, float total) {
        this.id = id;
        this.file_id = file_id;
        this.doctor_id = doctor_id;
        this.start_time = start_time;
        this.start_date = start_date;
        this.total = total;
    }

    public OrderDoctorDTO(int id, int file_id, int doctor_id, String start_time, String start_date, float total, int order_id) {
        this.id = id;
        this.file_id = file_id;
        this.doctor_id = doctor_id;
        this.start_time = start_time;
        this.start_date = start_date;
        this.total = total;
        this.order_id = order_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public OrderDoctorDTO(int id, int file_id, String start_time, String start_date, int order_id) {
        this.id = id;
        this.file_id = file_id;
        this.start_time = start_time;
        this.start_date = start_date;
        this.order_id = order_id;
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

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
