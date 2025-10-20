package com.example.demo.project.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.project.entity.ApplicationRequest;
import com.example.demo.project.entity.Course;
import com.example.demo.project.service.ApplicationRequestService;
import com.example.demo.project.service.CourseService;
import com.example.demo.project.service.OperatorService;


@Controller
@RequestMapping("/requests")
public class RequestController {
    private final ApplicationRequestService reqService;
    private final CourseService courseService;
    private final OperatorService operatorService;

    public RequestController(ApplicationRequestService reqService, CourseService courseService, OperatorService operatorService) {
        this.reqService = reqService; this.courseService = courseService; this.operatorService = operatorService;
    }

    @GetMapping
    public String list(Model m){
        List<ApplicationRequest> list = reqService.findAll();
        m.addAttribute("requests", list);
        return "requests/list";
    }

    @GetMapping("/new")
    public String createForm(Model m){
        m.addAttribute("request", new ApplicationRequest());
        m.addAttribute("courses", courseService.findAll());
        return "requests/new";
    }

    @PostMapping
    public String create(@ModelAttribute ApplicationRequest request, @RequestParam Long courseId){
        Course c = courseService.findById(courseId);
        request.setCourse(c);
        request.setHandled(false);
        reqService.save(request);
        return "redirect:/requests";
    }

    @GetMapping("/{id}/assign")
    public String assignForm(@PathVariable Long id, Model m){
        ApplicationRequest r = reqService.findById(id);
        if (r == null) return "redirect:/requests";
        m.addAttribute("request", r);
        m.addAttribute("operators", operatorService.findAll());
        return "requests/assign";
    }

    @PostMapping("/{id}/assign")
    public String assign(@PathVariable Long id, @RequestParam(required = false, name = "operatorIds") List<Long> operatorIds){
        if (operatorIds == null) operatorIds = List.of();
        Set<Long> set = new HashSet<>(operatorIds);
        reqService.assignOperators(id, set);
        return "redirect:/requests";
    }

    @PostMapping("/{id}/remove-operator")
    public String removeOperator(@PathVariable Long id, @RequestParam Long operatorId){
        reqService.removeOperator(id, operatorId);
        return "redirect:/requests/" + id + "/assign";
    }
}
