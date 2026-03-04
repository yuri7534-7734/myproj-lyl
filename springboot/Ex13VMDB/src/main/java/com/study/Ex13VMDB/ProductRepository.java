package com.study.Ex13VMDB;


import org.springframework.data.jpa.repository.JpaRepository;

//DB에 접근
public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
}
