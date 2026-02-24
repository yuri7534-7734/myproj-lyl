package com.study.Ex12LoginJoinDB.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository : @Component가 들어간 JPA에서 DB제어 클래스
@Repository                             //인터페이스 상속
public interface MemberRepository
    //MemberRepository : DB를 직접 제어X JPA 기능을 상속 받아서 쓰는 인터페이스
        extends JpaRepository<MemberEntity, Long> { // < 어떤 테이블(엔티티), PK(id)의 타입 >
                                                    // MemberEntity의 @Id 필드 타입이 Long이니까 Long
    //JpaRepository의 기본함수
    // 1. findAll : select * from Table - SQL문을 실행
    // 2. findBy열이름 : select*from Table where 컬럼명=값 - SQL을 실행
    //       예) findById((Long)2) : select * from Table where id=2
    //           findByUserName("홍길동") : select * from Table where username="홍길동"
    // 3. save(Entity) : insert와 update SQL을 실행, id값을 보고 있으면 update, 없으면 insert
    // 4. delete(Entity) : delete SQL을 실행
}
