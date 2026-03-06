package com.study.Ex14LoginJoinDB.service;


import com.study.Ex14LoginJoinDB.entity.MemberEntity;
import com.study.Ex14LoginJoinDB.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    @Autowired
    private final MemberRepository memberRepository;

    public MemberEntity login(String memberUsername, String memberPassword) {
        return memberRepository.findByMemberUsername(memberUsername)
                .filter(m->m.getMemberPassword().equals(memberPassword))
                .orElse(null);
    }

}
