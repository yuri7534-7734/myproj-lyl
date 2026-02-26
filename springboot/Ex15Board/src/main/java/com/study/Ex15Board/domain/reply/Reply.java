package com.study.Ex15Board.domain.reply;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//DB의 reply테이블 1행(row) = Reply객체 1개
@Entity //JPA가 DB 테이블과 매핑되는 클래스로 인식
@Getter //모든 필드 Getter 자동 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) //JAP는 기본 생성자가 필요하다 근데 외부에서 new Reply() 못하게 보호
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reply_idx", nullable = false) //NOT NULL 제약조건
    private Long replyIdx; //기본키 인덱스
    @Column(name="reply_name", nullable = false)
    private String replyName; //댓글 작성자 이름
    @Column(name="reply_content", nullable = false)
    private String replyContent; //댓글 내용
    @Column(name="reply_date", nullable = false)
    private LocalDateTime replyDate = LocalDateTime.now(); //작성일시
    @Column(name="reply_board_idx", nullable = false)
    private Long replyBoardIdx; //게시글 DB 인덱스

    @Builder
    public Reply(String replyName, String replyContent, Long replyBoardIdx) {
    this.replyName = replyName;
    this.replyContent = replyContent;
    this.replyBoardIdx = replyBoardIdx;
    }
    public void update(String replyName,String replyContent) {
        this.replyName = replyName;
        this.replyContent = replyContent;
        this.replyDate = LocalDateTime.now();
    }


}//class

