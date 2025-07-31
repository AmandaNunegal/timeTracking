package com.nunegal.timeTracking.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nunegal.timeTracking.dtos.TimekeepingDto;
import com.nunegal.timeTracking.entity.Timekeeping;

@Mapper(componentModel = "spring")
public interface TimekeepingMapper {

	Timekeeping toTimekeeping(TimekeepingDto timekeepingDto);
	
	TimekeepingDto toTimekeepingDto(Timekeeping timekeeping);
	
	List<Timekeeping> toListTimekeepings(List<TimekeepingDto> timekeepingsDto);
	
	List<TimekeepingDto> toListTimekeepingsDto(List<Timekeeping> timekeepings);
	
}
