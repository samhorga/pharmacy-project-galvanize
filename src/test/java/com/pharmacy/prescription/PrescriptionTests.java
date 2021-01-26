package com.pharmacy.prescription;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrescriptionTests {

    @Test
    public void createPrescription() {
        Prescription prescription = new Prescription("tylenol", 2, "John Adams", "Kevin Abraham");

        assertEquals("tylenol", prescription.getDrugName());
        assertEquals(2, prescription.getQuantity());
        assertEquals("John Adams", prescription.getPatientName());
        assertEquals("Kevin Abraham", prescription.getDoctorName());
    }

}
