package com.study.Ex06BackendProject.dto;

//DAO : Data Access Object, 데이터에 접근하는 역할을 담당하는 객체

import com.study.Ex06BackendProject.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddArticleRequest {
    private String title;
    private String content;

    //블로그 글을 추가할 때 저장할 엔터티로 변환하는 용도
    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
