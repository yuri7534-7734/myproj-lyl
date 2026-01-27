-- ch08.sql

-- DDL : 데이터 정의어
USE 세계학사;

CREATE DATABASE IF NOT EXISTS 세계학사;

-- 컬럼명 데이터타입 정리
-- 길이는 글자단위(byte단위 아님)
-- CHAR(길이) : 고정길이 문자열
-- 예) 이름 CHAR(5); '홍길동'->'홍길동'
--                 '홍길동입니다' -> 못들어감 5자 > 2

-- VARCHAR(길이) : 가변길이 문자열 (0~16383자 정도 UTF8mb4기준)
-- 예) 이름 VARCHAR(5); '홍길동' -> '홍길동' '홍' -> '홍'
-- 자주 사용하는 문자열에 적합(내부 매커니즘)
-- 예) 게시판app - 글제목, 사용자이름

-- TEXT : 큰 길이의 문자열(0~16383자), 자주 사용하지 않는 문자열.
-- 예) 게시판app - 본문내용, 상세설명

-- INT(길이) : 정수(약-21억 ~ 21억), 4바이트
-- 길이는 표현의 범위이다. ZEROFILL 예약어 함께 사용하지 않으면,
--  의미 없다. 추천하지 않음.
--   예) age INT(10) ZEROFILL;  30 -> 0000000030 표현됨.

-- FLOAT : 실수(소숫점 7자리)
-- DATE/TIME : 날짜/시간
-- DATETIME : 날짜와 시간을 통합
-- TIMESTAMP : 날짜와 시간을 통합인데 UTC적용된 시간 1970년 ~ 2038년



CREATE TABLE 학과 (
   학과번호 CHAR(2) -- 99학과이하 예상, 2자리 고정길이
  ,학과명 VARCHAR(20) -- 길이 예측 어려움. 하지만 20자 이하 예쌍.
  ,학과장명 VARCHAR(20)
);


INSERT INTO 학과 
VALUES ('AA', '컴퓨터공학과', '배경민')
     , ('BB', '소프트웨어학과', '김남준')
     , ('CC', '디자인융합학과', '박선영');

SELECT * FROM 학생;

CREATE TABLE 학생 (
    학번 char(5) -- 기본키 (Primary Key, PK)
   ,이름 varchar(20)
   ,생일 date -- 2000-03-10 기본(-)구분, 다른 예) 2000/03/10
   ,연락처 varchar(20)
   ,학과번호 char(2) -- 외래키(FORIGN KEY, FK)
);
DESC 학생;

INSERT INTO 학생
VALUES ('S0001','이윤주','2020-01-30','01033334444','AA')
      ,('S0001','이승은','2021.02.23',NULL,'AA')
      ,('S0003','백재용','2018/03/31','01077778888','DD');
-- PK 제약조건 없어서 그냥 들어감

DROP TABLE 학생;

SELECT *
FROM 휴학생

-- 다른 테이블을 복사해서 테이블 생성하기
CREATE TABLE 휴학생 AS
SELECT *
FROM 학생
WHERE 1=2;

-- 데이터만 지우기
TRUNCATE 휴학생;

INSERT INTO 휴학생
  VALUES ('S0010','이유리','1999-06-10','01077345727','AB');

-- 구조만 복사
CREATE TABLE A AS
SELECT *
FROM B
WHERE 1=0; -- 데이터는 필요없고 컬럼 구조만 필요할 때 쓰는 꼼수
-- where 조건식이 true인 행만 출력
-- where 도시 = '서울' / '서울'만 출력 '부산' 버림

-- 구조 + 데이터 복사
CREATE TABLE A AS
SELECT *
FROM B;

-- 구조 + 데이터 + 제약조건 (완전복제)
CREATE TABLE A LIKE B;

-- 가상 컬럼 (Generated Column) : 계산된 결과를 저장
CREATE TABLE 회원 (
     -- 기본키 설정 : 중복허용안함(UNIQUE), NOT NULL속성
      아이디 VARCHAR(20) PRIMARY KEY,
      회원명 VARCHAR(20),
      키 INT,
      몸무게 INT,
      -- INSERT 시에 자동계산되어 저장된다.
      체질량지수 DECIMAL(4, 1) AS (몸무게/POWER(키/100,2))STORED
);

DESC 회원;

INSERT INTO 회원(아이디,회원명, 키, 몸무게)
VALUES ('ARANG','김아랑',170 ,55);

SELECT * FROM 학생;

-- ALTER : 테이블(객체) 속성 변경
DESC 학생;
ALTER TABLE 학생 ADD 성별 CHAR(1);

ALTER TABLE 학생 ADD COLUMN 벌점 char(2);
-- 컬럼 변경
ALTER TABLE 학생 CHANGE COLUMN 연락처 핸드폰번호 VARCHAR(20);
-- 컬럼 삭제
ALTER TABLE 학생 DROP COLUMN 성별;
-- 테이블 이름변경
ALTER TABLE 학생 RENAME 졸업생;
DESC 졸업생;

-- drop : 테이블의 삭제
DROP TABLE 학과;
DROP TABLE 졸업생;

-- 제약조건
CREATE TABLE 학과 (
     학과번호 CHAR(2), -- NOT NULL, UNIQUE 제약조건 2개
     학과명 VARCHAR(20),
     학과장명 VARCHAR(20)
);

DESC 학과;

SELECT * FROM 학과;

INSERT INTO 학과 
VALUES ('01','국어국문학과','홍교수');

INSERT INTO 학과 
VALUES ('01','영문과','데이비드교수'); -- UNIQUE 제약조건

INSERT INTO 학과 
VALUES (NULL,'영문과','데이비드교수'); -- NOT NULL 제약조건

INSERT INTO 학과 
VALUES ('02',NULL,'데이비드교수'); -- NOT NULL 제약조건

DROP TABLE 학과;

ALTER TABLE 학과 
ADD CONSTRAINT PK_학과 PRIMARY KEY(학과번호); 
DESC 학과;

-- 외래키(FK) 제약조건 추가
CREATE TABLE 학생 (
    학번 CHAR(5) PRIMARY KEY
   ,이름 VARCHAR(20) NOT NULL
   ,생일 DATE NOT NULL
   ,연락처 VARCHAR(20) UNIQUE
   ,학과번호 CHAR(2) -- 외래키 제약조건
   ,성별 CHAR(1) CHECK(성별 IN('남','여')) -- 둘 중 하나의 값만 허용
   -- 등록일이 입력 안되면, 오늘날자 / NULL을 넣으면 NULL도 들어간다.
   ,등록일 DATE DEFAULT(CURDATE())
   -- 학과번호 INSERT 시, 학과 테이블의 학과번호에 있는 것이어야 됨.
   ,FOREIGN KEY(학과번호) REFERENCES 학과(학과번호)
   );

SELECT * FROM 학과;
SELECT * FROM 학생;

INSERT INTO 학과
VALUES ('01','국어국문과','홍교수');

INSERT INTO 학생
VALUES ('S0001','강감찬','2000-02-03','01022223333','01','남',NULL);

VALUES ('S0001','강감찬','2000-02-03','01022223333','01','홍',NULL);
-- Check constraint '학생_chk_1' is violated.
-- DEFAULT 제약조건 테스트
INSERT INTO 학생( 학번, 이름, 생일, 연락처, 학과번호, 성별)
VALUES('S0003','신사임당','2000-04-05','01044446666','02','남');
-- 학과에 02가 없기 때문에 a foreign key constraint fails


SELECT * FROM 학생;

-- ON DELETE/UPDATE CASCADE
-- 참조하는 부모 테이블에서 삭제/수정이 일어날 때 자식테이블도
-- 자동으로 변경/삭제 된다.

DROP TABLE 학생;
DROP TABLE 학과;

CREATE TABLE 학과 (
    학과번호 CHAR(2) PRIMARY KEY,
    학과명 VARCHAR(20) 
);

CREATE TABLE 학생 (
     학번 CHAR(5) PRIMARY KEY,
     이름 VARCHAR(20),
     학과번호 CHAR(2),
     FOREIGN KEY(학과번호) -- MUL
     REFERENCES 학과(학과번호)
     ON DELETE CASCADE
     ON UPDATE CASCADE
);
DESC 학생;
DESC 학과;

-- 학과 데이터
INSERT INTO 학과 VALUES ('01','국어국문학과');
INSERT INTO 학과 VALUES ('02','컴퓨터공학과');
SELECT * FROM 학과;
-- 학생 데이터
INSERT INTO 학생 VALUES ('S0001','홍길동','01');
INSERT INTO 학생 VALUES ('S0002','이소룡','02');

SELECT * FROM 학과;
SELECT * FROM 학생;

-- 학과번호를 수정하면, 참조하던 학생 테이블의 학과번호도 함께 수정된다.
UPDATE 학과 SET 학과번호 ='03' WHERE 학과번호 = '02';
-- 학과번호를 삭제하면, 참조하던 학생 테이블의 레코드도 함께 삭제된다.
DELETE FROM 학과 WHERE 학과번호 = '01';


-- 연습문제
DESC 제품;
USE 세계무역;
-- 1번
ALTER TABLE 제품 ADD CHECK (재고 >= 0);
ALTER TABLE 제품 MODIFY COLUMN 재고 INT CHECK (재고>=0);
-- MODIFY COUMN : 컬럼이름 변경 불가, 구성은 변경 가능.
ALTER TABLE 제품 CHANGE COLUMN 재고 재고 INT CHECK (재고>=0);
-- CHANGE COLUMN : 컬럼이름 , 구성을 변경 가능.

-- 2번
ALTER TABLE 제품 ADD COLUMN 재고금액 INT AS (단가*재고) STORED;

SELECT *
FROM 제품

SELECT *
FROM 주문세부


-- 3번
ALTER TABLE 주문세부 
ADD CONSTRAINT FK_제품
FOREIGN KEY (제품번호)
REFERENCES 제품(제품번호)
ON DELETE CASCADE
ON UPDATE CASCADE;

SELECT *
FROM 영화

-- 실전문제
-- 1번
CREATE TABLE 영화 (
     영화번호 CHAR(5) PRIMARY KEY
    ,타이틀 VARCHAR(100) NOT NULL
    ,장르 VARCHAR(20) CHECK(장르 IN('코미디','드라마','다큐','SF','액션','역사','기타'))
    ,배우 VARCHAR(100) NOT NULL
    ,감독 VARCHAR(50) NOT NULL
    ,제작사 VARCHAR(150) NOT NULL
    ,개봉일 DATE 
    ,등록일 DATE DEFAULT (CURDATE())
    );

-- 2번
DROP TABLE 평점관리;
CREATE TABLE 평점관리 (
     번호 INT AUTO_INCREMENT PRIMARY KEY
    ,평가자닉네임 VARCHAR(50) NOT NULL
    ,영화번호 CHAR(20) NOT NULL 
    ,평점 INT NOT NULL CHECK( 1 <= 평점 <=5 )
    ,평가 VARCHAR(2000) NOT NULL
    ,등록일 DATE DEFAULT (CURDATE())
    ,FOREIGN KEY (영화번호) REFERENCES 영화(영화번호)
     ON DELETE CASCADE
     ON UPDATE CASCADE
)


-- 3번
INSERT INTO 영화 (영화번호, 타이틀, 장르, 배우, 감독, 제작사, 개봉일)
VALUES('00001','파묘','드라마','최민식,김고은','장재현','쇼박스','2024-02-22')
     ,('00002','듄.파트2','액션','티모시 샬랴메, 젠데이아','드니 뵐뇌브','레전더리 픽처스','2024-02-28');

-- 4번
INSERT INTO 평점관리 ( 평가자닉네임, 영화번호, 평점, 평가)
VALUES('영화광','00001',5,'미치도록 스릴이 넘쳐요')
     ,('무비러브','00002',4,'장엄한 스케일이 좋다');
     
-- 5번
 INSERT INTO 영화 (영화번호)
 VALUES ('00003');

-- 6번
DELETE FROM 영화
WHERE 영화번호 = '00002';

-- 7번 
 ALTER TABLE 평점관리
 
 


-- SELECT *
-- FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS tc
-- WHERE CONSTRAINT_SCHEMA.CHECK_CONSTRAINTS cc
-- ON tc.TC.CONSTRAINT_NAME = cc.constraint_name
-- WHERE tc.TABLE_NAME ='제품';
