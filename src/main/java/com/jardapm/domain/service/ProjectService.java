package com.jardapm.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jardapm.domain.dto.ProjectDetailsDto;
import com.jardapm.domain.dto.ProjectDto;
import com.jardapm.domain.enums.Situation;
import com.jardapm.domain.exception.BadRequestException;
import com.jardapm.domain.exception.EntityInUseException;
import com.jardapm.domain.exception.EntityNotFoundException;
import com.jardapm.domain.model.Project;
import com.jardapm.domain.model.QProject;
import com.jardapm.domain.repository.ProjectRepository;
import com.jardapm.domain.util.Util;

@Service
public class ProjectService {
	
	
	@Autowired 
	ProjectRepository projectRepository;
	@Autowired 
	ProjectActivityService projectActivityService;
	
	QProject qProject = QProject.project;
	
	public Project saveOrUpadte(Project project) {
		Util.setInclusionData(project);
		return projectRepository.save(project);
	}
	
	public Optional<Project> findById(Long id) {
		return projectRepository.findById(id);
	}
	
	public List<Project> findAll() {
		return projectRepository.findAll();
	}
	
	public void deleteProjectById(Long id) {
		try {
			projectRepository.deleteById(id);
			
		}catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format(
					"O projeto de código %d não existe.", id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(
					"O projeto de código %d não pode ser removido por que está em uso.", id));
		}
	}
	
	public void disableById(Long id) {
		projectRepository.findById(id).ifPresentOrElse((project) ->{
			project.setSituation(Situation.INACTIVE);
			saveOrUpadte(project);
			
		}, () ->{
			
			new BadRequestException("Não existe um projeto com o id informado.");
		});
	}

	public boolean existsById(Long projectId) {
		return projectRepository.existsById(projectId);
	}

	public List<ProjectDto> getProjectList() {
		return projectRepository.listProjects(null);
	}

	public ProjectDetailsDto getProjectAndActivitiesById(Long projectId) {
		
		ProjectDetailsDto projectDetailsDto = new ProjectDetailsDto();
		Project project = findById(projectId)
				.orElseThrow(() -> new EntityNotFoundException(String.format("Projeto de código %d não existe" , projectId)));
		
		projectDetailsDto.setProject(project);
		projectDetailsDto.setActivities(projectActivityService.findAllByProjectId(projectId));
		
		return projectDetailsDto;
	}
}
