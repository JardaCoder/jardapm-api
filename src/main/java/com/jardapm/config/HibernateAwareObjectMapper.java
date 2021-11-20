package com.jardapm.config;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module.Feature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class HibernateAwareObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;


	@Bean
	@Primary
	public ObjectMapper objectMapper() {
    	Hibernate5Module hibernate5Module = new Hibernate5Module();
    	hibernate5Module.disable(Feature.USE_TRANSIENT_ANNOTATION);
    	
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                .indentOutput(true)
                .timeZone(TimeZone.getTimeZone("America/Sao_Paulo"))
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd"))
                .modules(hibernate5Module, new JavaTimeModule());
        
        return builder.build();
	}

	
}
