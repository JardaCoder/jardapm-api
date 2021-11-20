package com.jardapm.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.jardapm.domain.model.ProjectActivity;

@Repository
public interface ProjectActivityRepository extends JpaRepository<ProjectActivity, Long>, QuerydslPredicateExecutor<ProjectActivity> {



}
