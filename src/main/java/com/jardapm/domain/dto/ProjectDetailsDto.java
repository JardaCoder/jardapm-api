package com.jardapm.domain.dto;

import java.util.List;

import com.jardapm.domain.model.Project;
import com.jardapm.domain.model.ProjectActivity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDetailsDto {
	
	
	private Project project;
	
	private List<ProjectActivity> activities;

}
