package com.study.Ex14LoginJoinDB.dto;

import com.study.Ex14LoginJoinDB.entity.MemberEntity;
import lombok.*;
import org.hibernate.annotations.processing.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveMemberDto {
    private Integer member_no;
    private String member_username;
    private String member_email;
    private String member_password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate member_joindate;

    //Dto to Entity
    public MemberEntity toSaveEntity() {
        //객체를 생성할 때 방법 2가지
        // 1. new 필드가 있는 생성자 함수
        // 2. builder()를 사용 -> 사용하면 좋은점
        //     1) 생성자함수는 매개변수의 순서가 동일해야한다.
        //          return new MemberEntity(no, member_username, member_email..)
        //         -> 필드의 순서를 자유롭게 하여 생성가능하다!

        return MemberEntity.builder()
                .member_no(member_no)
                .member_username(member_username)
                .member_email(member_email)
                .member_password(member_password)
                .member_joindate(member_joindate)
                .build();
    }

}
