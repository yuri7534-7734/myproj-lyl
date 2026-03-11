package com.study.ExCourses;

import com.study.ExCourses.dto.RequestDto;
import com.study.ExCourses.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    //수강신청 저장
    public ResponseDto register(RequestDto requestDto) {
        RegistrationEntity entity = RegistrationEntity.builder()
                .course_id(requestDto.getCourse_id())
                .build();
        RegistrationEntity saved = registrationRepository.save(entity);
        return saved.toDto();
    }
    //수강 취소
    public void delete(Long registrationId) {
            registrationRepository.deleteById(registrationId);
    }
}
