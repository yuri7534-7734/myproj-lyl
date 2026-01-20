use mydb;

-- 테이블 안에 있는 행의 갯수 알아보는 코드
SELECT count(*)
FROM 제품;

-- 데이터 검색
SELECT *
FROM 고객;

-- 테이블 자체를 통째로 삭제하는 명령어
DROP TABLE 세계무역.주문; -- 바로 삭제되기 때문에 DB명까지 입력
TRUNCATE TABLE 주문


SELECT *
FROM member

-- 열 삭제
DELETE FROM member
WHERE member_no = 3;

INSERT INTO member (member_id, member_pw, member_nickname)
values ('kim', '7534', '김은희')

insert INTO member (member_id,member_pw, member_nickname)
values ('lee','8339','이유리')

CREATE database if not exists yuri; -- 데이터베이스명이 yuri가 없으면 만들어라.

DROP database if exists yuri; -- 데이터베이스명이 yuri가 있으면 삭제해라.



create SCHEMA if not EXISTS yuri;

DROP SCHEMA if exists yuri;

show databases like '세계무역'

show tables from mydb;


create TABLE eating (
id INT ,
name VARCHAR(10),
age INT,
reserve DATE
);

alter table eating
modify id INT primary KEY auto_increment;


update eating
set 