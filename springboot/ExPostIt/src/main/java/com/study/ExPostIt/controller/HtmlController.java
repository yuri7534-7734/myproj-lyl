package com.study.ExPostIt.controller;


import com.study.ExPostIt.PostItRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


//전체 흐름 구조
// 브라우저 (index.html)
// - HTTP 요청 / 응답 (JSON)
// RestController
// - Java 메서드 호출
// PostItRepository
// - SQL 쿼리 자동 생성
// MySQL DB (notes 테이블)

@Controller
@RequiredArgsConstructor
public class HtmlController {
    private final PostItRepository postItRepository;


    @GetMapping("/")
    public String main(){
        return "index";
    }


}
