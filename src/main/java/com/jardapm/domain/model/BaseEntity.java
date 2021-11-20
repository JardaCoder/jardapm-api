package com.jardapm.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;

import com.jardapm.domain.enums.Situation;

import lombok.Data;

@Data
@MappedSuperclass
@FilterDef(name = "situationNeE", defaultCondition="situation <> 'INACTIVE'")
@Filter(name="situationNeE")
public class BaseEntity implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private LocalDate inclusionDate; 
	
	@Column
	private LocalDateTime lastModification; 
	
	@Column
	@Enumerated(EnumType.STRING)
	private Situation situation = Situation.ACTIVE;

}
