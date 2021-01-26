package com.pharmacy;

public class Drug {
    private String drugName;
    private int quantity;

    public Drug(String drugName, int quantity) {
        this.drugName = drugName;
        this.quantity = quantity;
    }

    public String getDrugName() {
        return drugName;
    }

    public int getQuantity() {
        return quantity;
    }
}
