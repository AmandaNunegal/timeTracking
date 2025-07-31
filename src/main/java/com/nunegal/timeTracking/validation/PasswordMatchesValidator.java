package com.nunegal.timeTracking.validation;

import com.nunegal.timeTracking.dtos.EmployeeMoreDto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, EmployeeMoreDto> {

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {

	}

	@Override
	public boolean isValid(EmployeeMoreDto dto, ConstraintValidatorContext context) {
		if (dto.getPassword() == null || dto.getPassword2() == null) {
			return false;
		}

		boolean valid = dto.getPassword().equals(dto.getPassword2());

		if (!valid) {

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Las contraseñas no coinciden").addPropertyNode("password2")
					.addConstraintViolation();
		}

		return valid;
	}
}
