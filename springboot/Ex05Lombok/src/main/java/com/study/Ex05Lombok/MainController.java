package com.study.Ex05Lombok;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 이 클래스는 웹 요청을 처리하는 컨트롤러
//스프링부트 실행
//컴포넌트 스캔 시작
//@Controller 붙은 클래스 발견
//객체를 스프링 컨테이너에 등록
//이 객체는 이제 스프링이 관리함
// -> Bean 등록
public class MainController {

    @Autowired
    private Member member; //스프링이 관리하는 객체 (싱글톤, 기본값)

    @GetMapping("/")
    @ResponseBody //그대로 브라우저에 출력하기
    public String main (){
//        member.setName("Hong");
        System.out.println(member.getName());
        System.out.println(member.getAge());

        return "롬복 예제 서버입니다. " + member.getName()+""+member.getAge();
    }
@GetMapping("/allArgs")
@ResponseBody
public String allArgs(){
        Member member = new Member("변사또",30,"123","");//내가 직접 만든 객체
        return member.getName()+","+member.getAge();
}

//생성자 주입
    private final Member member1;
    @Autowired
    public MainController(Member member){
        this.member1 = member;
    }

    @GetMapping("reqArgs")
    @ResponseBody
    public String reqArgs(){
        member1.setPhone("3456");
        return "reqArgs() " + member1.getPhone();
    }

}//class
