package com.nunegal.timeTracking.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimekeepingFormValidator.class)
@Documented
public @interface ValidTimekeepingForm {
		
    String message() default "Estos campos son obligatorios";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}