package fr.vertours.webui.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidBirthDateValidator.class)
public @interface ValidBirthDate {

    String message() default "Please enter a birth Date that follows the \"yyyy-mm-dd\" pattern's";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
