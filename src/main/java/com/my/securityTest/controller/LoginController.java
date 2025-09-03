package com.my.securityTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
        // securityConfig에 정의해 놓지 않으면 못들어감
        // login 페이지를 허용할지 말지 안 정해놓았기 때문
        // 로그인을 요청 -> security가 관여하도록 처리해야함
    }
}
