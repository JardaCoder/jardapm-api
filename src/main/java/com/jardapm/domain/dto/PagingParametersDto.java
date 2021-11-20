package com.jardapm.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingParametersDto {
	
	private Integer page;
	private Integer size;
	private String sortField;
	private String sortDirection;
	private boolean buscarTodos;
	
	public PagingParametersDto() {
		this.setPage(0);
		this.setSize(10);
		this.setSortField("id");
		this.setSortDirection("ASC");
	}

}
