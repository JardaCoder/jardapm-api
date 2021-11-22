package com.jardapm.domain.repository.custom.interfaces;

import java.util.List;

import com.jardapm.domain.dto.ProjectDto;
import com.querydsl.core.types.dsl.BooleanExpression;

public interface ProjectRepositoryCustomInterface {
	
	List<ProjectDto> listProjects(BooleanExpression booleanExpression);

}
