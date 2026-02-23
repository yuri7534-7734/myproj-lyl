package com.study.Ex10RealDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository : @Component가 들어간 JPA에서 DB제어 클래스
@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //JpaRepository의 기본함수
    // 1. findAll : select * SQL문을 실행
    // 2. findBy열이름 : select 컬럼명 SQL을 실행
    // 3. save : insert와 update SQL을 실행
    // 4. delete : delete SQL을 실행
}
