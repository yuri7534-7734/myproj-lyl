package com.study.ExCourses.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name ="course")
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="course_id")
    private Long courseId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long professorId;
    private String time;
    private Integer credits;
    private Integer capacity;
    private Integer enrolled;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
