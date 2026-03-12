package com.study.ExCourses.dto;

import com.study.ExCourses.entity.RegistrationEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private Long registrationId;
    private Long courseId;
    private LocalDateTime registeredAt;

    public ResponseDto(RegistrationEntity e){
        this.registrationId = e.getRegistrationId();
        this.courseId = e.getCourseId();
        this.registeredAt = e.getRegisteredAt();
    }


}
