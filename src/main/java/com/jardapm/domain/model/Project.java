package com.jardapm.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(indexes = @Index(columnList = "situation"))
public class Project extends BaseEntity {

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

}
