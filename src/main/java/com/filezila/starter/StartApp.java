package com.filezila.starter;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.filezila.service.AmazonClientService;


@SpringBootApplication
@EntityScan("com.filezila.model")
@EnableJpaRepositories("com.filezila.dao")
@ComponentScan(basePackages = { "com.filezila.controller", "com.filezila.service", "com.filezila.config"})
public class StartApp extends SpringBootServletInitializer{

	@Resource
	AmazonClientService amazonClientService;
	
	public static void main(String[] args) {
		
		SpringApplication.run(StartApp.class, args);

	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StartApp.class);
	}

}
