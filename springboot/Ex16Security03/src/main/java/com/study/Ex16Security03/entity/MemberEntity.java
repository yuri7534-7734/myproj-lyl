package com.study.Ex16Security03.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="member_security")
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Long id;
    @Column(name="username", nullable = false, unique = true, length = 50)
    private String username;
    @Column(name="password",nullable = false, length = 255)
    private String password;
    @Column(name="user_role",nullable = false)
    private String user_role;
    @Column(name="nick_name",nullable = false)
    private String nick_name;
    //@DateTimeFormat 안쓰는 이유
    // 엔티티를 HTML 폼과 직접 매핑하는 것은 권장되지 않는다.
    // 폼 데이터는 DTO로 받고, 필요한 데이터만 엔티티로 변환해서 사용하는 것이 좋다.
    // 엔티티를 직접 사용하면 의도하지 않은 필드 변경으로 DB 데이터가 수정될 위험이 있다.
    // @Transactional 안에서 setter로 엔티티 값을변경하면 DB에 반영된다.


    @Column(name="join_date", nullable = false, updatable = false)
    private LocalDate join_date;

    @PrePersist
    public void prePersist() {
        this.join_date = LocalDate.now();
    }

    //@Builder //일부 필드만 가진 생성자 빌더패턴
    //public MemberEntity(Long, id, ...){}

}
