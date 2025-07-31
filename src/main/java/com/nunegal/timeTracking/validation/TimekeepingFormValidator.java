package com.nunegal.timeTracking.validation;

import com.nunegal.timeTracking.dtos.TimekeepingDto;
import com.nunegal.timeTracking.dtos.TimekeepingFormDto;
import com.nunegal.timeTracking.enums.TypeTimekeeping;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TimekeepingFormValidator implements ConstraintValidator<ValidTimekeepingForm, TimekeepingFormDto> {

	@Override
	public boolean isValid(TimekeepingFormDto formTk, ConstraintValidatorContext context) {
		boolean valid = true;

		if (formTk == null || formTk.getTimekeepings() == null) {
			return true;
		}

		for (int i = 0; i < formTk.getTimekeepings().size(); i++) {
			TimekeepingDto tk = formTk.getTimekeepings().get(i);
			if ((tk.getType() == TypeTimekeeping.ENTRADA_1 || tk.getType() == TypeTimekeeping.SALIDA_1)
					&& tk.getDateTime() == null) {
				valid = false;

				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("Este campo es obligatorio")
						.addPropertyNode("timekeepings")
						.inIterable().atIndex(i)
						.addPropertyNode("dateTime")
						.addConstraintViolation();
			}
		}
		return valid;
	}
}
