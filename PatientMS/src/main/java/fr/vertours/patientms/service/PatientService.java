package fr.vertours.patientms.service;

import fr.vertours.patientms.model.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();

    Patient getPatientById(long id);

    Patient savePatient(Patient patient);

    Patient updatePatient(long id, Patient patient);

    void deletePatient(long id);
}
