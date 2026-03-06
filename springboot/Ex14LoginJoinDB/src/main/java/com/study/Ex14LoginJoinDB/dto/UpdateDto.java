package com.study.Ex14LoginJoinDB.dto;

import com.study.Ex14LoginJoinDB.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDto {
    private Integer memberNo;
    private String memberUsername;
    private String memberEmail;
    private String memberPassword;
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private LocalDate memberJoindate;

    public MemberEntity toSaveDto() {
        return MemberEntity.builder()
                .memberNo(memberNo)
                .memberUsername(memberUsername)
                .memberEmail(memberEmail)
                .memberPassword(memberPassword)
                .memberJoindate(memberJoindate)
                .build();
    }

}
