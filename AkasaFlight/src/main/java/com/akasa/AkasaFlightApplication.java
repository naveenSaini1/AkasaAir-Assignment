package com.akasa;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AkasaFlightApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkasaFlightApplication.class, args);
	}
	@Bean
    Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .build()
                .apiInfo(apiDetails());
    }
	
	public ApiInfo apiDetails() {
		return new ApiInfo(
				"Online Flight Booking API",
				"Online Flight Booking Api where we can book the flight",
				"pm",
				"Free to use",
				new springfox.documentation.service.Contact("PostMeal Team", "Pstmeal.io", "naveensaini94766@gmail.com"),
				"API license",
				"http://naveen.com",
				Collections.emptyList()
				);
		
	}

}
