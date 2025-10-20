package com.example.demo.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.project.entity.Course;
import com.example.demo.project.repository.CourseRepository;

@Service
public class CourseService {
    private final CourseRepository repo;
    public CourseService(CourseRepository repo) { this.repo = repo; }
    public List<Course> findAll(){ return repo.findAll(); }
    public Course findById(Long id){ return repo.findById(id).orElse(null); }
}
