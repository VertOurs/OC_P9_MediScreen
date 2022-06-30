package fr.vertours.assessms.Bean;

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
}
