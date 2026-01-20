-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS mydb;

-- 데이터베이스 전환
USE mydb;

-- 회원 테이블 생성
-- INT(10) 정수 10자리 할당
--   4바이트 범위의 숫자(-21억 ~ +21억)
--   무조건 10자리 메모리를 할당.
--   옛날 10의 의미 - 화면에 보여지는 표시 너비((예:0000012345)
--   요즘은 그냥 INT
-- VARCHAR(50) 문자열 50자리 할당(가변길이)
--   최대 50자까지 저장 가능
--   50미만의 문자를 넣으면, 그만큼만 메모리 확보
-- PRIMARY KEY : 행(row)을 구분하는 고유 식별자(예:주민번호)
-- PK는 컬럼에 붙지만, 식별하는 대상은 행(row)
-- 행 중복 방지 장치
-- AUTO_INCREMENT : Insert할 때 1씩 증가하는 속성을 추가.
CREATE TABLE mydb.member(
-- no나 id는 다른 DB/프레임워크의 예약어일 수도 있음 주의.
member_no INT(10) PRIMARY KEY AUTO_INCREMENT,
member_id VARCHAR(50), -- 로그인 아이디
member_pw VARCHAR(50), -- 로그인 암호
member_nickname VARCHAR(50) -- 닉네임
)


-- 테이블 구조 확인할 때
DESC member;


-- 행/레코드/데이터 추가

-- 하지만 문자열:단따옴표, 테이블명, 컬럼명에서는 쌍따옴표를 사용한다.
-- 백틱(`) : 예약어 사용, 특수문자/공백 포함된 이름, 대소문자 구분 시 사용
-- 예) `order`, `user`, `desc`, `key`, `group`
INSERT INTO member (member_no, member_id, member_pw, member_nickname)
values (1, 'hong', '1234', '홍길동')

-- 위의 괄호 내용 생략됨
-- 모든 컬럼의 데이터를 기입하면, 필드(컬럼) 이름 생략 가능 즉, 부분은 안됨.
INSERT INTO `member`
values (2, 'lee', '1234', '이순신')

-- auto_increment 속성은 0으로 추가하면 자동증가됨.
INSERT INTO member 
values (0, 'park', '1234', '박수다')

INSERT INTO member
values (0, 'park', '1234', '박수다')

-- 열(레코드) 삭제하기
DELETE FROM member 
WHERE member_no = 5;

-- SQL예약어(select,INSERT) 대소문자 구분하지 않는다.
-- 사용자 정의어(테이블명, 컬럼멍) : 윈OS 구분하지 않는다. LinuxOS : 구분함.
--                         : 모두 소문자로 작성한다!!
-- 데이터 값 : mySQL(_ci:case_insensitive) : 'abc'와 'ABC'를 같은 값으로 취급.
--          Oracle/postgreSQL : 'abc'와 'ABC'를 다른 값으로 취급.

-- 열(레코드) 수정하기
UPDATE member SET member_id='hong2', member_pw='2222'
WHERE member_no = 1;

-- 모든 행과 열의 데이터 조회
SELECT *
FROM member;

-- 열의 갯수 세기
SELECT COUNT(*)
FROM member;

-- COMMIT : 트랜잭션 작업을 실제로 확정(저장)하는 명령어
-- MySQL : 기본은 auto commit (INSERT/UPDATE/DELETE 후 자동 저장)
-- Oracle : 기본은 manual commit (COMMIT 해야 저장됨)
commit;

-- 확인명령어
SELECT *
from member;