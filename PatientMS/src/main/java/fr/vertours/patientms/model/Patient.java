package fr.vertours.patientms.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "FirstName is mandatory")
    @Column
    private String firstName;

    @NotBlank(message = "LastName is mandatory")
    @Column
    private String lastName;

    @NotNull(message = "Date of birth is mandatory")
    @Column
    private LocalDate dateOfBirth;

    @NotNull(message = "Gender is mandatory")
    @Column
    private char gender;

    @NotBlank(message = "address is mandatory")
    @Column
    private String address;

    @NotBlank(message = "phone is mandatory")
    @Column
    private String phone;

}
