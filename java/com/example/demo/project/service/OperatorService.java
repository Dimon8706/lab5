package com.example.demo.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.project.entity.Operator;
import com.example.demo.project.repository.OperatorRepository;

@Service
public class OperatorService {
    private final OperatorRepository repo;
    public OperatorService(OperatorRepository repo) { this.repo = repo; }
    public List<Operator> findAll(){ return repo.findAll(); }
    public Operator findById(Long id){ return repo.findById(id).orElse(null); }
    public Operator save(Operator o){ return repo.save(o); }
}
