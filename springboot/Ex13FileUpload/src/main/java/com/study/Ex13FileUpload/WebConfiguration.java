package com.study.Ex13FileUpload;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//정적 파일 접근 허용 설정
//브라우저에서 이미지, CSS, JS 파일을 직접 접근할 수 있게 열어주는 설정

@Configuration //여기는 스프링 설정 클래스입니다.
               //이 클래스 안에 스프링이 관리해야 할 객체(Bean) 만들겠다.
public class WebConfiguration extends WebMvcConfigurationSupport {
    //addResourceHandlers : templates폴더와 static폴더의 리소스를 사용가능하도록 해줌.
    //localhost:8080/image.png 경로로 접근 가능하도록 열어주는 역할.
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("file:src/main/resources/templates/",
                        "file:src/main/resources/static/");
    }
}