package com.study.Ex09Model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//스프링부트 실행 시작점_1
@SpringBootApplication
public class Ex09ModelApplication {
//main() 실행 -> 내장 톰캣 실행_2
//스프링 컨테이너 생성_3
//Controller, Bean 자동 스캔
	public static void main(String[] args) {
		SpringApplication.run(Ex09ModelApplication.class, args);
	}

}
