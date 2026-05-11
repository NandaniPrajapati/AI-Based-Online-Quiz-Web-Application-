package com.AIQuizApp.Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
@EnableJpaRepositories("QuizRepository")
@ComponentScan({"QuizRepository", "QuizService","QuizController", "Config", "JWT","DTO"})
@EntityScan(basePackages = "Model")
//@CrossOrigin(origins = "http://localhost:5173")
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);

		System.out.println("hello java ");

	}




}
