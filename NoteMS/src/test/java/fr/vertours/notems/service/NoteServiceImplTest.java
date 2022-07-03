package fr.vertours.notems.service;

import fr.vertours.notems.domain.Note;
import fr.vertours.notems.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class NoteServiceImplTest {

    NoteRepository repository = mock(NoteRepository.class);

    NoteServiceImpl classUnderTest = new NoteServiceImpl(repository);

    static Note getExpectedNote(){
        Note expectedNote = new Note();
        expectedNote.setId("idTest");
        expectedNote.setPatientId(1);
        expectedNote.setRecommendation("recommendation de test");
        return expectedNote;
    }
    static Note getUpdateNote(){
        Note expectedNote = new Note();
        expectedNote.setId("idTest");
        expectedNote.setPatientId(1);
        expectedNote.setRecommendation("recommendation de test Updated");
        return expectedNote;
    }

    @Test
    void findAllNotesByPatientId() {
        List<Note> notes = new ArrayList<>();
        when(repository.findAllNotesByPatientId(1)).thenReturn(notes);

        classUnderTest.findAllNotesByPatientId(1);

        verify(repository, times(1)).findAllNotesByPatientId(1);
    }

    @Test
    void getNoteById() {
        Note expectedNote = getExpectedNote();
        when(repository.findById("idTest")).thenReturn(java.util.Optional.of(expectedNote));

        Note actualNote = classUnderTest.getNoteById("idTest");

        assertEquals(expectedNote.getId(), actualNote.getId());
    }

    @Test
    void saveNote() {
        Note expectedNote = getExpectedNote();
        when(repository.save(expectedNote)).thenReturn(expectedNote);

        Note actualNote = classUnderTest.saveNote(expectedNote);

        assertEquals(expectedNote, actualNote);
    }

    @Test
    void updateNote() {
        Note existingNote = getExpectedNote();
        Note updateNote = getUpdateNote();
        when(repository.findById("idTest")).thenReturn(java.util.Optional.of(existingNote));
        when(repository.save(updateNote)).thenReturn(updateNote);

        Note actualNote = classUnderTest.updateNote("idTest", updateNote);

        assertEquals(updateNote.getRecommendation(),actualNote.getRecommendation());

    }
}