package com.study.Ex07Thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String main() {
        return "index";
        // 타임리프를 사용할 경우 파일 이름만 적는다.
        // 스프링의 ViewResolver가 templates/ + 파일이름 + .html 형태로 찾아준다.
        // 따라서 확장자를 따로 쓰지 않는 것이 일반적이다.
    }
    @GetMapping("/index1")
    //Model 클래스 : 스프링 MVC모델에서 데이터를 전달하는 용도의 클래스
    //              내부적으로 Map(key-value)데이터구조이다.
    //매개변수로 선언하면, 스프링에서 주입(new)이 된다.
    //Model 객체에 KV를 넣으면, 타임리프에서 가져다 사용한다.
    public String index1(Model model) {
        model.addAttribute("name_text","홍길동");
        model.addAttribute("name_html","<ins>홍길동</ins>");
        model.addAttribute("server_value","HONG");
        return "index";
    }

    @GetMapping("/index2")
    public String index2(Model model){
        model.addAttribute("address_null",null);
        return "index2";
    }

}//class
