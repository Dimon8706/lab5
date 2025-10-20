package com.example.demo.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.requests.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
