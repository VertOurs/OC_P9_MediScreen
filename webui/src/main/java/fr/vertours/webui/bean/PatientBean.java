package fr.vertours.webui.bean;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientBean {

    private long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private char gender;
    private String address;
    private String phone;

    public PatientBean(long id, String firstName, String lastName, LocalDate dateOfBirth, char gender, String address, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }
}
