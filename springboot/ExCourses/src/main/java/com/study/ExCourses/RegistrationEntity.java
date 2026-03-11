package com.study.ExCourses;

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
    private Long registration_id;

    @Column(name="course_id", nullable = false)
    private String course_id;

    @Column(name ="registered_at", updatable = false)
    private LocalDateTime registered_at;

    //저장 직전에 자동으로 현재 시간을 넣어준다.
    @PrePersist
    public void prePersis() {
        this.registered_at = LocalDateTime.now();
    }

    public ResponseDto toDto(){
        return ResponseDto.builder()
                .registration_id(this.registration_id)
                .course_id(this.course_id)
                .registered_at(this.registered_at)
                .build();
    }

}
