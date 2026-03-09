package com.study.ExPostIt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 애플리케이션 진입점.
 * - 내장 톰캣 + 스프링 부트를 기동한다.
 * - 이 프로젝트에서 정의한 설정/컴포넌트/컨트롤러를 모두 스캔해서 실행한다.
 */
@SpringBootApplication
public class ExPostItApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExPostItApplication.class, args);
    }

}
