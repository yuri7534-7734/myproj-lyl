package com.study.ExPostIt.dto;

import com.study.ExPostIt.PostItEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostItResDto { //응답 Dto, 브라우저에 내려줄 JSON 구조.
    private Long id;
    private String text;
    private String color;
    private Integer X;
    private Integer y;
    private BigDecimal rotation;
    private Integer z_index;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public PostItResDto(PostItEntity e){ //엔티티에서 각 필드를 그대로 복사해서 DTO 생성.
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
