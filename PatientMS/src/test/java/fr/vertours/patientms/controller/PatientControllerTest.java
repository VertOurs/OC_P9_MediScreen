package fr.vertours.patientms.controller;

import fr.vertours.patientms.model.Patient;
import fr.vertours.patientms.repository.PatientRepository;
import fr.vertours.patientms.service.PatientServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {

    @Autowired
    public MockMvc mvc;

    @Autowired
    PatientServiceImpl service;

    @Autowired
    PatientRepository repository;



    static Patient getJean() {
        Patient patient = new Patient();
        patient.setId(1l);
        patient.setFirstName("Jean");
        patient.setLastName("Moulin");
        patient.setDateOfBirth(LocalDate.of(1990, 01, 01));
        patient.setGender('M');
        patient.setAddress("rue du test");
        patient.setPhone("phone du test");
        return patient;
    }

    @Test
    void home() throws Exception {
        mvc.perform(get("/api/patient"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        is("Greetings form PatientMS API !")));
    }

    @Test
    void allPatients() throws Exception {
        repository.deleteAll();
        Patient patient = getJean();
        repository.save(patient);

        mvc.perform(get("/api/patient/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName",
                        is("Jean")));
    }

    @Test
    void getPatientById() throws Exception {
        repository.deleteAll();
        Patient patient = getJean();
        repository.save(patient);
        mvc.perform(get("/api/patient/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",
                        is("Jean")));
    }
    @Test
    void getPatientByIdException() throws Exception {
        repository.deleteAll();
        mvc.perform(get("/api/patient/150"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$",
                        is("this id : 150, was not found in database")));
    }

    @Test
    void savePatient() throws Exception {
        repository.deleteAll();
        String newPatient = " { "
                + "\"firstName\" : \"Jean\","
                + " \"lastName\" : \"Perrin\","
                + " \"dateOfBirth\" : \"1990-01-10\","
                + " \"gender\" : \"M\","
                + " \"address\" : \"rue du test\","
                + " \"phone\": \"phone du test\" }";

        mvc.perform(post("/api/patient/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPatient))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Jean")));
    }

    @Test
    void updatePatient() throws Exception {
        repository.deleteAll();
        Patient patient = getJean();
        patient.setId(400l);
        Patient save = repository.save(patient);
        String updatePatient = " { "
                + "\"firstName\" : \"Léa\","
                + " \"lastName\" : \"Perrin\","
                + " \"dateOfBirth\" : \"1990-01-10\","
                + " \"gender\" : \"F\","
                + " \"address\" : \"rue du test\","
                + " \"phone\": \"phone du test\" }";


        mvc.perform(put("/api/patient/update/"+save.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatePatient))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Léa")));
    }
}