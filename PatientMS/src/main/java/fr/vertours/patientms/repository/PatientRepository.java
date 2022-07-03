package fr.vertours.patientms.repository;

import fr.vertours.patientms.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByFirstNameAndLastName(String firstName, String lastName);

}
