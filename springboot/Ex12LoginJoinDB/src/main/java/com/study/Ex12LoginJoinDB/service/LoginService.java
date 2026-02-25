package com.study.Ex12LoginJoinDB.service;

import com.study.Ex12LoginJoinDB.entity.MemberEntity;
import com.study.Ex12LoginJoinDB.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Component 가 붙은 3가지 스프링 어노테이션
// 1. @Controller : HTTP요청을 처리하는 프론트 컨트롤러
// 2. @Repository : DB처리를 담당하는 클래스(JPA)
// 3. @Service : 로직(코드)를 주로 담당하는 클래스
@Service
@RequiredArgsConstructor
public class LoginService {
    @Autowired
    MemberRepository memberRepository;

    //아이디로 회원을 찾아서 그 사람의 권한을 꺼내오는 함수
    public String getUserRole(String userId){
       Optional<MemberEntity> optional = memberRepository.findByUserId(userId);
       //DB에서 userId로 회원을 찾는다.
       //찾으면 -> MemberEntity / 못찾으면 -> null
       //null 대신 Optional이라는 상자에 담아서 준다.
       //Optional = 값이 있을 수도 없을 수도 있다. 라고 표현하는 래퍼
        if(!optional.isPresent()) { //or isEmpty()
           //회원이 없으면 권한도 없으니까 null 반환 = 그 아이디의 회원이 없음.
           return null;
       }
       //get() : Optional에 래핑되어진 값을 꺼내온다.
        return optional.get().getUserRole();
        //optional.get() = Optional 상자 안에 MemberEntity 꺼냄
        //getUserRole() = 그 회원의 권한 값 꺼냄
        // 결과적으로 "ADMIN" 같은 문자열 반환
    }
}
