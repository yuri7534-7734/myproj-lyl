package com.study.Ex11CounterDB;

import org.springframework.data.jpa.repository.JpaRepository;

//제네릭 < 엔티티 클래스, PK타입 >
public interface CountRepository extends JpaRepository<CountEntity, Long> { //DB 접근담당
}

//JPA가 자동제공하여 사용가능한 메서드
// save()
// findAlL() : countRepository.findAll()
// findById()
// delete()
// count()
