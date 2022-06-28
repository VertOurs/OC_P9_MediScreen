package fr.vertours.patientms.controller;

import fr.vertours.patientms.service.PatientServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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


    @Test
    void home() throws Exception {
        mvc.perform(get("/api/patient"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        is("Greetings form PatientMS API !")));
    }

    @Test
    @Disabled
    void allPatients() throws Exception {
        mvc.perform(get("/api/patient/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName",
                        is("Aymeric")));
    }

    @Test
    @Disabled
    void getPatientById() throws Exception {
        mvc.perform(get("/api/patient/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",
                        is("Aymeric")));
    }
    @Test
    @Disabled
    void getPatientByIdException() throws Exception {
        mvc.perform(get("/api/patient/150"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$",
                        is("this id : 150, was not found in database")));
    }

    @Test
    @Disabled
    void savePatient() throws Exception {
        String newPatient = " { "
                + "\"firstName\" : \"Aymeric\","
                + " \"lastName\" : \"Perrin\","
                + " \"dateOfBirth\" : \"1966-12-31\","
                + " \"gender\" : \"M\","
                + " \"address\" : \"1551, rue Louis Blériot\","
                + " \"phone\": \"06 74 89 65 14\" }";

        mvc.perform(post("/api/patient/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPatient))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Aymeric")));
    }

    @Test
    @Disabled
    void updatePatient() throws Exception {
        String updatePatient = " { "
                + "\"firstName\" : \"Léa\","
                + " \"lastName\" : \"Perrin\","
                + " \"dateOfBirth\" : \"1966-12-31\","
                + " \"gender\" : \"F\","
                + " \"address\" : \"1551, rue Louis Blériot\","
                + " \"phone\": \"06 74 89 65 14\" }";

        mvc.perform(put("/api/patient/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatePatient))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Léa")));
    }

    @Test
    @Disabled
    void deletePatient() throws Exception {
        mvc.perform(delete("/api/patient/delete/180"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$",
                        is("this id : 180, was not found in database")));
    }
}