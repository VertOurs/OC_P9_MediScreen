package fr.vertours.webui.controller;

import fr.vertours.webui.proxy.AssessProxy;
import fr.vertours.webui.proxy.PatientProxy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PatientProxy patientProxy;

    @MockBean
    private AssessProxy assessProxy;


    @Test
    void home() throws Exception {
        mvc.perform(get("/home"))
                .andExpect(status().isOk());
    }

    @Test
    void result() {
    }
}