package com.study.Ex02Bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//Bean이란?
//스프링에서 관리하는 자바 클래스 객체를 의미한다. @Component, @Service, @Controller 붙이면 스프링이 관리한다.
//같은 이름의 빈을 중복해서 생성하는 것은 불가. 첫글자는 영소문자로 시작한다. ex) studentNO
// 1. 기본적으로 싱글톤을 지원
// 2. Dead(죽으면) 되면 자동 복구
// 3. 자동 의존성 주입(DI - Dependency Injection)
//    = 제어의 역전 (IoC(Inverse of Control))
//    개발자가 직접 객체를 생성(new)하지 않고,
//    F/W이 대신 생성(관리)해주는 것을 사용하는 것. (객체 관리로부터 자유로워진다)
//    개발자가 A -> B -> C 스프링을 이용하면 C -> B -> A

//Annotation이란?
//자바코드에 붙이는 메타데이터로서 컴파일러(Spring, 스프링)에게
//정보를 제공하는 역할을 하는 심볼.
//
//
//@SpringBootApplication : 아래 3가지 어노테이션이 붙어있는 어노테이션
//                       : 기존적인 스프링부트 앱 개발환경과 설정을 다 해준다.

//1. @ComponentScan : @컴포넌트 자동 탐색
//                  : @Component가 붙은 클래스를 다 찾아서 Bean으로 등록한다.
//2. @EnableAutoConfiguration : @자동 설정 활성화
//                            : 핵심! 스프링 프레임워크의 기본적인 기능을 자동활성화 하는 어노테이션.
//                            : " classpath에 있는 라이브러리를 보고 알아서 설정해라 "
//                            : 조건 확인하고 맞으면 Bean 등록, 스프링부트의 편리함의 근원
//3. @SpringBootConfiguration : @설정 클래스 선언
//                            : = @Configuration이 붙은 클래스를 찾아서 설정 클래스로 등록한다.
//      (사용자설정 클래스)      : 주로 부가적인 기능을 추가할 때 사용한다.

//Bean을 만드는 방법
//1. @Configuration + @Bean :
//          Config클래스 안에서 사용되고, 주로 외부 라이브러리를 사용할 때 사용한다.
//2. @Component + @Autowired :
//          주로 개발자가 직접 만든 클래스를 빈으로 등록하기 위해 사용한다.

@Configuration
class MyConfig {
    //외부라이브러리( Java표준 ) 빈 등록한다.
    @Bean
    public java.util.Random random () {
        return new java.util.Random(); //리턴하는 객체
    }
}

@Component
class Student {
    String name = "홍길동";
    @Autowired //빈을 주입받는다.(의존성 주입 DI)
    java.util.Random random;

}

@SpringBootApplication
public class Ex02BeanApplication {


    public static void main(String[] args) {
        System.out.println("스프링 애플리케이션 시작!");

        //객체 창고
        ApplicationContext context =
                SpringApplication.run(Ex02BeanApplication.class,args);

        //스프링 컨테이너(빈 보관소)에 등록된 빈 목록을 출력해보자.
        //myConfig, student, random
        String[] beanNames = context.getBeanDefinitionNames(); //컨테이너에 등록된 모든 Bean 이름 반환
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            if (beanName.equals("random")) {
                System.out.println(beanName);
            }
        }
        //new Random() 으로 생성안하고 컨테이너에서 꺼내 쓴다 = loC(제어의 역전)
        //Bean 꺼내쓰기 //제어의 역전
        java.util.Random r = context.getBean(java.util.Random.class);
        System.out.println( r.nextInt( 10 ) );
        Student student = context.getBean(Student.class);
        System.out.println( student.name );
        System.out.println(student.random.nextInt(10));

        //SpringApplication.run() 실행
        //스프링 컨테이너 생성 ( 객체 창고 )
        //@
        //객체들 스캔 시작
    }

}

