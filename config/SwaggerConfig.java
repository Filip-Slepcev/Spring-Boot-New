package com.springboot.blog.springbootblogrestapi.config;

import io.swagger.v3.core.model.ApiDescription;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    private Info info;

    private Info info(){
        Contact contact = new Contact();
        contact.setName("Ramesh Fadatare");
        contact.setUrl("www.javaguides.net");
        contact.setEmail("ramesh@gmail.com");

        License license = new License();
        license.setName("License of API");
        license.setUrl("API license URL");

        Info tempInfo = new Info();
        tempInfo.setTitle("Spring Boot Blog REST APIs");
        tempInfo.setDescription("Spring Boot Blog REST API Documentation");
        tempInfo.setVersion("2");
        tempInfo.setTermsOfService("Terms of service");
        tempInfo.setContact(contact);
        tempInfo.setLicense(license);

        return tempInfo;
    }
}
