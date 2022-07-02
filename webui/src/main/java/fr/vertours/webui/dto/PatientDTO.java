package fr.vertours.webui.dto;

import fr.vertours.webui.bean.PatientBean;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class PatientDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String address;
    private String phone;

    public PatientDTO() {
    }

    public PatientDTO(long id, String firstName, String lastName, String dateOfBirth, String gender, String address, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }

    public static PatientBean converterToBean(PatientDTO dto) {
        LocalDate date = LocalDate.parse(dto.dateOfBirth);
        char gender = dto.getGender().charAt(0);
        return new PatientBean(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                date,
                gender,
                dto.getAddress(),
                dto.getPhone()
                );
    }
    public static PatientDTO converterToDTO(PatientBean bean) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return new PatientDTO(
                bean.getId(),
                bean.getFirstName(),
                bean.getLastName(),
                bean.getDateOfBirth().format(formatter),
                String.valueOf(bean.getGender()),
                bean.getAddress(),
                bean.getPhone()
        );
    }
}
