package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {
	// esto SIMEMPRE ES ASÍ PREPARA EL RESTTEMPLATE PARA
	// ACCEDER A UNA API EXTERNA. Por defecto no viene cargado.
	@Bean
	public RestTemplate getresttemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
