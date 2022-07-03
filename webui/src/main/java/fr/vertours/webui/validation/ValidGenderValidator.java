package fr.vertours.webui.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ValidGenderValidator implements ConstraintValidator<ValidGender, String> {

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext context) {
        String regex = "^[M|F]$";
        Pattern pattern = Pattern.compile(regex);

        if (pattern.matcher(gender).matches()) {
            return true;
        }
        return false;
    }
}
