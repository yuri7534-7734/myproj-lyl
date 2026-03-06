package com.study.Ex14LoginJoinDB.controller;

import com.study.Ex14LoginJoinDB.dto.LoginMemberDto;
import com.study.Ex14LoginJoinDB.dto.RequestDto;
import com.study.Ex14LoginJoinDB.dto.SaveMemberDto;
import com.study.Ex14LoginJoinDB.dto.UpdateDto;
import com.study.Ex14LoginJoinDB.entity.MemberEntity;
import com.study.Ex14LoginJoinDB.entity.MemberRepository;
import com.study.Ex14LoginJoinDB.service.LoginService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class HtmlController {
    @Autowired
    private final LoginService loginService;

    @Autowired
    private final MemberRepository memberRepository;


    //로그인 화면(main)
    @GetMapping("/")
    public String main(HttpSession session, Model model){

        model.addAttribute("loginUser", session.getAttribute("loginUser"));
        return "index";

    }

    //로그인 버튼 눌렀을 때
    @PostMapping("/loginAction")
    @ResponseBody
    public String loginAction(@Valid @ModelAttribute LoginMemberDto dto,
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
                dto.getMemberUsername(),
                dto.getMemberPassword()
        );
        if (member == null) {
            return "|<script>alert('아이디 또는 비밀번호가 틀렸습니다.'); history.back();</script>|";
        }
        session.setAttribute("member_username", member.getMemberUsername());
        session.setAttribute("loginUser",member);
            //null이여도 에러 안나고 안전하게 하려면 "admin".equals(member.getMemberUsername())
        if(member.getMemberUsername().equals("admin")) {
            return "|<script>alert('관리자 페이지로 이동합니다.'); location.href='/admin';</script>|";
        }

        return "|<script>alert('로그인되었습니다.'); location.href='/';</script>|";
    }
    //관리자 페이지
    @GetMapping("/admin")
    public String admin(Model model) {
            List<MemberEntity> list = memberRepository.findAll();
        System.out.println("list = " + list);
            model.addAttribute("list", list);
        return "admin";
    }

    //관리자 페이지 -> 회원 정보 수정
    @GetMapping("/memberEdit") //@ModelAttribute 보다는 @RequestParam이 나은 이유 : memberNo 하나만 받기 때문에, 여러개 받을 때는 ModelAttribute
    public String memberEdit(@RequestParam Integer memberNo,Model model) {
        MemberEntity entity  = memberRepository.findById(memberNo)
                .orElseThrow( ()-> new IllegalArgumentException("없는 회원입니다.") );
        RequestDto dto = new RequestDto(
                entity.getMemberNo(),
                entity.getMemberUsername(),
                entity.getMemberEmail(),
                entity.getMemberPassword(),
                entity.getMemberJoindate()
        );
        model.addAttribute("dto",dto);

        return "memberEdit";
    }

    //중복버튼 눌렀을 때
    @PostMapping("/check-username")
    @ResponseBody
    public Map<String, Object> checkUsername(@ModelAttribute LoginMemberDto dto) {
        
        Map<String, Object> result = new HashMap<>(); //key-value 객체 넣기 위해 new HashMap 생성
        boolean exists = memberRepository.existsByMemberUsername(dto.getMemberUsername()); //DTO 안에 있는 username 값을 가져오기.
        //               Spring Data JPA가 자동으로 만들어주는 메서드( 0이면 FALSE / 1이상이면 TRUE 반환 )
        result.put("isDuplicate",exists); //HashMap 덕분에 put으로 데이터 넣기 성공
        return result;

    }
    //업데이트 시 본인 제외한 중복확인 시
    @PostMapping("/update-username")
    @ResponseBody
    //Key - Value 형태의 데이터를 반환한다는 뜻 { "isDuplicate"(문자열) : true(Object) }
    public Map<String, Object> updateUsername(@ModelAttribute LoginMemberDto dto) {//프론트에서 보낸 JSON데이터를 DTO에 담아라.
        Map<String, Object> result = new HashMap<>(); //응답으로 보낼 빈 상자 만들기, isDuplicate 값 담을 상자
        boolean isDuplicate = false; //기본 설정 = "중복 아니다"
                                     //중복 발견했을 때만 true로 바꾸기 위함.

        //입력한 아이디와 같은 아이디를 가진 회원이 있는지 확인하는 코드
        //dto.getMemberUsername() = "yuri" 그리고 결과를 Optional<MemberEntity>로 돌려준다.
        Optional<MemberEntity> optional =
                memberRepository.findByMemberUsername(dto.getMemberUsername());

        //조회 결과 같은 아이디를 가진 회원이 DB에 있을 때만 안으로 들어간다.
        if(optional.isPresent()){
            //같은 아이디 일 때
            MemberEntity member = optional.get();//Optional 안에 들어있는 실제 회원 객체 꺼내기

            //DB에서 찾은 회원 번호           //지금 수정하려는 내 회원 번호
            if(!member.getMemberNo().equals(dto.getMemberNo())) {
                isDuplicate = true;

            }
        }
        result.put("isDuplicate", isDuplicate);
        return result; //JSON처럼 응답하게 만들기


    }

    //회원가입 화면
    @GetMapping("/signUpJoin")
    public String signUpJoin() {
        return "signUp";
    }

    //회원가입 버튼 눌렀을 때
    @PostMapping("/signUpForm")
    @ResponseBody
    public String signUpAction(@ModelAttribute SaveMemberDto dto) {
        System.out.println(dto.getMemberEmail());
        System.out.println(dto.getMemberUsername());
        System.out.println(dto.getMemberPassword());

        dto.setMemberJoindate(LocalDate.now());

        try { MemberEntity entity = dto.toSaveEntity();
            memberRepository.save(entity);

        }catch(Exception e){
            e.printStackTrace();
            return "|<script>alert('회원가입 실패했습니다.'); history.back();</script>|";
        }

        return "|<script>alert('회원가입 완료되었습니다.'); location.href='/';</script>|";
    }

    @PostMapping("/updateForm")
    @ResponseBody
    public String updateForm(@ModelAttribute UpdateDto dto){
        System.out.println(dto.getMemberNo());
        System.out.println(dto.getMemberUsername());
        System.out.println(dto.getMemberEmail());
        System.out.println(dto.getMemberPassword());
        dto.setMemberJoindate(LocalDate.now());

        try {  MemberEntity entity = dto.toSaveDto();
               memberRepository.save(entity);
        } catch(Exception e){
            e.printStackTrace();
            return "<script>alert('회원 업데이트 실패했습니다.'); history.back();</script>";
        }
            return "<script>alert('회원 업데이트 성공했습니다.'); location.href='/';</script>";
    }

    @GetMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam Integer memberNo){
        try {
            memberRepository.deleteById(memberNo);
        } catch (Exception e) {
            return "|<script>alert('회원 삭제 실패했습니다.') history.back();</script>|";

        }
        return "|<script>alert('회원 삭제 완료했습니다.'); location.href='/admin'</script>|";
    }
    }


