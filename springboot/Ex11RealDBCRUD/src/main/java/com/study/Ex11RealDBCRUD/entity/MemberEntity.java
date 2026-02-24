package com.study.Ex11RealDBCRUD.entity;

import com.study.Ex11RealDBCRUD.dto.MemberSaveDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class MemberEntity {
    //@Id : 기본키 id열로 사용한다는 의미
    //@GeneratedValue : id값을 어떻게 생성할지 전략을 선택
    // 1. IDENTITY : MySQL, MariaDB, PostgreSQL, H2DB
    // 2. SEQUENCE : Oracle, PostgreSQL
    // 3. AUTO : 자동으로 선택
    @Id //기본키(PK)
    //자동 생성 방식을 DB의 자동증가(auto increment/identity column)로 하겠다.
    //“id는 내가 자바에서 안 넣을게. DB가 INSERT 할 때 자동으로 증가시켜서 만들어줘"
    //PK값을 자동 생성하겠다 = DB가 PK를 만들어주는 구조
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId; //DB는 Snake Case 선호, Java는 Camel Case 선호
    private String userPw;
    private String userName;
    private String userRole;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate joinDate;

    //Entity -> DTO
    public MemberSaveDto toSaveDto() {
         return MemberSaveDto.builder()
                 .id(id)
                 .userId(userId)
                 .userPw(userPw)
                 .userName(userName)
                 .userRole(userRole)
                 .joinDate(joinDate)
                 .build();
    }
}

//          MySQL                   Java
//          BIGINT                  Long
//          INT                     Integer
//          Varchar(n)              String
//          Text                    String
//          DATE                    LocalDate
//          DATETIME                LocalDateTime
//          BLOB                    byte[]         = 자주는 안씀
//          TinyInt(1)              Boolean

