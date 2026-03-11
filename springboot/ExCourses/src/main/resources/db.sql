-- ============================================================
--  수강신청 시스템 DB SQL
--  테이블 : professor, course, registration
--  DB     : MySQL
-- ============================================================

-- 데이터베이스 생성 및 선택
CREATE DATABASE IF NOT EXISTS course_registration
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;

USE course_registration;


-- ============================================================
--  1. 테이블 생성 (CREATE TABLE)
-- ============================================================

-- 교수 테이블
CREATE TABLE IF NOT EXISTS professor (
  professor_id VARCHAR(20)  NOT NULL COMMENT '교수 ID',
  name         VARCHAR(50)  NOT NULL COMMENT '교수 이름',
  created_at   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  PRIMARY KEY (professor_id)
) COMMENT '교수';

-- 과목 테이블
CREATE TABLE IF NOT EXISTS course (
  course_id    VARCHAR(20)  NOT NULL COMMENT '과목 ID',
  name         VARCHAR(100) NOT NULL COMMENT '과목명',
  professor_id VARCHAR(20)  NOT NULL COMMENT '담당 교수 ID',
  time         VARCHAR(100) NOT NULL COMMENT '강의 시간 (예: 월/수 10:00-12:00)',
  credits      INT          NOT NULL DEFAULT 3  COMMENT '학점',
  capacity     INT          NOT NULL DEFAULT 40 COMMENT '수강 정원',
  enrolled     INT          NOT NULL DEFAULT 0  COMMENT '현재 수강 인원',
  created_at   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  PRIMARY KEY (course_id),
  CONSTRAINT fk_course_professor FOREIGN KEY (professor_id)
    REFERENCES professor (professor_id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT chk_enrolled CHECK (enrolled >= 0),
  CONSTRAINT chk_capacity CHECK (capacity > 0),
  CONSTRAINT chk_credits  CHECK (credits > 0)
) COMMENT '과목';

-- 수강신청 테이블
CREATE TABLE IF NOT EXISTS registration (
  registration_id BIGINT       NOT NULL AUTO_INCREMENT COMMENT '수강신청 ID',
  course_id       VARCHAR(20)  NOT NULL COMMENT '과목 ID',
  registered_at   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '신청일시',
  PRIMARY KEY (registration_id),
  UNIQUE KEY uq_registration (course_id),  -- 같은 과목 중복 신청 방지
  CONSTRAINT fk_reg_course FOREIGN KEY (course_id)
    REFERENCES course (course_id) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT '수강신청';


-- ============================================================
--  2. 데이터 추가 (INSERT)
-- ============================================================

-- 교수 데이터
INSERT INTO professor (professor_id, name) VALUES
  ('P001', '김교수'),
  ('P002', '이교수'),
  ('P003', '박교수'),
  ('P004', '최교수'),
  ('P005', '정교수'),
  ('P006', '강교수');

-- 과목 데이터 (App.tsx의 availableCourses 기준)
INSERT INTO course (course_id, name, professor_id, time, credits, capacity, enrolled) VALUES
  ('C001', '자료구조',       'P001', '월/수 10:00-12:00', 3, 40, 35),
  ('C002', '알고리즘',       'P002', '화/목 13:00-15:00', 3, 40, 28),
  ('C003', '데이터베이스',   'P003', '월/수 14:00-16:00', 3, 35, 30),
  ('C004', '웹프로그래밍',   'P004', '화/목 10:00-12:00', 3, 45, 40),
  ('C005', '운영체제',       'P005', '금 09:00-12:00',    3, 35, 20),
  ('C006', '컴퓨터네트워크', 'P006', '월/수 16:00-18:00', 3, 40, 32);

-- 수강신청 데이터
INSERT INTO registration (course_id) VALUES
  ('C001'),
  ('C003'),
  ('C005');


-- ============================================================
--  3. 데이터 수정 (UPDATE)
-- ============================================================

-- 과목 정원 수정
UPDATE course
SET capacity = 50
WHERE course_id = 'C004';

-- 과목 강의 시간 수정
UPDATE course
SET time = '화/목 09:00-11:00'
WHERE course_id = 'C002';

-- 수강신청 시 수강 인원 1 증가
UPDATE course
SET enrolled = enrolled + 1
WHERE course_id = 'C001';

-- 수강취소 시 수강 인원 1 감소
UPDATE course
SET enrolled = enrolled - 1
WHERE course_id = 'C001';


-- ============================================================
--  4. 데이터 삭제 (DELETE)
-- ============================================================

-- 특정 과목 수강취소
DELETE FROM registration
WHERE course_id = 'C005';

-- 과목 삭제 (연관된 수강신청도 CASCADE 삭제됨)
DELETE FROM course
WHERE course_id = 'C006';


-- ============================================================
--  5. 주요 조회 (SELECT)
-- ============================================================

-- 전체 과목 목록 (교수명, 신청가능 여부 포함)
SELECT
  c.course_id,
  c.name                                                    AS course_name,
  p.name                                                    AS professor,
  c.time,
  c.credits,
  c.enrolled,
  c.capacity,
  IF(c.enrolled >= c.capacity, '마감', '신청가능')          AS status
FROM course c
JOIN professor p ON c.professor_id = p.professor_id
ORDER BY c.course_id;

-- 현재 신청한 과목 목록 (총 학점 포함)
SELECT
  c.course_id,
  c.name       AS course_name,
  p.name       AS professor,
  c.time,
  c.credits,
  r.registered_at
FROM registration r
JOIN course    c ON r.course_id    = c.course_id
JOIN professor p ON c.professor_id = p.professor_id
ORDER BY r.registered_at;

-- 현재 총 신청 학점
SELECT SUM(c.credits) AS total_credits
FROM registration r
JOIN course c ON r.course_id = c.course_id;
