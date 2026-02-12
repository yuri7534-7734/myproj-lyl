package com.study.Ex02Bean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Bean이란?
//스프링에서 관리하는 자바 클래스 객체를 의미한다. @Component, @Service, @Controller 붙이면 스프링이 관리한다.
// 1. 기본적으로 싱글톤을 지원
// 2. Dead(죽으면) 되면 자동 복구
// 3. 자동 의존성 주입(DI - Dependency Injection)
//    = 제어의 역전 (IoC(Inverse of Control))
//    개발자가 직접 객체를 생성(new)하지 않고,
//    F/W이 대신 생성(관리)해주는 것을 사용하는 것. (객체 관리로부터 자유로워진다)
//    개발자가 A -> B -> C 스프링을 이용하면 C -> B -> A

//Annotation이란? //@SpringBootApplication : 3가지 어노테이션이 붙어있는 어노테이션
//자바코드에 붙이는 메타데이터로서 컴파일러(Spring, 스프링)에게
//정보를 제공하는 역할을 하는 심볼.



@SpringBootApplication
public class Ex02BeanApplication {

	public static void main(String[] args) {

		SpringApplication.run(Ex02BeanApplication.class, args);
		//SpringApplication.run() 실행
		//스프링 컨테이너 생성 ( 객체 창고 )
		//@
		//객체들 스캔 시작
	}

}
