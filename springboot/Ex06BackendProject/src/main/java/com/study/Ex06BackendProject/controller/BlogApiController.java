package com.study.Ex06BackendProject.controller;

import com.study.Ex06BackendProject.domain.Article;
import com.study.Ex06BackendProject.dto.AddArticleRequest;
import com.study.Ex06BackendProject.dto.ArticleResponse;
import com.study.Ex06BackendProject.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController //HTTP ResponseBody에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private  final BlogService blogService;

    //HTTP 메서드가 POST일 때, 전달 받은 URL과 동일하면 메서드로 매핑
    @PostMapping("/api/articles")                          //RequestBody로 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
         //ResponseEntity<Article> : HTTP 상태코드를 직접 제어하고 싶을 때 ResponseEntity
         //Body = Article 객체
        Article savedArticle = blogService.save(request);
        //요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답객체에 담아 전송
        //Rest에서 많이 사용된다. # REST API 응답을 정석적으로 만드는 방법 #

        //3가지 응답 요소
        //Status : 상태코드 (200, 201, 404 등)
        //Header : 부가 정보
        //Body : 실제 데이터
        return ResponseEntity.status(HttpStatus.CREATED) //HTTP 상태 코드를 201 CREATED로 설정
                .body(savedArticle); //응답 body에 savedArticle 넣는다.
                                     //자동으로 Article 객체 -> JSON 으로 변환
    }

    //블로그 글 전체 조회 API
    //DB에 있는 모든 글을 가져와서 DTO로 변환한 뒤 JSON으로 반환하는 컨트롤러 API 메서드
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
                        //HTTP 응답(Response)형태로 직접 만들어서 반환하겠다
        //ResponseEntity<List<ArticleResponse>>
        //ResponseEntity 안에 List가 들어있고 List 안에 ArticleResponse가 여러 개 들어있다.

        //응답 데이터 : ArticleResponse 객체 여러개 //블로그 글 목록을 JSON으로 반환하겠다.
        List<ArticleResponse> articles = blogService.findAll() //DB에서 모든 글 가져오기
                .stream() //리스트 데이터를 하나씩 처리하겠다.
                .map(ArticleResponse::new) //Article 객체를 ArticleResponse 객체로 변환 for Entity를 그대로 API로 보내지 않기 위해서다.
                .toList(); //변환된 데이터들을 다시 리스트로 만든다.

        //응답 상태까지 같이 반환
        return ResponseEntity.ok() //HTTP 상태코드 200 OK = 요청 성공
                .body(articles); //응답 body에 articles 데이터를 넣는다.
        // 상태코드 : 200 ok
        // body : articles
        // body 타입 : List<ArticleResponse>
    }







}
