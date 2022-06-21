package fr.vertours.patientms.service;

import fr.vertours.patientms.exception.PatientDoesNotExistException;
import fr.vertours.patientms.model.Patient;
import fr.vertours.patientms.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repository;

    public PatientServiceImpl(PatientRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Patient> getAllPatients() {
        log.debug("service : get the list of all patients");
        return repository.findAll();
    }

    @Override
    public Patient getPatientById(long id) {
        log.debug("service : get patient by id : " + id);
        return getById(id);
    }

    @Override
    public Patient savePatient(Patient patient) {
        Patient patientCreated = repository.save(patient);
        log.debug("service : create patient : " + patient);
        return patientCreated;
    }

    @Override
    public Patient updatePatient(long id, Patient updatePatient) {
        Patient dBPatient = getById(id);

        dBPatient.setFirstName(updatePatient.getFirstName());
        dBPatient.setLastName(updatePatient.getLastName());
        dBPatient.setDateOfBirth(updatePatient.getDateOfBirth());
        dBPatient.setGender(updatePatient.getGender());
        dBPatient.setAddress(updatePatient.getAddress());
        dBPatient.setPhone(updatePatient.getPhone());
        log.debug("service : update patient : " + updatePatient);
        return repository.save(dBPatient);
    }

    @Override
    public void deletePatient(long id) {
        Patient patient = getById(id);
        repository.deleteById(patient.getId());
        log.debug("service : delete patient by id : " + id);
    }

    private Patient getById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PatientDoesNotExistException(id));
    }
}
