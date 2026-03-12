package com.study.Ex16Security03.service;


import com.study.Ex16Security03.entity.MemberEntity;
import com.study.Ex16Security03.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityService implements UserDetailsService {
    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //실제 DB에서 사용자 정보를 가져와서 시큐리티에 넘겨준다.
        Optional<MemberEntity> optional = memberRepository.findByUsername(username);
        if(optional.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        } MemberEntity entity = optional.get();


        //사용자 아이디를 통해, 사용자 정보와 권환(ROLE)을 스프링 시큐리티에 전달해 주는 코드
        List<GrantedAuthority> authorityList = new ArrayList<>();
        //인가 리스트에 추가
        //테스트로 권리자 권한을 한개 추가해준다.
        authorityList.add( new SimpleGrantedAuthority(entity.getUser_role() ) );
        //권한과 함께 사용자 정보 객체를 반환한다.
        //username : 아이디 - "admin"
        //password : 암호 - "1234"
        //Given that there is no default password encoder configured.
        //password는 반드시 암호화해서 넘겨야 됨.
        //Bcrypt 사이트(bcrypt-gnerator.com)에서 암호를 생성해서 붙여넣기.
        //$2a$12$Dv3.fLzfA4ZLi3IRikRBV.d5Yo7gQfhvgYXZG0CGB5mHJTSu4PjQy
        System.out.println("시큐리티가 사용자 정보를 조회했습니다." + entity.getUsername());
        return new User(entity.getUsername(), entity.getPassword(), authorityList);
    }
}
