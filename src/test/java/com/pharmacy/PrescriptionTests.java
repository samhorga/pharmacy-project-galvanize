package com.pharmacy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrescriptionTests {

    @Test
    public void createPrescription() {
        Drug drug = new Drug("tylenol", 2, 14.34d);
        Prescription prescription = new Prescription(drug, "John Adams", "Kevin Abraham");

        assertEquals("tylenol", prescription.getDrug().getDrugName());
        assertEquals(2, prescription.getDrug().getQuantity());
        assertEquals("John Adams", prescription.getPatientName());
        assertEquals("Kevin Abraham", prescription.getDoctorName());
    }

}
