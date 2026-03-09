package com.study.Ex14LoginJoinDB.service;


import com.study.Ex14LoginJoinDB.entity.MemberEntity;
import com.study.Ex14LoginJoinDB.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    @Autowired
    private final MemberRepository memberRepository;

    public MemberEntity login(String memberUsername, String memberPassword) {
        Optional<MemberEntity> optional = memberRepository.findByMemberUsername(memberUsername); //입력된 아이디와 같은 아이디를 DB에서 찾기
                                                                                                 //있다면 : Optional[MemberEntity], 없다면 : Optional.empty
        if(optional.isPresent()){
            MemberEntity m = optional.get();

            if(m.getMemberPassword().equals(memberPassword)){
                return m;
            }

        }

        return null;
    }

}
