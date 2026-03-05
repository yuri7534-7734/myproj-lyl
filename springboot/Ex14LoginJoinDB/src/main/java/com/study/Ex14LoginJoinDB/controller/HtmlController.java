package com.study.Ex14LoginJoinDB.controller;

import com.study.Ex14LoginJoinDB.dto.LoginMemberDto;
import com.study.Ex14LoginJoinDB.entity.MemberEntity;
import com.study.Ex14LoginJoinDB.service.LoginService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequiredArgsConstructor
public class HtmlController {
    @Autowired
    private final LoginService loginService;


    //로그인 화면(main)
    @GetMapping("/")
    public String main(){
        return "index";
    }
    //로그인 버튼 눌렀을 때
    @PostMapping("/loginAction")
    @ResponseBody
    public String logfinAction(@Valid @ModelAttribute LoginMemberDto dto,
                               BindingResult bindingResult, HttpSession session) { //BindingResult : 폼 검증하고 그 결과를 담는 객체
                                                                                   //HTTPSession : 사용자 로그인 상태 저장하는 공간
        if(bindingResult.hasErrors()) { //바인딩 오류 찾아내기 : 입력값 검증에서 에러가 하나라도 있으면 true
            //DTO에 설정한 message값을 가져온다.
            String detail = bindingResult.getFieldError().getDefaultMessage();
            System.out.println("detail : " + detail);
            //bindingResult : 폼 검증 결과를 저장하는 객체, 사용자가 빈값 입력 시  @NotBlank 괄호 안에 있는 메시지 저장됨.
            //getFieldError() : 특정 필드에서 발생한 에러 객체를 가져온다.
            //getDefaultMessage() : 검증 어노테이션에 작성한 message를 가져온다.
            return "<script>alert('"+ detail +"'); history.back(); </script>";
        }

        MemberEntity member = loginService.login(
                dto.getMember_username(),
                dto.getMember_password()
        );
        if(member == null) {
            return "|<script>alert('아이디 또는 비밀번호가 틀렸습니다.'); history.back();</script>|";
        }
        session.setAttribute("member_username", member);
        return "|<script>alert('로그인되었습니다.'); location.href='/';</script>|";
    }

    //회원가입 화면
    @GetMapping("/signUpJoin")
    public String signUpJoin() {
        return "signUp";
    }

    //회원가입 버튼 눌렀을 때
    @PostMapping("/signUpForm")
    @ResponseBody
    public String signUpAction(@Valid @ModelAttribute LoginMemberDto dto,
                               BindingResult bindingResult, HttpSession session) { //BindingResult : 폼 검증하고 그 결과를 담는 객체
                                                                                   //HTTPSession : 사용자 로그인 상태 저장하는 공간


        return "redirect:/index";
    }

}
