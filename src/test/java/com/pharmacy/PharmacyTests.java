package com.pharmacy;

import com.pharmacy.exceptions.DrugNotFoundException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PharmacyTests {

    private Pharmacy pharmacy;
    private Drug drug;

    @Before
    public void setUp() {
        pharmacy = new Pharmacy();
        drug = new Drug("tylenol", 2, 14.34d);
    }

    @Test
    public void checkDrugAvailability() {
        pharmacy.add(drug);

        int actualQuantity = pharmacy.checkQuantity("tylenol");

        assertEquals(2, actualQuantity);
    }

    @Test(expected = DrugNotFoundException.class)
    public void checkDrugNonAvailability() {
        pharmacy.checkQuantity("tylenol");
    }

    @Test
    public void checkPrescriptionValid() {
        pharmacy.add(drug);
        Prescription prescription = new Prescription(drug, "John Adams", "Kevin Abraham");

        pharmacy.setPrescription(prescription);
        String validityPrescription = pharmacy.checkPrescription(prescription);

        assertEquals("Prescription is valid", validityPrescription);
    }

    @Test
    public void checkPrescriptionNotValid() {
        pharmacy.add(drug);
        Prescription prescription = new Prescription(drug, "John Adams", "Kevin Abraham");

        pharmacy.setPrescription(prescription);

        Prescription prescription1 = new Prescription(drug, "John Abraham", "Adams Kevin");

        String validityPrescription = pharmacy.checkPrescription(prescription1);

        assertEquals("Can't dispense the medication", validityPrescription);
    }

    @Test
    public void dispenseMedication() {
        pharmacy.add(drug);
        Prescription prescription = new Prescription(drug, "John Adams", "Kevin Abraham");

        pharmacy.setPrescription(prescription);

        pharmacy.dispenseMedication();

        assertEquals(0, pharmacy.checkQuantity(drug.getDrugName()));
    }

    @Test
    public void changeStatusToReady() {
        pharmacy.add(drug);
        Prescription prescription = new Prescription(drug, "John Adams", "Kevin Abraham");

        pharmacy.setPrescription(prescription);

        pharmacy.dispenseMedication();

        assertEquals("READY", prescription.getStatus());
    }

    @Test
    public void checkPharmacyBalance() {
        pharmacy.add(drug);
        Prescription prescription = new Prescription(drug, "John Adams", "Kevin Abraham");

        pharmacy.setPrescription(prescription);

        pharmacy.dispenseMedication();

        pharmacy.pickUp();

        assertEquals(28.68, pharmacy.getBalance(), 0.1);
    }

    @Test
    public void changeStatusToSold() {
        pharmacy.add(drug);
        Prescription prescription = new Prescription(drug, "John Adams", "Kevin Abraham");

        pharmacy.setPrescription(prescription);

        pharmacy.dispenseMedication();

        pharmacy.pickUp();

        assertEquals("SOLD", pharmacy.getPrescription().getStatus());
    }

    @Test
    public void cancelPrescription() {
        pharmacy.add(drug);
        Prescription prescription = new Prescription(drug, "John Adams", "Kevin Abraham");

        pharmacy.setPrescription(prescription);

        pharmacy.dispenseMedication();

        double refundedAmount = pharmacy.cancelPrescription();

        assertEquals(28.68, refundedAmount, 0.1);
    }

    @Test
    public void changeStatusToNotAvailableInStock() {
        pharmacy.add(drug);
        pharmacy.getDrugList().get(0).setQuantity(0);
        Prescription prescription = new Prescription(drug, "John Adams", "Kevin Abraham");

        pharmacy.setPrescription(prescription);
        pharmacy.dispenseMedication();

        assertEquals("NOT AVAILABLE IN STOCK", prescription.getStatus());
    }

    @Test
    public void removeDrugFromThePharmacyWhenQuantityIs0() {
        pharmacy.add(drug);
        pharmacy.getDrugList().get(0).setQuantity(0);
        Prescription prescription = new Prescription(drug, "John Adams", "Kevin Abraham");

        pharmacy.setPrescription(prescription);
        pharmacy.dispenseMedication();

        assertFalse(pharmacy.getDrugList().contains(drug));
    }
}
