package com.study.Ex06StaticWeb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//Request Mapping : HTTP 요청에 대한 경로와 응답 메소드를 정의한다.
//@GetMapping : 사용자의 HTTP Get요청에 대한 응답 경로와 메서드를 정의합니다.
//@PostMapping : Post요청에 대한 응답 경로와 메서드를 정의합니다.
// * HTML Form태그 submit은 GET/POST 요청밖에 없음.
// * jQuery의 Ajaxs나 JS의 fetch함수, JS모듈(node모듈)의 axios함수
//   RESTful API로 호출할때 GET/POST/PUT/DELETE 요청이 사용됨.
//                      select/insert/update/delete
//@PutMapping : Put요청에 대한 응답 경로와 메서드를 정의합니다.
//@DeleteMapping : Delete요청에 대한 응답 경로와 메서드를 정의합니다.
//@PatchMapping : Patch요청에 대한 응답 경로와 메서드를 정의합니다.
//   업데이트 작업시 사용.

//PUT과 PATCH의 차이점
//PUT : 자원의 전체 교체
//PATCH : 자원의 부분 교체

//@RequestMapping : 모든 타입의 요청을 처리할 수 있습니다.
//@RequestMapping(value = "/", method = RequestMethod.GET)
//@RequestMapping(value = "/", method = RequestMethod.POST)


@Controller
public class MainController {
    //@GetMapping("/")
    @RequestMapping(value="/",method = RequestMethod.GET) //옛날방식 + 통합방식
                                                             //HTTP 메서드를 구분하지 않음.

    //@ResponseBody //주로 JSON/XML로 응답할 때 사용
    public String main() {
        return "index.html"; //"index.html"파일로 응답한다. View Resolver 역할.
                             //View Resolver(뷰 리졸버) : 이 문자열이 어떤 파일인지 찾아주는 역할
    }

    @GetMapping("/hello")
    public String hello() {
        return "redirect:/hello.html";
              //뷰를 찾지 말고, 브라우저에게 다른 주소로 다시 요청하라고 해라.
              //스프링 MVC가 인식하는 특별한 명령어.
              //return "hello"; View를 찾아서 보여줘라

    }
}
