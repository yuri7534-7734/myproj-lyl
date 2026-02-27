package com.study.Ex11CounterDB;

import org.springframework.data.jpa.repository.JpaRepository;

//제네릭 < 엔티티 클래스, PK타입 >
public interface CountRepository extends JpaRepository<CountEntity, Long> {
}
