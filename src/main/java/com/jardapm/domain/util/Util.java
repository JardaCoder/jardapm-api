package com.jardapm.domain.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.jardapm.domain.dto.PagingParametersDto;
import com.jardapm.domain.enums.Situation;
import com.jardapm.domain.model.BaseEntity;

public class Util {
	
	public static void setInclusionData(BaseEntity entity) {
		
		if(entity.getId() == null) {
			entity.setInclusionDate(LocalDate.now());
			entity.setSituation(Situation.ACTIVE);
		}
		entity.setLastModification(LocalDateTime.now());
	}
	
	public static PageRequest setPagingParameters(PagingParametersDto parameters) {
		
		PageRequest pageRequest = PageRequest.of(
				parameters.getPage(),
				parameters.getSize(),
				Sort.by(Direction.fromString(parameters.getSortDirection()), parameters.getSortField())
			);
		
		return pageRequest;
	}
	
	public static Sort setSortParameters(PagingParametersDto parameters) {
		return Sort.by(Direction.fromString(parameters.getSortDirection()), parameters.getSortField());
	}
	

}
