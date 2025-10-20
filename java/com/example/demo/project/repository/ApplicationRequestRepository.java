package com.example.demo.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.requests.entity.ApplicationRequest;

public interface ApplicationRequestRepository extends JpaRepository<ApplicationRequest, Long> {
}
