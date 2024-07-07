package com.example.pharmacy_management_system;

import java.util.Date;

public class PurchaseRecord {
    private String buyer;
    private Date purchaseDate;
    private double amount;

    public PurchaseRecord(String buyer, Date purchaseDate, double amount) {
        this.buyer = buyer;
        this.purchaseDate = purchaseDate;
        this.amount = amount;
    }

    public String getBuyer() {
        return buyer;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public double getAmount() {
        return amount;
    }
}
