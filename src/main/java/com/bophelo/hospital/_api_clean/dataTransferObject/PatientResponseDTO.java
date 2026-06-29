package com.bophelo.hospital._api_clean.dataTransferObject;

public class PatientResponseDTO {

    private String memberNumber;
    private String name;
    private int age;
    private String illness;
    private int daysAdmitted;

    public PatientResponseDTO(String memberNumber, String name, int age, String illness, int daysAdmitted) {
        this.memberNumber = memberNumber;
        this.name = name;
        this.age = age;
        this.illness = illness;
        this.daysAdmitted = daysAdmitted;
    }

    public int getAge() {
        return age;
    }

    public int getDaysAdmitted() {
        return daysAdmitted;
    }

    public String getIllness() {
        return illness;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public String getName() {
        return name;
    }
}
