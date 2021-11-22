package com.jardapm.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.jardapm.domain.model.Project;
import com.jardapm.domain.repository.custom.interfaces.ProjectRepositoryCustomInterface;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, QuerydslPredicateExecutor<Project>, ProjectRepositoryCustomInterface {



}
