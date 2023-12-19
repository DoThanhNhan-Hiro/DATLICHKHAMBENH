package com.example.datlichkham.dto;

public class OrderDetailDTO {
    private int order_id;
    private int orderDoctor_id;

    public static final String nameTable = "tbOrderDetail";
    public static final String colOrderId = "order_id";
    public static final String colOrderDoctorId = "orderDoctor_id";

    public OrderDetailDTO(int order_id, int orderDoctor_id) {
        this.order_id = order_id;
        this.orderDoctor_id = orderDoctor_id;
    }

    public OrderDetailDTO() {
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getOrderDoctor_id() {
        return orderDoctor_id;
    }

    public void setOrderDoctor_id(int orderDoctor_id) {
        this.orderDoctor_id = orderDoctor_id;
    }
}
