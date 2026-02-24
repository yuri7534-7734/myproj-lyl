package com.study.Ex11RealDBCRUD.dto;

import com.study.Ex11RealDBCRUD.entity.MemberEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

//데이터 모델링 클래스(데이터를 담는 클래스)의 종류
// 1. DTO 클래스 : Data Transfer Object 약자. 다른 계층간의 데이터 전송 시 사용.
//              : 데이터가 자주 바뀔 때 사용.
//              : @@ 보안상으로 필요한 필드만 담아서 보낼 수 있음 @@
// 2. VO 클래스 : View Object. 자주 데이터가 바뀌지 않을 때 보관용으로 사용
//                @@ 값 자체가 의미를 가지는 객체 @@
// 3. Entity 클래스 : JPA에서 DB테이블과 1:1로 매핑되는 클래스.
//                 : 엔티티 클래스만 가지고는 쓸 수 없음.
//                 : 엔티티 인스턴스에 값을 쓰면, Setter를 통해 값을 바꾸면, 자동으로 테이블에 적용된다.
//           MemberSaveDto : 엔티티와 다른 계층간에 중간단계의 클래스로서, 데이터 처리용도.
//                         : 엔티티 클래스와 1:1 ORM 매핑이 가능하다. 대신 필드 이름이 동일해야함!
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder //builder 패턴 사용
public class MemberSaveDto {
    private Long id;
    private String userId;
    private String userPw;
    private String userName;
    private String userRole;
    //프론트 -> 문자열 / 백엔드 -> LocalDate 라서 타입이 다르기 때문에
    //문자열을 LocalDate로 변환해주어야 한다!
    //@DateTimeFormat : 날짜 타입으로 변환할 때 형식을 알려주는 어노테이션
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate joinDate;



    //Dto -> Entity 변환 함수
    public MemberEntity toSaveEntity(){

        //객체를 생성할 때 방법 2가지
        //1. new 필드가 있는 생성자함수
        //2. builder()를 사용 -> 사용하면 좋은점
        //  1) 생성자함수는 매개변수의 갯수와 순서가 동일해야 한다.
        //     return new MemberEntity(id, userId, userPw, userName, userRole, joinDate)
        //  -> 필드(매개변수)의 순서와 갯수를 자유롭게 하여 생성가능하다.
        return MemberEntity.builder()
                .id(id)
                .userId(userId)
                .userRole(userRole)
                .userPw(userPw)
                .userName(userName)
                .joinDate(joinDate)
                .build();
    }

    //Entity -> Dto 상태 업데이트 함수
    //DTO 객체를 새로 만들지 않고, 현재 DTO의 값을 Entity 기준으로 갱신하는 용도
    public void updateDto(MemberEntity entity){
        //id : DB ROW ID(Key)이므로 업데이트 하지 않는다.
        this.setUserId(entity.getUserId());
        this.setUserPw(entity.getUserPw());
        this.setUserName(entity.getUserName());
        this.setUserRole(entity.getUserRole());
    }



}
