package com.example.demo.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.requests.entity.Operator;

public interface OperatorRepository extends JpaRepository<Operator, Long> {
}
