package com.study.Ex15Board.dto;

//RequestDto -> 폼(JSON)데이터 요청 데이터 매핑
//ResponseDto -> JSON 데이터 응답

//기본구조
//Client(브라우저) -> Controller -> Service -> Repository -> Entity(DB)

//요청흐름
//HTTP Request <-> Controller <-> Service <-> Entity 변환 <-> Repository.save() -> DB
//                (@RequestBody -> ReqDto)

//응답흐름(Response)
//DB -> Repository -> Service -> Entity -> Controller -> HTTP Response (JSON or View)
//                            (ResDto 변환)

//스프링 MVC 구조는 Controller → Service → Repository → Entity 순으로 동작하며,
// DTO는 계층 간 데이터 전달을 위해 사용
import com.study.Ex15Board.domain.board.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BoardResponseDto {
    private Long boardIdx;
    private String boardName;
    private String boardTitle;
    private String boardContent;
    private Integer boardHit;
    private LocalDateTime boardDate;

    //entity -> DTO 생성자 함수
    public BoardResponseDto(Board entity){
        this.boardIdx = entity.getBoardIdx();
        this.boardName = entity.getBoardName();
        this.boardTitle = entity.getBoardTitle();
        this.boardContent = entity.getBoardContent();
        this.boardHit = entity.getBoardHit();
        this.boardDate = entity.getBoardDate();

    }

}
