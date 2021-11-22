package com.jardapm.domain.dto;

import java.time.LocalDate;
import java.util.List;

import com.jardapm.domain.enums.YesNo;
import com.jardapm.domain.model.ProjectActivity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
	
	private String name;
	
	private Long id;
	
	private LocalDate initialDate;
	
	private LocalDate finalDate;
	
	private Double percentageProgress = (double) 0;
	
	List<ProjectActivity> projectActivities;
	
	private YesNo finished;
	
	private YesNo late = YesNo.NO;
	
	private Long totalActivities;
	
	
	public ProjectDto(String name, Long id, LocalDate initialDate, LocalDate finalDate) {
		super();
		this.name = name;
		this.id = id;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		
	}

}
