package com.study.Ex06BackendProject.controller;

import com.study.Ex06BackendProject.domain.Article;
import com.study.Ex06BackendProject.dto.AddArticleRequest;
import com.study.Ex06BackendProject.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // 테스트용 애플리케이션 컨텍스트
@AutoConfigureMockMvc // MockMvc 생성 및 자동 구동 // 실제 서버를 띄우지 않고 HTTP 요청/응답을 흉내낼 수 있게 해주는 설정
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc; //가짜 HTTP 요청을 보내는 도구
                               //실제 브라우저나 Postman 없이 GET/POST 요청을 테스트 할 수 있어요
    @Autowired
    protected ObjectMapper objectMapper; //직렬화, 역직렬화를 위한 클래스
                                         //Java 객체 - JSON 변환해주는 도구 //AddArticleRequest 객체를 JSON 문자열로 바꿀 때 사용
    @Autowired
    private WebApplicationContext context; //스프링의 전체 웹 설정 정보를 담고있는 객체
                                           //MockMvc를 만들 때 이 정보가 필요하다!
    @Autowired
    BlogRepository blogRepository; //DB에 직접 접근하는 도구
                                   //테스트 전후로 데이터를 넣거나 지울 때 사용한다.

    //매번 깨끗한 환경에서 가짜 HTTP 요청으로 API를 테스트하기 위한 준비 코드
    @BeforeEach //각 테스트 메서드 실행 전마다 자동으로 호출
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build(); //스프링 설정을 반영한 MockMvc를 새로 만든다.
        blogRepository.deleteAll(); //테스트 전에 DB를 깨끗하게 비운다.(이전 테스트 데이터가 영향을 주지 않도록)
    }

    @DisplayName("addArticle : 블로그 글 추가에 성공한다.")
    @Test
    public void addArticle() throws Exception {
        //given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content); //자바 객체

        //객체 JSON으로 직렬화
        //만들어진 requestBody 데이터
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        //when
        //설정한 내용을 바탕으로 요청 전송
        ResultActions result = mockMvc.perform( //mockMvc.perform 핵심내용 : 이 HTTP 요청을 실제 서버처럼 실행해라.
                post(url)
                          .contentType(MediaType.APPLICATION_JSON_VALUE)
                          .content(requestBody));

        //then
        result.andExpect(status().isCreated()); //테스트에서 응답 결과가 맞는 지확인하는 부분
        //.andExpect()
        // = 이 응답이 우리가 기대한 결과인지 검사한다. 테스트에서는 검증(assert) 역할
        // .status()
        // HTTP 상태코드를 검사하겠다
        // HTTP 응답에는 항상 상태코드가 있다.
        // .isCreated()
        // HTTP 201을 의미한다. 201 : 데이터 생성 API 성공 상태코드

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1); // 크기가 1인지 검증
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    };

    @DisplayName("findAllArticles : 블로그 글 목록 조회에 성공한다.")
    @Test       //findAllArticles API가 정상적으로 글 목록을 조회하는지 테스트
    public void findAllArticles() throws  Exception {

        //given = 테스트에 사용할 데이터 준비
        final String url = "/api/articles"; //요청 URL
        final String title = "title"; //테스트 글 제목
        final String content = "content"; //테스트 글 내용

        //DB에 테스트용 글을 미리 넣는 코드.
        blogRepository.save(Article.builder()
                        .title(title)
                        .content(content)
                        .build());

        //when = API 요청을 실제처럼 보내는 부분 // mockMvc.perform : 가짜 HTTP 요청을 실행
        final ResultActions resultActions = mockMvc.perform(get(url) //GET : / api / articles 요청을 보낸다
                .accept(MediaType.APPLICATION_JSON) //나는 JSON 응답을 원한다. = HTTP 에서는 Accept : application/json
        );

        //then
        resultActions
                .andExpect(status().isOk()) //HTTP 상태코드가 200인지 확인
                .andExpect(jsonPath("$[0].content").value(content)) //JSON 응답 데이터 검증 //첫 번째 글의 content 값이 content 변수와 같은지 확인
                .andExpect(jsonPath("$[0].title").value(title)); //첫 번째 글의 title 값이 title 변수와 같은지 확인
    }




}
//직렬화와 역직렬화

//HTTP에서는 JSON을, 자바에서는 객체를 사용
//이것을 형식에 맞게 변환하는 작업을 직렬화 역직렬화
//직렬화 : 자바 시스템 내부에서 사용되는 객체를 외부에서 사용할 수 있도록 데이터를 변환하는 작업하는 것
// title = "제목"       // "title" : "제목"
// content = "내용"     // "content" : "내용"
//역직렬화 : 외부에서 사용하는 데이터를 자바의 객체 형태로 작업하는 것
// "title" : "제목"     // title = "제목"
