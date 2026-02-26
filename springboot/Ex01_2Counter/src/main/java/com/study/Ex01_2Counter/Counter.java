package com.study.Ex01_2Counter;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data //Getter, Setter, @NoArgsConstructor 등을 자동으로 생성해주는 lombok
@Component //빈 만들어주기
public class Counter {
    private int count;
}
