package fr.vertours.notems.service;

import fr.vertours.notems.domain.Note;
import fr.vertours.notems.exception.NoteDoesNotExistException;
import fr.vertours.notems.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;

    public NoteServiceImpl(NoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Note> findAllNotesByPatientId(Integer patientId) {
        log.debug("service : get the list of all notes by PatientId");
        return repository.findAllNotesByPatientId(patientId);
    }

    @Override
    public Note getNoteById(String id) {
        log.debug("service : get note by id : " + id);
        return getById(id);
    }

    @Override
    public Note saveNote(Note note) {
        Note noteCreated = repository.save(note);
        log.debug("service : create patient : " + note);
        return noteCreated;
    }

    @Override
    public Note updateNote(String id, Note updateNote) {
        Note dBNote = getNoteById(id);
        dBNote.setPatientId(updateNote.getPatientId());
        dBNote.setRecommendation(updateNote.getRecommendation());
        log.debug("service : update note : " + updateNote);
        return repository.save(dBNote);
    }

    private Note getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoteDoesNotExistException(id));
    }
}
