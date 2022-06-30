package fr.vertours.assessms.Bean;

import lombok.Data;

@Data
public class NoteBean {

    private String id;
    private Integer patientId;
    private String recommendation;
}
