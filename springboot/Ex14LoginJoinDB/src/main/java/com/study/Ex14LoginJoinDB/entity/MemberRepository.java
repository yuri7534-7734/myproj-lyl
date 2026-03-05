package com.study.Ex14LoginJoinDB.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

    Optional<MemberEntity> findByMemberUsername(String username);
}
