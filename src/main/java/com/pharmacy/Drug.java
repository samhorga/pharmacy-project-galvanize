package com.pharmacy;

public class Drug {
    private String drugName;
    private int quantity;
    private double price;

    public Drug(String drugName, int quantity, double price) {
        this.drugName = drugName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getDrugName() {
        return drugName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }
}
