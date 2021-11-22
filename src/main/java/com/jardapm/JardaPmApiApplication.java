package com.jardapm;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import io.imagekit.sdk.utils.Utils;

@SpringBootApplication
@org.springframework.context.annotation.Configuration
@EnableScheduling
@EnableAsync
@EnableAutoConfiguration
public class JardaPmApiApplication {
	
	@PostConstruct
	public void init() throws IOException {
		ImageKit imageKit = ImageKit.getInstance();
	    Configuration config = Utils.getSystemConfig(JardaPmApiApplication.class);
	    imageKit.setConfig(config);
	}

	public static void main(String[] args){
		SpringApplication.run(JardaPmApiApplication.class, args);
		
	}

}
