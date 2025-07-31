package com.nunegal.timeTracking.dtos;

import java.util.ArrayList;
import java.util.List;

import com.nunegal.timeTracking.validation.ValidTimekeepingForm;

import lombok.Data;

@Data
@ValidTimekeepingForm
public class TimekeepingFormDto {

	private List<TimekeepingDto> timekeepings = new ArrayList<>();

	
}
