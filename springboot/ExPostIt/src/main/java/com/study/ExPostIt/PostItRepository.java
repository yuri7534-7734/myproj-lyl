package com.study.ExPostIt;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostItRepository extends JpaRepository<PostItEntity, Long> {
    //기본함수 지원
    //findAll()
    //findById()
    //등등
}
