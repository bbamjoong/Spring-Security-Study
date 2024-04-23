package com.example.testsecurity.controller;

import com.example.testsecurity.dto.JoinDTO;
import com.example.testsecurity.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor // 생성자 주입
public class JoinController {

    private final JoinService joinService; // RequiredArgsConstructor 어노테이션을 통해 의존성을 생성자 주입

    // 회원가입의 경우 별도의 로직이 필요하지 않으니 GetMapping, PostMapping을 이용.
    @GetMapping("/join")
    public String joinP() {

        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO) {
        joinService.joinProcess(joinDTO);

        return "redirect:/login";
    }
}
