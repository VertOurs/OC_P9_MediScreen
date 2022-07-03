package fr.vertours.patientms.repository;

import fr.vertours.patientms.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findOneByFirstNameAndLastName(String firstName, String lastName);

}
