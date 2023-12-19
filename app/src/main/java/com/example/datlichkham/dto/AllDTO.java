package com.example.datlichkham.dto;

public class AllDTO {
    private String fullameUser, birthdayUser, startDate, startTime;
    private int id;
    private int user_id;
    private String birthday;
    private int service_id;
    private int room_id;
    private String description;
    private int timework_id;
    private int servicesId;
    private String servicesName;
    private float servicesPrice;
    private int categoriesId;
    private String note;

    // thong ke cua dcotor
    private int idDoctor;
    private String day;
    private String time;
    private double total;
    //===================
    private int orderDoctor_id, order_id;
    public AllDTO() {
    }

    public String getNote() {
        return note;
    }

    public int getOrderDoctor_id() {
        return orderDoctor_id;
    }

    public void setOrderDoctor_id(int orderDoctor_id) {
        this.orderDoctor_id = orderDoctor_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTimework_id() {
        return timework_id;
    }

    public void setTimework_id(int timework_id) {
        this.timework_id = timework_id;
    }

    public int getServicesId() {
        return servicesId;
    }

    public void setServicesId(int servicesId) {
        this.servicesId = servicesId;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public float getServicesPrice() {
        return servicesPrice;
    }

    public void setServicesPrice(float servicesPrice) {
        this.servicesPrice = servicesPrice;
    }

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getFullameUser() {
        return fullameUser;
    }

    public void setFullameUser(String fullameUser) {
        this.fullameUser = fullameUser;
    }

    public String getBirthdayUser() {
        return birthdayUser;
    }

    public void setBirthdayUser(String birthdayUser) {
        this.birthdayUser = birthdayUser;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
