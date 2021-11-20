package com.jardapm.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class InactiveFilterConfig {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Before("execution(* com.jardapm.*.domain.repository.*.*(..))")
	public void habilitarFiltro(){
		filter();
	}

	@Before("execution(* com.jardapm.*.*.repository.*.*(..))")
	public void habilitarFiltroProjetoFinal(){
		filter();
	}	
	
	@Before("execution(* com.jardapm.domain.service.*.*(..))")
	public void habilitarFiltroServiceProjetoFinal(){
		this.filter();
	}		
	
	private void filter() {
		manager.unwrap(Session.class).enableFilter("situationNeE");
	}
}
