package com.jardapm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityWebConfiguration extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	PasswordEncoder encoder;
	
	 @Override
	  protected void configure(HttpSecurity http) throws Exception {
	
		 CharacterEncodingFilter filter = new CharacterEncodingFilter();
			filter.setEncoding("UTF-8");
			filter.setForceEncoding(true);
			http.addFilterBefore(filter,CsrfFilter.class);

			http.cors().disable()
	    .csrf().disable()
	    .authorizeRequests()
	    .antMatchers("/**").permitAll()
	    .anyRequest().authenticated()
	    .and()
	    .httpBasic();
	  }
	
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
    	auth
          .inMemoryAuthentication()
          .withUser("user")
          .password(encoder.encode("password"))
          .roles("USER")
          .and()
          .withUser("admin")
          .password(encoder.encode("admin"))
          .roles("USER", "ADMIN");
    }

}
