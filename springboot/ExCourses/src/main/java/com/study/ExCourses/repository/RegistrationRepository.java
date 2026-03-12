package com.study.ExCourses.repository;

import com.study.ExCourses.entity.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<RegistrationEntity, Long> {
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM RegistrationEntity r WHERE r.courseId = :courseId")
    boolean existsByCourse_id(@Param("courseId") String courseId);


    @Query("SELECT r FROM RegistrationEntity r WHERE r.courseId = :courseId")
    List<RegistrationEntity> findByCourse_id(@Param("courseId")String courseId);
}
