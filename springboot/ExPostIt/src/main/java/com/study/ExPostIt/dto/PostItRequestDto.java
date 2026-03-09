package com.study.ExPostIt.dto;

import com.study.ExPostIt.PostItEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostItRequestDto { //요청 DTO, 클라이언트에서 들어오는 JSON 바디를 받는 용도.
    private Long id;
    private String text;
    private String color;
    private Integer X;
    private Integer y;
    private BigDecimal rotation;
    private Integer z_index;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public PostItRequestDto(PostItEntity e){ //생성자는 필요 시 엔티티 -> 요청 DTO로도 변환 가능.
        this.id=e.getId();
        this.text=e.getText();
        this.color=e.getColor();
        this.X=e.getX();
        this.y = e.getY();
        this.rotation = e.getRotation();
        this.z_index = e.getZ_index();
        this.created_at = e.getCreated_at();
        this.updated_at = e.getUpdated_at();

    }


}
