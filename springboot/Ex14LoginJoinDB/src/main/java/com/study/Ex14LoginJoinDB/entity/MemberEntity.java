package com.study.Ex14LoginJoinDB.entity;

import com.study.Ex14LoginJoinDB.dto.SaveMemberDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
//DB의 member 테이블과 1:1로 매핑되는 JPA 엔티티
@Entity
@Table(name="member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer member_no;
    private String member_username;
    private String member_password;
    private String member_email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate member_joindate;

    //Entity -> Dto
    public SaveMemberDto toSaveDto() {
        return SaveMemberDto.builder()
                .member_username(member_username)
                .member_password(member_password)
                .member_email(member_email)
                .member_joindate(member_joindate)
                .build();
    }
}
