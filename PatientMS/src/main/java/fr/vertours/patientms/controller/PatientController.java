package fr.vertours.patientms.controller;

import fr.vertours.patientms.exception.PersonAlreadyPresentException;
import fr.vertours.patientms.model.Patient;
import fr.vertours.patientms.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("API for CRUD operations on patients")
@RestController
@Slf4j
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }


    @ApiOperation(value = "home application")
    @GetMapping("")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok().body("Greetings form PatientMS API !");
    }

    @ApiOperation(value = "Get all patients")
    @GetMapping("/all")
    public ResponseEntity<List<Patient>> allPatients() {
        List<Patient> allPatients = service.getAllPatients();
        log.debug("controller : get the list of all patients");
        return  ResponseEntity.ok().body(allPatients);
    }

    @ApiOperation(value = "Get patient by id")
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable long id) {
        Patient patient = service.getPatientById(id);
        log.debug("controller : get patient by id : " + id);
        return ResponseEntity.ok().body(patient);
    }

    @ApiOperation(value = "Create new patient")
    @PostMapping("/create")
    public ResponseEntity<Patient> savePatient(@RequestBody @Valid Patient patient) {
        Patient patientCreated = service.savePatient(patient);
        log.debug("controller : create patient : " + patient);
        return ResponseEntity.ok().body(patientCreated);
    }

    @ApiOperation(value = "Update patient")
    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable long id, @RequestBody @Valid Patient patient) {
        Patient patientUpdated = service.updatePatient(id, patient);
        log.debug("controller : update patient : " + patientUpdated);
        return ResponseEntity.ok().body(patientUpdated);
    }
}
