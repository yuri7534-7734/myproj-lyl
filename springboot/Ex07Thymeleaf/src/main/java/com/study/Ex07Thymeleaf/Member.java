package com.study.Ex07Thymeleaf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //get/set/reqArgcon 자동으로 만들어준다.
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 필드가 있는 생성자
public class Member {
    private String username; //스프링 시큐리티에서 사용자 계정을 의미한다.
    private String password; //                       비밀번호를 의미한다.
}
