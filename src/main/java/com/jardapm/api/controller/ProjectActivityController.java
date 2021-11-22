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
import com.jardapm.domain.model.ProjectActivity;
import com.jardapm.domain.service.ProjectActivityService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/activity")
public class ProjectActivityController {
	
	
	ProjectActivityService projectActivityService;
	
	@PostMapping
	public ResponseEntity<ProjectActivity> createProjectActivity(@Valid @RequestBody ProjectActivity project){
		return ResponseEntity.status(HttpStatus.CREATED).body(projectActivityService.saveOrUpadte(project));
	}
	
	@PutMapping("/update")
	public ResponseEntity<ProjectActivity> updateProjectActivity(@Valid @RequestBody ProjectActivity project){
		return ResponseEntity.ok().body(projectActivityService.saveOrUpadte(project));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ProjectActivity>> findAll(){
		return ResponseEntity.ok().body(projectActivityService.findAll());
	}	
	
	@GetMapping("/{id}")
	public ResponseEntity<ProjectActivity> findById(@PathVariable Long id){	
		ProjectActivity project  = projectActivityService.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format("Projeto de id %d não existe.", id) ));
		
		return ResponseEntity.ok().body(project);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		projectActivityService.disableById(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/permanent/{id}")
	public ResponseEntity<Void> deletePermanentById(@PathVariable Long id){
		projectActivityService.deleteProjectActivityById(id);
		return ResponseEntity.noContent().build();
	}
	
	
}