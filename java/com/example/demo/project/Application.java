package com.example.demo.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.project.entity.Course;
import com.example.demo.project.entity.Operator;
import com.example.demo.project.repository.CourseRepository;
import com.example.demo.project.repository.OperatorRepository;


@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner initData(CourseRepository courseRepo, OperatorRepository opRepo) {
        return args -> {
            if (courseRepo.count() == 0) {
                courseRepo.save(new Course(null, "Java Basics", "Intro to Java", 100));
                courseRepo.save(new Course(null, "Spring Boot", "Spring Boot hands-on", 200));
                courseRepo.save(new Course(null, "Thymeleaf", "Templating with Thymeleaf", 80));
            }
            if (opRepo.count() == 0) {
                opRepo.save(new Operator(null, "Aibek", "K.", "IT"));
                opRepo.save(new Operator(null, "Dinara", "B.", "Marketing"));
                opRepo.save(new Operator(null, "Samat", "T.", "Support"));
            }
        };
    }
}
