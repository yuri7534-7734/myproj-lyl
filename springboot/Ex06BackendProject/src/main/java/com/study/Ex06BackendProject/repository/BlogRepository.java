package com.study.Ex06BackendProject.repository;

import com.study.Ex06BackendProject.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article,Long> {
}
//JpaRepository의 부모 클래스인 CrudRepository에 save() 메서드가 선언되어있어서
//이 메서드를 사용하면 데이터베이스에 Article엔티티를 저장할 수 있다
