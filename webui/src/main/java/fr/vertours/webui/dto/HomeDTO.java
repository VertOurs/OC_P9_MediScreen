package fr.vertours.webui.dto;


import fr.vertours.assessms.model.Assessment;
import fr.vertours.patientms.model.Patient;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HomeDTO {

    private List<Patient> patients = new ArrayList<>();
    private Patient patient;
    private Assessment assess;
    private boolean hasAssess;

    public boolean isHasAssess() {
        return assess != null;
    }
}
