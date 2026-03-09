package com.study.ExPostIt.controller;


import com.study.ExPostIt.PostItEntity;
import com.study.ExPostIt.PostItRepository;
import com.study.ExPostIt.dto.PostItRequestDto;
import com.study.ExPostIt.dto.PostItResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestController {
    private final PostItRepository postItRepository;

    @GetMapping({"/notes", "/list"})
    public List<PostItResDto> list(){
        //리스트 //안에 담길 타입들

        // 이런 구조 : PostItResDto 객체 여러개가 담긴 구조
        //List<PostItResDto> =
        // [
        //        PostItResDto { id=1, text="메모1" },
        //        PostItResDto { id=2, text="메모2" },
        //        PostItResDto { id=3, text="메모3" }
        // ]

        //DB 전체 조회해서 Entity list로 만들기
        List<PostItEntity> list = postItRepository.findAll();
        //Entity에서 DTO list로 변환한다.
        List<PostItResDto> dtoList = list.stream()
                        .map(PostItResDto::new)
                        .toList();
        //List<PostItResDto>로 매핑 후 JSON 배열 응답.
        return dtoList;


    }

    @PostMapping("/add") //빈 노트 생성
    public PostItResDto add(@RequestBody PostItRequestDto dto){
        // DB 스키마상 text는 NOT NULL이라서, 클라이언트가 안 보내면 빈 문자열로 저장
        String safeText = (dto.getText() == null) ? "" : dto.getText();

        PostItEntity entity = PostItEntity.builder()
                //.id() 생략
                .text(safeText)
                .X(dto.getX())
                .y(dto.getY())
                .color(dto.getColor())
                .z_index(dto.getZ_index())
                .rotation(dto.getRotation())
                .build();
        PostItEntity newEntity = postItRepository.save(entity);

        PostItResDto resDto = newEntity.toDto();

        return resDto;
    }

    @PatchMapping("/notes/{id}/text")
    public PostItResDto updateText(@PathVariable Long id, @RequestBody PostItRequestDto dto){
        PostItEntity entity = postItRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "note not found"));

        String safeText = (dto.getText() == null) ? "" : dto.getText();
        entity.setText(safeText);

        PostItEntity saved = postItRepository.save(entity);
        return saved.toDto();
    }

    @PatchMapping("/notes/{id}/position")
    public PostItResDto updatePosition(@PathVariable Long id, @RequestBody PostItRequestDto dto){
        PostItEntity entity = postItRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "note not found"));

        // 좌표는 null이면 기존 값 유지
        if (dto.getX() != null) entity.setX(dto.getX());
        if (dto.getY() != null) entity.setY(dto.getY());

        PostItEntity saved = postItRepository.save(entity);
        return saved.toDto();
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id){
        Optional<PostItEntity> entity = postItRepository.findById(id);
        if (entity.isPresent()){
            postItRepository.delete(entity.get());
        }
    }

}//class

//Java에서 반환하는 것 (List<PostItResDto>)
//[
//  PostItResDto { id=1, text="오늘 할 일", color="#FFF176" },
//  PostItResDto { id=2, text="회의 준비", color="#FF8A80" }
//]

//브라우저 / 클라이언트가 받는 것 (JSON 배열)
//[
//  { "id": 1, "text": "오늘 할 일", "color": "#FFF176" },
//  { "id": 2, "text": "회의 준비", "color": "#FF8A80" }
//]

// 누가 변환?
// @RestController 가 내부적으로 Jackson 라이브러리를 사용해서 자동 변환해준다.
// ~
// Java List 반환
// ~
// Jackson 이 자동으로 JSON으로 직렬화
// ~
// 클라이언트에 JSON 응답