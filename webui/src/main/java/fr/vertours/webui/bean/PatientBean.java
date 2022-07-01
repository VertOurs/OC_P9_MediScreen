package fr.vertours.webui.bean;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientBean {

    private long patientId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private char gender;
    private String address;
    private String phone;
}
