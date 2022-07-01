package fr.vertours.notems.controller;

import fr.vertours.notems.domain.Note;
import fr.vertours.notems.service.NoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api("API for CRUD operations on notes")
@RestController
@Slf4j
@RequestMapping("/api/note")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @ApiOperation(value = "home application")
    @GetMapping("")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok().body("Greetings form NoteMS API !");
    }

    @ApiOperation(value = "Get all notes by Patient Id")
    @GetMapping("/all/{patientId}")
    public ResponseEntity<List<Note>> listNote(@PathVariable("patientId") Integer patientId) {
        List<Note> notes = service.findAllNotesByPatientId(patientId);
        log.debug("controller : get the list of all note by patientId");
        return ResponseEntity.ok().body(notes);
    }

    @ApiOperation(value = "Get note by id")
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable String id) {
        Note note = service.getNoteById(id);
        log.debug("controller : get note by id : " + id);
        return ResponseEntity.ok().body(note);
    }

    @ApiOperation(value = "Create new note")
    @PostMapping("/create")
    public ResponseEntity<Note> saveNote(@RequestBody @Valid Note note) {
        Note noteCreated = service.saveNote(note);
        log.debug("controller : create Note : " + note);
        return ResponseEntity.ok().body(noteCreated);
    }

    @ApiOperation(value = "Update note")
    @PutMapping("/update/{id}")
    public ResponseEntity<Note> updatePatient(@PathVariable String id, @RequestBody @Valid Note note) {
        Note noteUpdated = service.updateNote(id, note);
        log.debug("controller : update note : " + noteUpdated);
        return ResponseEntity.ok().body(noteUpdated);
    }

    @ApiOperation(value = "Delete note")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable String id) {
        service.deleteNote(id);
        log.debug("controller : delete note by id : " + id);
        return ResponseEntity.ok().body("Success, note are correctly delete");
    }
}
