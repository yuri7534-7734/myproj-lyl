package com.study.Ex01FirstApp;
//package : 클래스의 소속 (그룹)을 나타냄.
//          동일한 클래스 이름을 다른 그룹에서 사용할 수 있게 해줌
//          실제 폴더 구조와 일치해야함
// com.study.Myclass : 클래스이름은 동일해도, 다른 패키지(폴더)에 있으므로
// com.play.Myclass    동일 이름의 클래스를 사용할 수 있다. 같은 이름이지만 서로 다른 클래스가 된다.
//  예 ) 서울 사는 김서방, 인천 사는 김서방

import org.springframework.boot.SpringApplication;
//autoconfigure : 라이브러리들의 디펜던시(버전 호환성 체크)
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex01FirstAppApplication {

	public static void main(String[] args) {
		//String[] args : 프로그램 구동시 주는 파라미터
		// 예) 한컴오피스 hwp.exe 문서1.hwp 문서2.hwp
		// 예) java -version

		//SpringApplication.run : 스프링 앱 실행 함수
		//Ex01FirstAppApplication.class : 클래스 정보를 담은 객체
		SpringApplication.run(Ex01FirstAppApplication.class, args);
		System.out.println("메인 함수 실행됨");
	}

}