package com.study.Ex14LoginJoinDB.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter // HTTP 요청 파라미터 읽음 -> DTO 객체 생성(new LoginMemberDto();) -> setter 호출해서 값넣기 때문에 필요함
@NoArgsConstructor
public class LoginMemberDto {
    @NotBlank(message = "userId에 null, 빈 문자열, 스페이스문자만 넣을 수 없음.")
    @Size(min = 4, max= 20)
    private String member_username;

    @NotBlank(message = "userId에 null, 빈 문자열, 스페이스문자만 넣을 수 없음.")
    @Size(min=4,max=20)
    private String member_password;

}
