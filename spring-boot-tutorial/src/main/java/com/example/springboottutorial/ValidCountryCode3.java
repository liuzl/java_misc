package com.example.springboottutorial;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = CountryCode3ConstraintValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidCountryCode3 {

    String message() default "Invalid country code of ISO 3166-1 Alpha-3";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
