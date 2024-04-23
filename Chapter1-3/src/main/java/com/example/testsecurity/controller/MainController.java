package com.example.testsecurity.controller;


import com.example.testsecurity.service.ViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ViewService viewService;

    @GetMapping("/")
    public String mainP(Model model) {
        String name = viewService.getSessionUsername();
        String role = viewService.getSessionRole();
        model.addAttribute("name", name);
        model.addAttribute("role", role);
        return "main";
    }
}
