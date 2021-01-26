package com.pharmacy;

import com.pharmacy.exceptions.DrugNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pharmacy {
    List<Drug> drugList = new ArrayList<Drug>();
    private Prescription prescription;

    public void add(Drug drug) {
        drugList.add(drug);
    }

    public int checkQuantity(final String drugName) {
        Optional<Drug> theDrug = drugList.stream().filter(drug -> drug.getDrugName().equals(drugName)).findFirst();

        if(theDrug.isPresent()) {
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
        if(prescription.equals(doctorsPrescription)) {
            return "Prescription is valid";
        } else {
            return "Can't dispense the medication";
        }
    }
}
