package fr.vertours.webui.dto;

import lombok.Data;

@Data
public class ResultDTO {

    private long patientId;
    private String firstName;
    private String lastName;
    private String assess;

    public ResultDTO(long patientId, String firstName, String lastName, String assess) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.assess = assess;
    }
}
