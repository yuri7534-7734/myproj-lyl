package com.study.Ex10RealDB;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

//@Entity : 엔티티 클래스임을 알려준다. DB테이블과 1:1매핑되는 클래스.
@Entity //JPA쪽
@Table(name = "member")
@Getter
@AllArgsConstructor
@NoArgsConstructor //기본생성자 필수, @ModelAttribute @RequestBody에 필요!
//@Setter : 넣지 않는다. 개발자의 실수나 자동으로 호출되는 경우를 제거한다.
public class MemberEntity {
    //@Id : 기본키 id열로 사용한다는 의미
    //@GeneratedValue : id값을 어떻게 생성할지 전략을 선택
    // 1. IDENTITY : MySQL, MariaDB, PrestreDB, H2DB
    // 2. SEQUENCE : Oracle, PrestreSQL
    // 3. AUTO : 자동으로 선택
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId; //DB는 Snake Case 선호, Java는 Camel Case 선호
    private String userPw;
    private String userName;
    private String userRole;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate joinDate;
}
