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
}
