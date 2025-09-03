package com.my.securityTest.controller;

import com.my.securityTest.dto.JoinDto;
import com.my.securityTest.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final JoinService joinService;

    @GetMapping("/login")
    public String login() {
        return "login";
        // securityConfig에 정의해 놓지 않으면 못들어감
        // login 페이지를 허용할지 말지 안 정해놓았기 때문
        // 로그인을 요청 -> security가 관여하도록 처리해야함
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(JoinDto joinDto) {
        joinService.joinProcess(joinDto);
        return "redirect:/login";
    }

    @GetMapping("/my/info")
    public String infoPage(Model model) {
        // 현재 로그인 된 사용자 정보를 확인
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        model.addAttribute("user", username);
        return "info";
    }
}
