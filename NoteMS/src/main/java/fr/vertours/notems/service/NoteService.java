package fr.vertours.notems.service;

import fr.vertours.notems.domain.Note;

import java.util.List;

public interface NoteService {
    List<Note> findAllNotesByPatientId(Integer patientId);

    Note saveNote(Note note);

    Note getNoteById(String id);

    Note updateNote(String id, Note note);

}
