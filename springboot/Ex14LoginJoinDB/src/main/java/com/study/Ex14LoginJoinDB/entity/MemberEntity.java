package com.study.Ex14LoginJoinDB.entity;

import com.study.Ex14LoginJoinDB.dto.SaveMemberDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
//DB의 member 테이블과 1:1로 매핑되는 JPA 엔티티
@Entity
@Table(name="member")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberNo;
    private String memberUsername;
    private String memberPassword;
    private String memberEmail;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberJoindate;

    //Entity -> Dto
    public SaveMemberDto toSaveDto() {
        return SaveMemberDto.builder()
                .memberNo(memberNo)
                .memberUsername(memberUsername)
                .memberPassword(memberPassword)
                .memberEmail(memberEmail)
                .memberJoindate(memberJoindate)
                .build();
    }
}
