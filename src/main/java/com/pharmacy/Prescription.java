package com.pharmacy;

import java.util.Objects;

public class Prescription {
    private Drug drug;
    private String patientName;
    private String doctorName;

    public Prescription(Drug drug,  String patientName, String doctorName) {
        this.drug = drug;
        this.patientName = patientName;
        this.doctorName = doctorName;
    }

    public Drug getDrug() {
        return drug;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return drug.equals(that.drug) &&
                patientName.equals(that.patientName) &&
                doctorName.equals(that.doctorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drug, patientName, doctorName);
    }
}
