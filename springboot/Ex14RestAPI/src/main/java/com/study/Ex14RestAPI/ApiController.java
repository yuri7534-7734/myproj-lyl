package com.study.Ex14RestAPI;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//@RestController : @Controller + @ResponseBody를 합쳐놓은 것
//클래스 이름 위에 @RestController을 넣으면,
//클래스 안의 모든 메소드는 @RestController를 적용받는다.
@RestController
//클래스 안의 모든 메소드는 "/api/v1" 경로로 시작한다.
@RequestMapping("/api/v1")

public class ApiController {
    //URI : http://localhost:8080/api/v1/hello
    @RequestMapping("/hello")
    public String hello(){
        return"Hello, 저는 API 서버입니다";
    }

    @PostMapping("/login")
    @ResponseBody //응답을 http body에 전달하여 보낸다.
    public Map<String, String> login(@RequestBody String body){
        System.out.println(body);
        Map<String, String> resMap = new HashMap<>();
        resMap.put("status","ok");
        resMap.put("message","로그인되었습니다.");
        return resMap;
    }

    @PostMapping("/loginDto")
    @ResponseBody
    public ResDto loginDto(@RequestBody ReqDto dto){
        System.out.println("dto.getUsername = " + dto.getUsername());

        ResDto resDto = new ResDto();
        resDto.setStatus("success");
        resDto.setMessage("로그인 성공입니다");

        return resDto;
    }

}
