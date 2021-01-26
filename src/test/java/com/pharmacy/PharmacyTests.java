package com.pharmacy;

import com.pharmacy.exceptions.DrugNotFoundException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PharmacyTests {

    Pharmacy pharmacy;

    @Before
    public void setUp() {
        pharmacy = new Pharmacy();
    }

    @Test
    public void checkDrugAvailability() {
        pharmacy.add(new Drug("tylenol", 10));

        int actualQuantity = pharmacy.checkQuantity("tylenol");

        assertEquals(10, actualQuantity);

    }

    @Test(expected = DrugNotFoundException.class)
    public void checkDrugNonAvailability() {
        pharmacy.checkQuantity("tylenol");
    }

    @Test
    public void checkPrescriptionValid() {
        Drug drug = new Drug("tylenol", 2);
        pharmacy.add(drug);
        Prescription prescription = new Prescription(drug, "John Adams", "Kevin Abraham");

        pharmacy.setPrescription(prescription);
        String validityPrescription = pharmacy.checkPrescription(prescription);

        assertEquals("Prescription is valid", validityPrescription);
    }

    @Test
    public void checkPrescriptionNotValid() {
        Drug drug = new Drug("tylenol", 2);
        pharmacy.add(drug);
        Prescription prescription = new Prescription(drug, "John Adams", "Kevin Abraham");

        pharmacy.setPrescription(prescription);

        Prescription prescription1 = new Prescription(drug, "John Abraham", "Adams Kevin");

        String validityPrescription = pharmacy.checkPrescription(prescription1);

        assertEquals("Can't dispense the medication", validityPrescription);
    }
}
