package com.study.ExCourses.dto;

import com.study.ExCourses.RegistrationEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    private Long registration_id;
    private String course_id;
    private LocalDateTime registered_at;

    public RequestDto(RegistrationEntity e){
        this.registration_id = e.getRegistration_id();
        this.course_id = e.getCourse_id();
        this.registered_at = e.getRegistered_at();
    }
}
