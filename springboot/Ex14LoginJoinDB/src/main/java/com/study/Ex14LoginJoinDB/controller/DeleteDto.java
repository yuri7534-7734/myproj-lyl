package com.study.Ex14LoginJoinDB.controller;


import com.study.Ex14LoginJoinDB.entity.MemberEntity;
import com.study.Ex14LoginJoinDB.entity.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteDto {
    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public void delete(final Integer memberNo){
        MemberEntity entity = memberRepository.findById(memberNo)
                .orElseThrow( ()-> new IllegalArgumentException("없는 회원입니다."));

       memberRepository.delete(entity);
    }
}
