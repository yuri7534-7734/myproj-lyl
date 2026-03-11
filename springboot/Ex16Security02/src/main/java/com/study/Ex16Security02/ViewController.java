package com.study.Ex16Security02;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    //인증되지 않은 사용자여도, 로그인, 회원가입페이지로 접근 가능해야함

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm"; //시큐리티 적용후에는 권한이 없어서 안된다.
    }
}
