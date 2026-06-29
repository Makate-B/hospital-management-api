package com.bophelo.hospital._api_clean.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String memberNumber;

    @NotBlank(message = "Patient name is required")
    private String name;

    @Min(value = 1, message = "Age must be greater than 0")
    private int age;

    @NotBlank(message = "Illness is required")
    private String illness;

    @Min(value = 0, message = "Days admitted must not be negative")
    private int daysAdmitted;

    @ManyToOne
    @JoinColumn( name = "doctor_id")
    private Doctor doctor;


    //JPA wants default constructor
    public Patient() {
    }
    public Patient(String memberNumber, String name, int age, String illness, int daysAdmitted) {
        this.memberNumber = memberNumber;
        this.name = name;
        this.age = age;
        this.illness = illness;
        this.daysAdmitted = daysAdmitted;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDaysAdmitted() {
        return daysAdmitted;
    }

    public void setDaysAdmitted(int daysAdmitted) {
        this.daysAdmitted = daysAdmitted;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public long getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
