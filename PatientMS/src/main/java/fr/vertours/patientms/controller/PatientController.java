package fr.vertours.patientms.controller;

import fr.vertours.patientms.model.Patient;
import fr.vertours.patientms.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }


    @GetMapping("")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok().body("Greetings form PatientMS API ! ");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> allPatients() {
        List<Patient> allPatients = service.getAllPatients();
        log.debug("controller : get the list of all patients");
        return  ResponseEntity.ok().body(allPatients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable long id) {
        Patient patient = service.getPatientById(id);
        log.debug("controller : get patient by id : " + id);
        return ResponseEntity.ok().body(patient);
    }

    @PostMapping("/create")
    public ResponseEntity<Patient> savePatient(@RequestBody Patient patient) {
        Patient patientCreated = service.savePatient(patient);
        log.debug("controller : create patient : " + patient);
        return ResponseEntity.ok().body(patientCreated);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable long id, @RequestBody Patient patient) {
        Patient patientUpdated = service.updatePatient(id, patient);
        log.debug("controller : update patient : " + patientUpdated);
        return ResponseEntity.ok().body(patientUpdated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable long id) {
        service.deletePatient(id);
        log.debug("controller : delete patient by id : " + id);
        return ResponseEntity.ok().body("Success, patient are correctly delete");
    }
}
