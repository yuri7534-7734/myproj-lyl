package com.study.Ex11CounterDB;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity //DB 테이블과 1:1 매칭
@Table(name="count") //클래스 이름과 실제 테이블의 이름이 다를 때만 지정한다.
@Builder
@Getter //Setter함수는 설정하면 안된다.
@AllArgsConstructor
@NoArgsConstructor
public class CountEntity {
    @Id //기본키로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //@GeneratedValue : 해당 ID값을 어떻게 생성할지 전략을 선언해준다
    @Column(name="count_no") //DB컬럼이름과 변수이름이 다르면 맞춰준다.
    private Long count_no; // 기본키
    @Column(name="count")
    private Long count; //테이블 컬럼이름과 변수 이름을 매칭해준다.

    public void updateCount(Long count){
            this.count = count;
    }

}
