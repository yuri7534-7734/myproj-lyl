package com.study.Ex11CounterDB;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data //Getter, Setter 만들기
@Component //빈(Bean)만들기 스프링 프레임워크에서 관리하는 자바 객체
public class Counter { //메모리변수, 서버의 값을 저장하고 가져올 정수형 변수를 설정
    private int count; // 이건 DB가 아니라 메모리 값이기 때문에 서버 꺼지면 초기화 된다.
}
