package com.study.Ex12CalcDB;

import jakarta.persistence.*;
import lombok.*;

@Entity //DB 테이블과 1:1로 매칭
@Table(name="history")
@Builder
@Getter
@Setter //Setter 함수는 설정하면 안됨.
@AllArgsConstructor
@NoArgsConstructor
public class HistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_no")
    private Long history_no;
    @Column(name="op")
    private String op;
    @

}
