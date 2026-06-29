package com.bophelo.hospital._api_clean.dataTransferObject;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class PatientRequestDTO {

    @NotBlank(message = "Patient name is required")
    private String name;

    @Min(value = 1, message = "Age must be greater than 0")
    private int age;

    @NotBlank(message = "Illness is required")
    private String illness;

    @Min(value = 0, message = "Days admitted must at least 1")
    private int daysAdmitted;

    public int getAge() {
        return age;
    }

    public int getDaysAdmitted() {
        return daysAdmitted;
    }

    public String getIllness() {
        return illness;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDaysAdmitted(int daysAdmitted) {
        this.daysAdmitted = daysAdmitted;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public void setName(String name) {
        this.name = name;
    }
}
