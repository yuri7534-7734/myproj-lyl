package com.study.Ex14LoginJoinDB.service;


import com.study.Ex14LoginJoinDB.entity.MemberEntity;
import com.study.Ex14LoginJoinDB.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.lang.reflect.Member;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    MemberRepository memberRepository;

    public MemberEntity login(String member_username, String member_password) {
        return memberRepository.findByMemberUsername(member_username)
                .filter(m->m.getMember_password().equals(member_password))
                .orElse(null);
    }

}
