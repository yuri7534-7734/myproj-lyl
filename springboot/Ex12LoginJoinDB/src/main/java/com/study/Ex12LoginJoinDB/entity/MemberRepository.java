package com.study.Ex12LoginJoinDB.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository : @Component가 들어간 JPA에서 DB제어 클래스
@Repository                             //인터페이스 상속
public interface MemberRepository //한 줄 의미 = 기본 CRUD 자동 제공
    //MemberRepository : DB를 직접 제어X JPA 기능을 상속 받아서 쓰는 인터페이스
        extends JpaRepository<MemberEntity, Long> { // < 어떤 테이블(엔티티), PK(id)의 타입 >
                                                    // MemberEntity의 @Id 필드 타입이 Long이니까 Long
    //사용자 함수 정의
    //쿼리 자동 생성 : select * from T where userId="userId변수값"
                           // 열이름
    Optional<MemberEntity> findByUserId(String userId);
    //JPA 커스텀 쿼리 생성하는 규칙 : 단점 = 모든 검색을 함수호출로 불가능. ( Join이나 복잡한 쿼리 x )
    // 메소드 커스텀이란?
    // = 아이디로 회원 찾기
    // = 이메일로 회원 찾기
    // = 이름 + 나이로 회원 찾기
    // 메소드 커스텀 방법 보기 링크 ::
    //https://velog.io/@633jinn/JPARepository-%EB%A9%94%EC%86%8C%EB%93%9C-%EC%BB%A4%EC%8A%A4%ED%85%80%ED%95%98%EA%B8%B0

    //JPA에서 네이티브 SQL, JPQL을 사용하는 방법




    //JpaRepository의 기본함수 = 기본 CRUD 자동 제공
    // 1. findAll : select * from Table - SQL문을 실행
    // 2. findBy열이름 : select*from Table where 컬럼명=값 - SQL을 실행
    //       예) findById((Long)2) : select * from Table where id=2
    //           findByUserName("홍길동") : select * from Table where username="홍길동"
    // 3. save(Entity) : insert와 update SQL을 실행, id값을 보고 있으면 update, 없으면 insert
    // 4. delete(Entity) : delete SQL을 실행
}
