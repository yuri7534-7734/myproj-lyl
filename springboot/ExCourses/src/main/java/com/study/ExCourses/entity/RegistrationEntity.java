package com.study.ExCourses.entity;

import com.study.ExCourses.dto.ResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "registration")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RegistrationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="registration_id")
    private Long registrationId;

    @Column(name="course_id", nullable = false)
    private Long courseId;

    @Column(name ="registered_at", updatable = false)
    private LocalDateTime registeredAt;

    //저장 직전에 자동으로 현재 시간을 넣어준다.
    @PrePersist
    public void prePersis() {
        this.registeredAt = LocalDateTime.now();
    }

    public ResponseDto toDto(){
        return ResponseDto.builder()
                .registrationId(this.registrationId)
                .courseId(this.courseId)
                .registeredAt(this.registeredAt)
                .build();
    }

}
