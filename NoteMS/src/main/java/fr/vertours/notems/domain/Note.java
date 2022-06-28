package fr.vertours.notems.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "notes")
public class Note {

    @Id
    private String id;

    private Integer patientId;

    private String recommendation;
}
