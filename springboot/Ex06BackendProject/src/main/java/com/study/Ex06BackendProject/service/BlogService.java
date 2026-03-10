package com.study.Ex06BackendProject.service;

import com.study.Ex06BackendProject.domain.Article;
import com.study.Ex06BackendProject.dto.AddArticleRequest;
import com.study.Ex06BackendProject.dto.ArticleResponse;
import com.study.Ex06BackendProject.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service //해당 클래스를 빈으로 서블릿 컨테이너에 등록해준다.
public class BlogService {

    private final BlogService blogService;
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity()); //DTO는 DB에 바로 저장할 수 없기 때문에 엔티티로 변환 후 저장
    }

    public List<Article> findAll() { //findAll()을 호출해 article 테이블에 저장되어 있는 모든 데이터를 조회합니다.
        return blogRepository.findAll();
    }

    //블로그 글 하나를 조회
    //데이터베이스에 저장되어 있는 글의 ID를 이용해 글을 조회한다.
    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found: " + id));
    }
    //URL 경로에서 값 추출
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }
    
}
