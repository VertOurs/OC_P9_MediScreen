package fr.vertours.notems.repository;

import fr.vertours.notems.domain.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {

    List<Note> findAllNotesByPatientId(Integer patientId);
}
