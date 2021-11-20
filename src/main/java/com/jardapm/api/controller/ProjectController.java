package com.jardapm.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jardapm.domain.exception.EntityNotFoundException;
import com.jardapm.domain.model.Project;
import com.jardapm.domain.service.ProjectService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/project")
public class ProjectController {
	
	
	ProjectService projectService;
	
	@PostMapping
	public ResponseEntity<Project> createProject(@Valid @RequestBody Project project){
		return ResponseEntity.status(HttpStatus.CREATED).body(projectService.saveOrUpadte(project));
	}
	
	@PutMapping("/update")
	public ResponseEntity<Project> updateProject(@Valid @RequestBody Project project){
		return ResponseEntity.ok().body(projectService.saveOrUpadte(project));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Project>> findAll(){
		return ResponseEntity.ok().body(projectService.findAll());
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<Project> findById(@PathVariable Long id){	
		Project project  = projectService.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format("Projeto de id %d não existe.", id) ));
		
		return ResponseEntity.ok().body(project);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		projectService.deleteProjectById(id);
		return ResponseEntity.noContent().build();
	}
	
	
}