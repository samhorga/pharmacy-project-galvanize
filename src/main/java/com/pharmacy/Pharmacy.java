package com.pharmacy;

import com.pharmacy.exceptions.DrugNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pharmacy {
    List<Drug> drugList = new ArrayList<Drug>();
    private Prescription prescription;
    private double balance;

    public void add(Drug drug) {
        drugList.add(drug);
    }

    public int checkQuantity(final String drugName) {
        Optional<Drug> theDrug = drugList.stream().filter(drug -> drug.getDrugName().equals(drugName)).findFirst();

        if (theDrug.isPresent()) {
            return theDrug.get().getQuantity();
        } else {
            throw new DrugNotFoundException("No such drug exists");
        }
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public String checkPrescription(Prescription doctorsPrescription) {
        if (prescription.equals(doctorsPrescription)) {
            return "Prescription is valid";
        } else {
            return "Can't dispense the medication";
        }
    }

    public void dispenseMedication() {
        Drug drug = this.getTheDrug(this.prescription.getDrug().getDrugName());
        if(drug.getQuantity() > 0) {
            drug.setQuantity(drug.getQuantity() - prescription.getDrug().getQuantity());
            this.prescription.setStatus("READY");
        }
    }

    public Drug getTheDrug(String drugName) {
        Optional<Drug> theDrug = drugList.stream().filter(drug -> drug.getDrugName().equals(drugName)).findFirst();

        if (theDrug.isPresent()) {
            return theDrug.get();
        }
        return null;
    }

    public double getBalance() {
        return balance;
    }

    public void pickUp() {
        this.balance+=this.getTheDrug(prescription.getDrug().getDrugName()).getPrice()  * prescription.getDrug().getQuantity();
        this.prescription.setStatus("SOLD");
    }

    public double cancelPrescription() {
        double amountRefunded = this.getTheDrug(prescription.getDrug().getDrugName()).getPrice()  * prescription.getDrug().getQuantity();
        Drug drug = this.getTheDrug(this.prescription.getDrug().getDrugName());
        if(drug.getQuantity() > 0) {
            drug.setQuantity(drug.getQuantity() + prescription.getDrug().getQuantity());
            this.prescription.setStatus("CANCELLED");
        }
        return amountRefunded;
    }
}
