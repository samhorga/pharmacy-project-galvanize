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
        pharmacy.add(new Drug("tylenol", 10, 14.34d));

        int actualQuantity = pharmacy.checkQuantity("tylenol");

        assertEquals(10, actualQuantity);

    }

    @Test(expected = DrugNotFoundException.class)
    public void checkDrugNonAvailability() {
        pharmacy.checkQuantity("tylenol");
    }

    @Test
    public void checkPrescriptionValid() {
        Drug drug = new Drug("tylenol", 2, 14.34d);
        pharmacy.add(drug);
        Prescription prescription = new Prescription(drug, "John Adams", "Kevin Abraham");

        pharmacy.setPrescription(prescription);
        String validityPrescription = pharmacy.checkPrescription(prescription);

        assertEquals("Prescription is valid", validityPrescription);
    }

    @Test
    public void checkPrescriptionNotValid() {
        Drug drug = new Drug("tylenol", 2, 14.34d);
        pharmacy.add(drug);
        Prescription prescription = new Prescription(drug, "John Adams", "Kevin Abraham");

        pharmacy.setPrescription(prescription);

        Prescription prescription1 = new Prescription(drug, "John Abraham", "Adams Kevin");

        String validityPrescription = pharmacy.checkPrescription(prescription1);

        assertEquals("Can't dispense the medication", validityPrescription);
    }

    @Test
    public void dispenseMedication() {
        Drug drug = new Drug("tylenol", 2, 14.34d);
        pharmacy.add(drug);
        Prescription prescription = new Prescription(drug, "John Adams", "Kevin Abraham");

        pharmacy.setPrescription(prescription);

        pharmacy.dispenseMedication();

        assertEquals(0, pharmacy.checkQuantity(drug.getDrugName()));
    }

    @Test
    public void changeStatusToReady() {
        Drug drug = new Drug("tylenol", 2, 14.34d);
        pharmacy.add(drug);
        Prescription prescription = new Prescription(drug, "John Adams", "Kevin Abraham");

        pharmacy.setPrescription(prescription);

        pharmacy.dispenseMedication();

        assertEquals(0, pharmacy.checkQuantity(drug.getDrugName()));
        assertEquals("READY", prescription.getStatus());
    }

    @Test
    public void checkPharmacyBalance() {
        Drug drug = new Drug("tylenol", 2, 14.34d);
        pharmacy.add(drug);
        Prescription prescription = new Prescription(drug, "John Adams", "Kevin Abraham");

        pharmacy.setPrescription(prescription);

        pharmacy.dispenseMedication();

        pharmacy.pickUp();

        assertEquals(28.68, pharmacy.getBalance(), 0.1);
    }

    @Test
    public void changeStatusToSold() {
        Drug drug = new Drug("tylenol", 2, 14.34d);
        pharmacy.add(drug);
        Prescription prescription = new Prescription(drug, "John Adams", "Kevin Abraham");

        pharmacy.setPrescription(prescription);

        pharmacy.dispenseMedication();

        pharmacy.pickUp();

        assertEquals("SOLD", pharmacy.getPrescription().getStatus());
    }

    @Test
    public void cancelPrescription() {
        Drug drug = new Drug("tylenol", 2, 14.34d);
        pharmacy.add(drug);
        Prescription prescription = new Prescription(drug, "John Adams", "Kevin Abraham");

        pharmacy.setPrescription(prescription);

        pharmacy.dispenseMedication();

        double refundedAmount = pharmacy.cancelPrescription();

        assertEquals(28.68, refundedAmount, 0.1);
    }

}
