package fr.vertours.notems.controller;

import fr.vertours.notems.domain.Note;
import fr.vertours.notems.repository.NoteRepository;
import fr.vertours.notems.service.NoteServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;



import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NoteControllerTest {

    @Autowired
    public MockMvc mvc;

    @Autowired
    NoteServiceImpl service;

    @Autowired
    NoteRepository repository;


    static Note getExpectedNote(){
        Note expectedNote = new Note();
        expectedNote.setPatientId(400);
        expectedNote.setRecommendation("recommendation de test");
        return expectedNote;
    }

    @Test
    void home() throws Exception {
        mvc.perform(get("/api/note"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        is("Greetings form NoteMS API !")));
    }

    @Test
    void listNote() throws Exception {
        Note note = getExpectedNote();
        Note save = repository.save(note);
        mvc.perform(get("/api/note/all/"+ save.getPatientId()))
                .andExpect(status().isOk());
    }

    @Test
    void getNoteById() throws Exception {
        Note note = getExpectedNote();
        Note save = repository.save(note);

        mvc.perform(get("/api/note/"+ save.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",
                        is(note.getId())));
    }

    @Test
    void saveNote() throws Exception {
        String newNote = " { "
                + "\"patientId\" : \"400\","
                + " \"recommendation\": \"reccommendation de test\" }";

        mvc.perform(post("/api/note/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newNote))
                .andExpect(status().isOk())
                .andExpect(jsonPath(
                        "$.patientId",
                        is(400)));
    }

    @Test
    void updatePatient() throws Exception {
        Note note = getExpectedNote();
        Note save = repository.save(note);

        String updateNote = " { "
                + "\"patientId\" : \"401\","
                + " \"recommendation\": \"reccommendation de test\" }";

        mvc.perform(put("/api/note/update/"+save.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateNote))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientId", is(401)));
    }
}