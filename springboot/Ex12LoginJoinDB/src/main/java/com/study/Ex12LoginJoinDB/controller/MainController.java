package com.study.Ex12LoginJoinDB.controller;


import com.study.Ex12LoginJoinDB.dto.MemberLoginDto;
import com.study.Ex12LoginJoinDB.dto.MemberSaveDto;
import com.study.Ex12LoginJoinDB.entity.MemberEntity;
import com.study.Ex12LoginJoinDB.entity.MemberRepository;
import com.study.Ex12LoginJoinDB.service.LoginService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final LoginService loginService;

    @GetMapping("/")
    public String index(){
        //session 객체의 정보는 유효하다.
        return "index";
    }

    //loginForm : 로그인 양식 요청
    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    //@Valid : Validation (유효성체크) 하도록 하는 어노테이션
    //@ModelAttribute : 로그인 폼에서 넘어온 파라미터를 MemberLoginDto 객체에 자동으로 바인딩
    //BindingResult : 밸리데이션 결과값을 가진 객체
    //loginAction : 로그인// 실제 처리
    @PostMapping("/loginAction")
    @ResponseBody //HTML 파일이 아니라 문자 자체를 브라우저로 보내는 것.
    public String loginAction(@Valid @ModelAttribute MemberLoginDto dto,
                              BindingResult bindingResult, HttpSession session){
        if(bindingResult.hasErrors()) { //바인딩 오류 발생
            //DTO에 설정한 message값을 가져온다.
            String detail = bindingResult.getFieldError().getDefaultMessage();
            System.out.println("detail = " + detail);

            //스프링 서버에서 자바스크립트를 body에 응답값으로 보내면,
            //웹브라우저가 이 자바스크립트를 수행해준다.
            //history.back()이 좋은 점 : 로그인( 회원가입 )시 유저가 입력한 값이 그대로 남아있다.
            return "<script>alert('"+ detail +"'); history.back(); </script>";
        }
        //아이디 비밀번호 체크 로직


        //로그인 성공 로직
        //로그인 성공하면 세션에 로그안 상태 저장
        session.setAttribute("isLogin", true); //로그인 상태 유지
        session.setAttribute("userId",dto.getUserId());
        //프론트 컨트롤러에 코드가 길어지면 -> Service클래스(MVC)로 코드를 분리한다.
        String userRole = loginService.getUserRole(dto.getUserId());
        session.setAttribute("userRole",userRole);
        System.out.println(userRole); //admin ROLE_ADMIN,

        if( userRole.equals("ROLE_ADMIN") ){
            return "<script>alert('로그인 성공'); location.href='/admin'; </script>";
        }else {
            return "<script>alert('로그인 성공'); location.href='/'; </script>";
        }
    }
    //리다이렉트 : a태그, location.href, meta refresh
    //          - request, model에 데이터가 날라감.

    //아이디 : hong 암호 : kim1234!#$@

    //비회원 : 로그인 안한 사용자 - 쿠키에 사용자의 흔적을 담는다.
    //회원 : 로그인( 회원가입 )한 사용자 - DB, 세션객체에 데이터를 담는다.

    @GetMapping("/logoutAction")
    public String logoutAction(HttpSession session){
        session.invalidate(); //로그아웃 처리
        return "redirect:/";
    }


    @RequestMapping("/admin")
    public String admin(Model model){
        List<MemberEntity>list = memberRepository.findAll();
        model.addAttribute("list",list);

        return "admin";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }

    //회원가입 저장 액션
    @PostMapping("/joinAction")
    //@ModelAttribute : 클라가 보낸 HTTP 요청 파라미터를 자바 클래스에 매핑하는 어노테이션.
    //MemberSaveDto의 필드에 자동으로 꽂아 넣는다(바인딩)
    public String joinAction(@ModelAttribute MemberSaveDto dto){
        //System.out.println("dto = " + dto.toString()); //dto = com.study.Ex11RealDBCRUD.dto.MemberSaveDto@47587261
        //System.out.println("dto = " + dto.getId()); //DB에서 만들어지는 거니까 안줌.
        System.out.println("dto = " + dto.getUserId());
        System.out.println("dto = " + dto.getUserPw());
        System.out.println("dto = " + dto.getUserName());

        //dto.setId(0L); //DB ROW ID는 0으로 //회원가입일 때는 id를 절대 세팅하지 말 것
        dto.setJoinDate(LocalDate.now()); //현재날짜로 회원가입 설정.

        try {MemberEntity entity = dto.toSaveEntity();
            memberRepository.save(entity);
        } catch (Exception e){
            e.printStackTrace();
            return "redirect";
        }

        return "redirect:/";
    }


    @GetMapping("/viewMember")
    //URL : viewMember?id=3
    //      viewMember?id=${memeber.Id}
    public String viewMember(@RequestParam("id")Long id, Model model){
                                              //Long id = 3; (자동으로 바인딩)
        System.out.println("id = "+id);
        //DB에서 id로 회원을 찾아서 Optional로 받는다.
        //Optional<T> = T가 있을 수도 있고 없을 수도 있다는 걸 명시하는 래퍼(상자)
        //Optional<MemberEntity> = MemberEntity가 있을 수도, 없을 수도 있다.
        Optional<MemberEntity>optional=memberRepository.findById(id);
        //해당 id 회원이 없으면 수정폼 보여줄 게 없으니 홈으로 보내버린다.
        //.isPresent() : 안에 값이 있으면 true
        if(!optional.isPresent()){
            return "redirect:/";
        }
        //null이 아니면 람다식 실행
        //있으면 그때만 model에 담는다.
        //optional안에 값이 있으면 실행.
        optional.ifPresent( (entity)->{
            model.addAttribute("member",entity.toSaveDto());
        } );

        return "modifyForm";
    }

    @PostMapping("/modifyAction")
    public String modifyAction(@ModelAttribute MemberSaveDto dto){
        try{
        MemberEntity entity = dto.toSaveEntity();
        memberRepository.save(entity); //id값이 있으면 업데이트 됨.
        }catch(Exception e){
         e.printStackTrace();
        }

        return "redirect:/";
    }

    @GetMapping("/deleteMember")
    public String deleteMember(@RequestParam("id")Long id){

        try{
            memberRepository.deleteById(id);
        } catch(Exception e){
            e.printStackTrace();
        }

        return "redirect:/";
    }

}//class
