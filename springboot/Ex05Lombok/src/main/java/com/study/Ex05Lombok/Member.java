package com.study.Ex05Lombok;

//롬복이 지원하는 어노테이션 목록
//@Getter : getter 자동생성
//@Setter : setter 자동생성
//@NoArgsConstructor : 매개변수 없는 기본생성자 자동생성
//@AllArgsConstructor : 모든 필드를 파라미터로 받는 생성자 자동생성
//@RequiredArgsConstructor : final이거나 @NonNull붙은 필드만
//                           매개변수로 받는 생성자를 자동생성
//   : 생성자 주입에 사용


//@NonNull : null을 허용하지 않는 객체 Bean 자동생성
//@Nullable : null을 허용하는 객체 Bean 자동생성,
//          jakarta.annotation.Nullable
//          javax -> jakarta  java이름의 라이센스 때문에
//@Data : @Getter, @Setter,@RequiredArgsConstructor,
//        @ToString, @EqualsAndHashCode을 한꺼번에 설정해주는 어노테이션
//@ToString : toString 메소드 자동생성
//@EqualsAndHashCode : equals, hashCode 메서드 생성


//jakarta : 예전에는 javax 패키지를 Rename한 것. 자바 기능 향상 패키지. @NonNull  //null을 허용하지 않는 필드에 설정한다.
//  private String phone;
//  @Nullable  //null을 허용하는 필드에 설정한다.
//  private String email;
import jakarta.annotation.Nullable;
import lombok.*;
import org.springframework.stereotype.Component;

@Component //Bean으로 사용하기 위해서는
           //기본생성자, 필드생성자, Getter/Setter를 넣어야 됨.
           //Lombok을 이용하면, 자동생성된다.
//@Getter  //인텔리제이 보기 > 도구 창 > 구조 화면에서 확인한다.
//@Setter
//force = true : final필드가 존재할 때, 강제로 초기화 해주는 옵션.
@NoArgsConstructor(force = true) //실제 코드 생성, 실제 클래스 구조를 바꾸는 구현 도구 ( 물리 )
@AllArgsConstructor
//@RequiredArgsConstructor //생성자 주입에 주로 사용
@Data
public class Member {
    private final String name;
    private final Integer age; //final 필드는 반드시 초기화해서 사용해야한다.
    //final 필드 : 한번만 값 설정 가능, 값을 다시 바꿀 수 없음(재할당 불가)
    //값이 절대 바뀌면 안될 때 사용
    @NonNull //null을 허용하지 않는 필드
    private String phone;
    @Nullable
    private String email;
}
