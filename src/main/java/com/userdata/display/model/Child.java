package com.userdata.display.model;

// Structure of the child table response
public class Child {
    private Integer id;
    private Integer parentId;
    private String sender;
    private String receiver;
    private Integer totalAmount;
    private Integer paidAmount;

    public Child() {}

    public Child(Integer id, Integer parentId, String sender, String receiver, Integer totalAmount, Integer paidAmount) {
        this.id = id;
        this.parentId = parentId;
        this.sender = sender;
        this.receiver = receiver;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
    }

    public Integer getId() {
        return id;
    }

    public Integer getParentId() {
        return parentId;
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

    public Integer getPaidAmount() {
        return paidAmount;
    }
}
