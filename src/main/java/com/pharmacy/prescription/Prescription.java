package com.pharmacy.prescription;

public class Prescription {
    private String drugName;
    private int quantity;
    private String patientName;
    private String doctorName;

    public Prescription(String drugName, int quantity, String patientName, String doctorName) {
        this.drugName = drugName;
        this.quantity = quantity;
        this.patientName = patientName;
        this.doctorName = doctorName;
    }

    public String getDrugName() {
        return drugName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }
}
