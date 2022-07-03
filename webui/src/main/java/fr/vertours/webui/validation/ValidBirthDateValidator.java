package fr.vertours.webui.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidBirthDateValidator implements ConstraintValidator<ValidBirthDate, String> {

    @Override
    public boolean isValid(String dateOfBirth, ConstraintValidatorContext context) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(dateOfBirth, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
