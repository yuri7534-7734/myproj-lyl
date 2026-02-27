-- 파일이름 : db.sql

-- db 생성
DROP DATABASE IF EXISTS counter;
CREATE DATABASE counter;

-- db 사용 설정
USE counter;

-- 회원정보
DROP TABLE if EXISTS counter.count;
CREATE TABLE counter.count(
   count_no INT AUTO_INCREMENT NOT NULL PRIMARY KEY, -- 고유키
   count INT DEFAULT(0) -- 카운트
);

-- 데이터 추가
INSERT INTO counter.count VALUES(
		0, DEFAULT);
		
-- 데이터 조회
SELECT * FROM counter.count;