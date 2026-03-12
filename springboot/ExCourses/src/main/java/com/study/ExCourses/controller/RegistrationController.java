package com.study.ExCourses.controller;

import com.study.ExCourses.RegistrationService;
import com.study.ExCourses.dto.RequestDto;
import com.study.ExCourses.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping
    public ResponseEntity<List<ResponseDto>> findAll() {
        return ResponseEntity.ok(registrationService.findAll());
    }

    //POST /api/register
    @PostMapping
    public ResponseEntity<?> register(@RequestBody RequestDto requestDto){
        System.out.println("받은 courseId = " + requestDto.getCourseId());
        ResponseDto result = registrationService.register(requestDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        registrationService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
