package com.example.demo.project.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.project.entity.ApplicationRequest;
import com.example.demo.project.entity.Operator;
import com.example.demo.project.repository.ApplicationRequestRepository;


@Service
public class ApplicationRequestService {
    private final ApplicationRequestRepository repo;
    private final OperatorService operatorService;

    public ApplicationRequestService(ApplicationRequestRepository repo, OperatorService operatorService) {
        this.repo = repo; this.operatorService = operatorService;
    }

    public List<ApplicationRequest> findAll(){ return repo.findAll(); }
    public ApplicationRequest findById(Long id){ return repo.findById(id).orElse(null); }
    public ApplicationRequest save(ApplicationRequest r){ return repo.save(r); }

    @Transactional
    public ApplicationRequest assignOperators(Long requestId, Set<Long> operatorIds) {
        ApplicationRequest r = findById(requestId);
        if (r == null) return null;
        if (r.isHandled()) return r;

        Set<Operator> ops = operatorIds.stream()
                .map(operatorService::findById)
                .filter(o -> o != null)
                .collect(Collectors.toSet());

        r.getOperators().addAll(ops);
        r.setHandled(true);
        return repo.save(r);
    }

    @Transactional
    public ApplicationRequest removeOperator(Long requestId, Long operatorId) {
        ApplicationRequest r = findById(requestId);
        if (r == null) return null;
        r.getOperators().removeIf(o -> o.getId().equals(operatorId));
        return repo.save(r);
    }
}
