package com.EventRegistrationApplication;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"api", "service", "utility", "dto", "exception", "config"})
@EntityScan(basePackages = "entity")
@EnableJpaRepositories(basePackages = "repository")
public class eventRegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(eventRegistrationApplication.class, args);
    }
}

