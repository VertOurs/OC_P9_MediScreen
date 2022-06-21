package fr.vertours.patientms.repository;

import fr.vertours.patientms.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
