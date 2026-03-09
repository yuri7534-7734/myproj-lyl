package com.study.ExPostIt;
/* CREATE TABLE IF NOT EXISTS notes (
        id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
        text LONGTEXT NOT NULL,
        color VARCHAR(16) NOT NULL DEFAULT '#FFF176',
x INT NOT NULL DEFAULT 0,
y INT NOT NULL DEFAULT 0,
rotation DECIMAL(5,2) NOT NULL DEFAULT 0.00,
z_index INT NOT NULL DEFAULT 1,
created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (id),
INDEX idx_notes_z (z_index),
INDEX idx_notes_created (created_at)            -- 이모지 지원
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
*/


import com.study.ExPostIt.dto.PostItResDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notes")
public class PostItEntity { //DB테이블 notes의 한 행(포스트잇 한장)을 표현하는 JPQ엔티티.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false,updatable = false)
    private Long id; //PK 자동 증가
                                           //columnDefinition = "LONGTEXT" : DDL을 생성할 때 해당 컬럼의 타입을 직접 지정한느 속성
                                           //JPA는 Java타입을 자동으로 DB타입으로 변환하는데, 기본 매핑만으로는 원하는 타입을 정확히 지정 못할 때가 있다.
    @Column(name="text", nullable = false, columnDefinition = "LONGTEXT")
    private String text; //메모 내용

    @Column(name="color", nullable = false, length=16)
    private String color; //메모 컬러

    @Column(name="x", nullable = false)
    private Integer X; //메모 위치

    @Column(name="y", nullable = false)
    private Integer y; //메모 위치

    @Column(name ="rotation", nullable = false, precision = 5, scale =2)
    private BigDecimal rotation; //메모 회전 각도

    @Column(name="z_index",nullable = false)
    private Integer z_index; //겹칠 때 위/아래 순서
                                                //updatable = false : UPDATE 쿼리에 이 컬럼을 퐣ㅁ하지 않는다.
                                                //                    최초 INSERT 이후 값이 절대 바뀌면 안되는 컬럼에 사용
    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime created_at; //생성 시각.

    @Column(name="updated_at",nullable = false)
    private LocalDateTime updated_at; //수정 시각.

    //@PrePersist와 @PreUpdate
    //엔터티 생명주기 콜백어노테이션
    //특정 이벤트가 발생하기 직전에 자동으로 해당 메서드를 실행시켜준다.

    // MySQL, ON UPDATE CURRENT_TIMESTAMP와의 차이
    // DB에 맡기면 JPA가 값을 모르는 상태가 될 수 있어서, JPA에서 직접 관리하는 것이 더 안전하다.

    // 실행시점 : em.persist() 호출 직전 -> 최초 INSERT 직전
    // noteRepository.save(new Note())
    // ~
    // @PrePersist 실행 (여기서 created_at, updated_at 세팅된다.)
    // ~
    // INSERT INTO notes ...
    @PrePersist
    public void prePersist() { //첫 저장 전에 created_at, updated_at을 현재 시각으로 세팅한다.
        LocalDateTime now = LocalDateTime.now();
        this.created_at = now;
        this.updated_at = now;
    }

    // 실행시점 : em.merge() 또는 변경감지 flush 직전 -> UPDATE 직전
    // note.setText("수정된 내용")
    // noteRepository.save(note)
    // ~
    // @PreUpdate 실행 (여기서 update_at만 갱신된다.)
    // ~
    // UPDATE notes SET ...
    @PreUpdate
    public void preUpdate() { //첫 업데이트 전에 update_at만 현재 시각으로 세팅한다.
        this.updated_at = LocalDateTime.now();
    }

    public PostItResDto toDto(){ //엔티티 -> PostItResDto로 변환해서 컨트롤러 응답에 사용.
        return PostItResDto.builder()
                .id(this.getId())
                .X(this.getX())
                .y(this.getY())
                .color(this.getColor())
                .text(this.getText())
                .rotation(this.getRotation())
                .created_at(this.getCreated_at())
                .updated_at(this.getUpdated_at())
                .build();
    }

}
