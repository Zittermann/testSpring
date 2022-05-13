package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


/**
 * Configuración Swagger para la generación de documentación de la API REST
 * localhost:8080/swagger-ui
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails()) // Esta API pertenece a esat empresa, accede con este link etcetctec
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiDetails() {

        return new ApiInfo("Spring Boot Book API REST",
                           "Library Api rest docs",
                           "1.0",
                           "https://www.google.com/",
                           new Contact("German",
                                       "https://github.com/Zittermann",
                                       "germanzittermann@gmail.com"),
                           "MIT",
                           "https://www.google.com/",
                           Collections.emptyList());
    }

}
