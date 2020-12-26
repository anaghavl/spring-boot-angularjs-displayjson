package com.userdata.display.model;

// Structure of the parent table response
public class Parent {
    private Integer id;
    private String sender;
    private String receiver;
    private Integer totalAmount;
    private Integer totalPaidAmount;

    public Parent() {}

    public Parent(Integer id, String sender, String receiver, Integer totalAmount, Integer totalPaidAmount) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.totalAmount = totalAmount;
        this.totalPaidAmount = totalPaidAmount;
    }

    public Integer getId() {
        return id;
    }

    public String getSender(){
        return sender;
    }

    public String getReceiver(){
        return receiver;
    }

    public Integer getTotalAmount(){
        return totalAmount;
    }

    public Integer getTotalPaidAmount(){
        return totalPaidAmount;
    }
}