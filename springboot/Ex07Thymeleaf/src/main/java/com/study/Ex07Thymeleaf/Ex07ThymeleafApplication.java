package com.study.Ex07Thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

//Thymeleaf : 스프링 프레임워크의 공식 동적 UI 템플릿 엔진이다.
//          : 과거에는 JSP(Java Server Page)가 주로 동적 웹을 만들기 위해
//            사용되었으나, 최신 트렌드는 타임리프를 기준으로 만들어진다.
//            자바에서 만든 데이터를 HTML에 넣어주는 도구 -> 템플릿 엔진 중에 타임리프.

@ServletComponentScan
@SpringBootApplication
public class Ex07ThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ex07ThymeleafApplication.class, args);
	}

}
