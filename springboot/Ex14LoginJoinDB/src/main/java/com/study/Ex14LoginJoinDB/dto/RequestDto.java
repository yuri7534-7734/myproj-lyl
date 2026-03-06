package com.study.Ex14LoginJoinDB.dto;

import com.study.Ex14LoginJoinDB.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RequestDto {
    private Integer memberNo;
    private String memberUsername;
    private String memberPassword;
    private String memberEmail;
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private LocalDate memberJoindate;

    @Builder
    public RequestDto(Integer memberNo, String memberUsername, String memberPassword,String memberEmail, LocalDate memberJoindate){
        this.memberNo = memberNo;
        this.memberUsername = memberUsername;
        this.memberPassword = memberPassword;
        this.memberEmail = memberEmail;
        this.memberJoindate = memberJoindate;

    }
    //DTO to Entity
    public MemberEntity toEntity(){
         return MemberEntity.builder()
                 .memberNo(memberNo)
                 .memberUsername(memberUsername)
                 .memberPassword(memberPassword)
                 .memberEmail(memberEmail)
                 .memberJoindate(memberJoindate)
                 .build();
    }

}
