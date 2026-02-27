package com.study.Ex11Counter;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data //Getter, Setter 만들기
@Component //빈(Bean)만들기 스프링 프레임워크에서 관리하는 자바 객체
public class Counter { //서버의 값을 저장하고 가져올 정수형 변수를 설정
    private int count;
}
