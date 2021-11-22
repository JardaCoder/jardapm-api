package com.jardapm.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jardapm.domain.enums.Situation;
import com.jardapm.domain.exception.BadRequestException;
import com.jardapm.domain.exception.EntityInUseException;
import com.jardapm.domain.exception.EntityNotFoundException;
import com.jardapm.domain.model.ProjectActivity;
import com.jardapm.domain.model.QProjectActivity;
import com.jardapm.domain.repository.ProjectActivityRepository;
import com.jardapm.domain.util.Util;

@Service
public class ProjectActivityService {
	
	
	@Autowired 
	ProjectActivityRepository projectActivitRepository;
	@Autowired 
	ProjectService projectService;
	
	QProjectActivity qProjectActivity = QProjectActivity.projectActivity;
	
	public ProjectActivity saveOrUpadte(ProjectActivity projectActivity) {
		Util.setInclusionData(projectActivity);
		
		if(!projectService.existsById(projectActivity.getProjectId())) {
			throw new EntityNotFoundException(String.format("O projeto de código %d não existe", projectActivity.getProjectId()));
		}
		
		return projectActivitRepository.save(projectActivity);
	}
	
	public Optional<ProjectActivity> findById(Long id) {
		return projectActivitRepository.findById(id);
	}
	
	public List<ProjectActivity> findAll() {
		return projectActivitRepository.findAll();
	}
	
	public void deleteProjectActivityById(Long id) {
		try {
			projectActivitRepository.deleteById(id);
			
		}catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format(
					"A atividade de código %d não existe.", id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(
					"A atividade de código %d não pode ser removido por que está em uso.", id));
		}
	}
	
	public void disableById(Long id) {
		projectActivitRepository.findById(id).ifPresentOrElse((project) ->{
			project.setSituation(Situation.INACTIVE);
			saveOrUpadte(project);
			
		}, () ->{
			
			new BadRequestException("Não existe um projeto com o id informado.");
		});
	}

	public List<ProjectActivity> findAllByProjectId(Long projectId) {
		return projectActivitRepository.findAllByProjectId(projectId);
	}
}
