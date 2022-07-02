package fr.vertours.webui.dto;




import fr.vertours.webui.bean.PatientBean;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class HomeDTO {

    private List<PatientBean> patients = new ArrayList<>();
    private Long patientId;
    private LocalDate dateOfBirth;
    private char gender;
    private String address;
    private String phone;

}
