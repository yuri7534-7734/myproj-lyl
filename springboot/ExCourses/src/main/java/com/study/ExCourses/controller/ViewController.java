package com.study.ExCourses.controller;

import com.study.ExCourses.entity.RegistrationEntity;
import com.study.ExCourses.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final RegistrationRepository registrationRepository;

    @GetMapping("/")
    public String main(Model model) {
        List<RegistrationEntity> registrations = registrationRepository.findAll();

        model.addAttribute("registrations", registrations);
        return "index";
    }
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }


}
