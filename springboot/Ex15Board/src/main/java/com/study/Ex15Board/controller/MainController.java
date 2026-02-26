package com.study.Ex15Board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 처음 진입 / 홈
// 메인 페이지를 렌더링
@Controller
public class MainController {
    @GetMapping("/")
    public String main(){
        return "redirect:/board/";
    }
}
