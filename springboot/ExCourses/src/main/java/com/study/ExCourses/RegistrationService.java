package com.study.ExCourses;

import com.study.ExCourses.dto.RequestDto;
import com.study.ExCourses.dto.ResponseDto;
import com.study.ExCourses.entity.RegistrationEntity;
import com.study.ExCourses.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    //수강신청 저장
    public ResponseDto register(RequestDto requestDto) {


        RegistrationEntity entity = RegistrationEntity.builder()
                .courseId(requestDto.getCourseId())
                .build();
        RegistrationEntity saved = registrationRepository.save(entity);
        System.out.println(requestDto.getCourseId());
        return saved.toDto();
    }
    // 신청 목록 조회
    public List<ResponseDto> findAll() {
        return registrationRepository.findAll()
                .stream()
                .map(RegistrationEntity::toDto)
                .toList();
    }



    //수강 취소
    public void delete(Long registrationId) {
            registrationRepository.deleteById(registrationId);
    }
}
