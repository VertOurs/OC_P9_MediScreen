package fr.vertours.webui.dto;

import fr.vertours.webui.bean.NoteBean;
import lombok.Data;

import java.util.List;

@Data
public class NotePageDTO {

    private long patientId;
    private String firstName;
    private String lastName;
    private List<NoteBean> notes;

    public NotePageDTO(long patientId, String firstName,
                       String lastName,
                       List<NoteBean> notes) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.notes = notes;
    }
}
