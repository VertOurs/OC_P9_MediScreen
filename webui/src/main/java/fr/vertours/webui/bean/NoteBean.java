package fr.vertours.webui.bean;

import lombok.Data;

@Data
public class NoteBean {

    private String id;
    private Integer patientId;
    private String recommendation;
}
