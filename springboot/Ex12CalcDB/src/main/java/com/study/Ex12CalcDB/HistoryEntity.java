package com.study.Ex12CalcDB;

import jakarta.persistence.*;
import lombok.*;

@Entity //DB 테이블과 1:1로 매칭
@Table(name="history")
@Builder //빌더 패턴에서는 모든 필드를 가진 생성자함수가 필요하다.
@Getter
//@Setter 함수는 설정하면 안됨.
@AllArgsConstructor
@NoArgsConstructor
public class HistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_no")
    private Integer history_no;
    @Column(name="op")
    private String op;
    @Column(name="input1")
    private Integer input1;
    @Column(name="input2")
    private Integer input2;
    @Column(name="result")
    private Double result;

    //DTO to Entity
    public HistoryEntity toEntity(){
        return null;
    }

}
