package com.eniso.tama;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class TamaApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {
		SpringApplication.run(TamaApplication.class, args);

	}
	
	@Override
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	      return application.sources(TamaApplication.class);
	   }

}
