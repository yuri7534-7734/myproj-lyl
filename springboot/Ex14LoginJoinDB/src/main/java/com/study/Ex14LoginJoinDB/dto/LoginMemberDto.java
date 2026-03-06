package com.study.Ex14LoginJoinDB.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter // HTTP 요청 파라미터 읽음 -> DTO 객체 생성(new LoginMemberDto();) -> setter 호출해서 값넣기 때문에 필요함
@NoArgsConstructor
public class LoginMemberDto {
    @NotBlank(message = "아이디에는 공백을 사용할 수 없습니다.")
    @Size(min =4, max= 20)
    private String memberUsername;

    @NotBlank(message = "비밀번호에는 공백을 사용할 수 없습니다.")
    @Size(min=4,max=20)
    private String memberPassword;

    private Integer memberNo;

}
