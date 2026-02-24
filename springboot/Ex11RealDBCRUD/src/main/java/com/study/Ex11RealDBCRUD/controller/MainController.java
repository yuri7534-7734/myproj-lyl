package com.study.Ex11RealDBCRUD.controller;

import com.study.Ex11RealDBCRUD.dto.MemberSaveDto;
import com.study.Ex11RealDBCRUD.entity.MemberEntity;
import com.study.Ex11RealDBCRUD.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private final MemberRepository memberRepository;

    @RequestMapping("/")
    public String main(Model model){
        List<MemberEntity>list = memberRepository.findAll();
        model.addAttribute("list",list);
        return "index";
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
