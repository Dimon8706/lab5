package com.example.demo.project.controller;

import com.example.demo.project.entity.Operator;
import com.example.demo.project.service.OperatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/operators")
public class OperatorController {

    private final OperatorService service;

    public OperatorController(OperatorService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("operators", service.findAll());
        return "operators/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("operator", new Operator());
        return "operators/new";
    }

    @PostMapping
    public String create(@ModelAttribute Operator operator) {
        service.save(operator);
        return "redirect:/operators";
    }
}
