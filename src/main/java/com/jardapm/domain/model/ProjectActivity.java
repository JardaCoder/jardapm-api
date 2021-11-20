package com.jardapm.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(indexes = @Index(columnList = "situation"))
public class ProjectActivity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	
	@NotBlank
	@Column(nullable = false)
	private String name;
	
	@NotNull
	@Column(nullable = false)
	private LocalDate initialDate;
	
	@NotNull
	@Column(nullable = false)
	private LocalDate finalDate;
	
	@JoinColumn(name = "project_id", updatable = false, insertable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Project project;
	
	@NotNull
	@Column(name = "project_id", nullable = false)
	private Long projectId;

}
