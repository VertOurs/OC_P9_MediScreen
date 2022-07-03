package fr.vertours.webui.dto;

import fr.vertours.webui.bean.NoteBean;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NoteDTO {

    private String id;
    private long patientId;

    @NotBlank(message = "recommendation must not be empty")
    private String recommendation;

    public NoteDTO() {
    }

    public static NoteBean convertToBBean(NoteDTO dto) {
        NoteBean bean = new NoteBean();
        bean.setId(dto.getId());
        bean.setPatientId((int) dto.getPatientId());
        bean.setRecommendation(dto.getRecommendation());

        return bean;
    }
}
