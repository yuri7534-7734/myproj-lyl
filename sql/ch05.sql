-- ch05.sql

USE 세계무역;

SELECT *
FROM 고객;
-- 조인(join) : 2개 이상의 테이블을 조합하여 하나의 결과를 반환하는 SQL문.
-- ANSI 조인 : 국제표준규격의 SQL문
-- Non-ANSI 조인 : 각 브랜드마다 독자적 SQL문

-- 조인의 종류 : CROSS, INNER, OUTER, SLEF
-- CROSS 조인 : 테이블 A와 테이블 B의 모든 행의 조합, 카티션곱(모든 경우의 수)
SELECT COUNT(*)
FROM 부서; -- 4행
DESC 부서; -- 2열(컬럼): 부서번호, 부서명

SELECT COUNT(*)
FROM 사원; -- 10
DESC 사원; -- 13열(컬럼): 사원번호, 이름, 영문이름 ..

SELECT *
FROM 부서
CROSS JOIN 사원; -- 40행

-- CROSS 예약어 생략(가급적 생략하지 말자.)
SELECT *
FROM 부서
JOIN 사원;

-- CROSS 조인 활용예
-- 테이블을 2개 이상 사용하므로, 테이블이름.컬럼이름 으로 명시적으로 지명한다.
SELECT 부서.부서번호, 부서명, 사원.이름, 사원.부서번호
FROM 부서
CROSS JOIN 사원
WHERE 이름 = '배재용';

-- ambigouous : 애매하다
DESC 부서; -- 부서번호, 부서명
DESC 사원; -- ...상사번호, 부서번호

-- Non-ANSI 문법 (MySQL과 Oracle에서 사용 가능)
SELECT *
FROM 부서, 사원; -- 40개

SELECT 부서.부서번호, 부서명, 사원.이름, 사원.부서번호
FROM 부서, 사원
WHERE 이름 = '배재용';

-- 이너(INNER)조인 : 두 테이블 사이의 공통된 값을 기준으로 결과를 반환
-- 1. 등가조인(이퀴 조인) : = 등호로 비교한다.
-- 2. 비등가조인(논이퀴 조인) : 등호 외 비교연산자로 비교한다.


-- ANSI
SELECT 사원번호, 직위, 사원.부서번호, 부서명
FROM 사원
JOIN 부서
ON 사원.부서번호 = 부서.부서번호
WHERE 이름 ='이소미';

-- Non-ANSI
SELECT 사원번호, 직위, 사원.부서번호, 부서명
FROM 사원, 부서
WHERE 사원.부서번호 = 부서.부서번호 AND 이름 ='이소미';

-- 고객별 주문현황을 출력하는데,
-- 고객번호, 담당자명, 고객회사명별 총주문건수를 출력하고,
-- 주문 건수가 많은 순서대로 출력하시오.
-- 고객: 고객정보, 주문: 주문정보

SELECT *
FROM 주문

SELECT *
FROM 주문세부


SELECT 고객.고객번호, 담당자명, 고객회사명, COUNT(*)AS 주문건수
FROM 고객
JOIN 주문
ON 고객.고객번호 = 주문.고객번호
GROUP BY 1, 2 ,3
ORDER BY COUNT(*) DESC;
--    PK          FK(외래키, 다른 테이블의 키값)

-- 3개 테이블에서 INNER 등가조인을 해보자.

-- ANSI
SELECT 고객.고객번호, 담당자명, 고객회사명, SUM(주문수량*단가)AS 주문금액합
FROM 고객
JOIN 주문
ON 고객.고객번호 = 주문.고객번호
JOIN 주문세부
ON 주문.주문번호 = 주문세부.주문번호
GROUP BY 1,2,3
ORDER BY 4 DESC;

-- Non-ANSI
SELECT 고객.고객번호, 담당자명, 고객회사명, SUM(주문수량*단가)AS 주문금액합
FROM 고객,주문,주문세부
WHERE 고객.고객번호 = 주문.고객번호 AND 주문.주문번호 = 주문세부.주문번호
GROUP BY 1,2,3
ORDER BY 4 DESC;

-- INNER조인 : 비등가조인
DESC 고객;
DESC 마일리지등급;
SELECT * FROM 마일리지등급;

SELECT *
FROM 고객

-- ANSI
SELECT 고객번호, 고객회사명, 담당자명, 마일리지, 등급명
FROM 고객
INNER JOIN 마일리지등급
ON 마일리지 BETWEEN 하한마일리지 AND 상한마일리지
WHERE 담당자명 = '이은광';

-- Non-ANSI
SELECT 고객번호, 고객회사명, 담당자명, 마일리지, 등급명
FROM 고객, 마일리지등급
WHERE 마일리지 BETWEEN 하한마일리지 AND 상한마일리지 AND 담당자명 = '이은광';

-- 아우터(OUTER)조인 : 조건(등가,비등가)에 맞지 않는 행도 결과값으로 나옴.
SELECT * FROM 부서

-- 총 사원은 10명
-- A4 홍보부에 속한 사원이 없음.
SELECT * FROM 사원


SELECT 부서.부서명, 사원.*
FROM 사원
LEFT JOIN 부서
ON 사원.부서번호 = 부서.부서번호; -- 정수진 수습사원은 제외됨.

-- 사원 테이블에서 부서번호가 null인 행도 출력해보자.
SELECT 부서명, 사원.*
FROM 사원
right JOIN 부서
ON 사원.부서번호 = 부서.부서번호;

-- Non-ANSI (오라클에서만 (+)연산자 가능)
SELECT 부서.부서명, 부서.*
FROM 사원, 부서
WHERE 사원.부서번호(+) = 부서.부서번호;

-- Non-ANSI (MySQL에서는 Outer 조인을 지원하지 않음.)

-- 셀프조인 : 한 개의 테이블을 대상으로 조인하는 것.
SELECT 상사번호, 이름, 직위
FROM 사원
WHERE 이름 = '이소미';

SELECT 상사번호, 이름, 직위
FROM 사원
WHERE 사원번호 = 'E06'; -- 이현진 대리

SELECT 사원.사원번호
, 사원.이름
, 상사.사원번호 AS '상사의 사원번호'
, 상사.이름 AS '상사'
FROM 사원
INNER JOIN 사원 AS 상사
ON 사원.상사번호 = 상사.사원번호

-- 연습문제
-- 1. 세계무역 데이터베이스의 제품 테이블과 주문 세부 테이블을 조인하여 
--   제품명별로 주문수량합과 주문금액합을 보이시오.
SELECT 제품.제품명
, SUM(주문세부.주문수량)AS 주문수량합
, TRUNCATE(SUM(주문세부.단가 * 주문세부.주문수량*(1-주문세부.할인율)),0)AS 주문금액합
FROM 제품
INNER JOIN 주문세부
ON 제품.제품번호 = 주문세부.제품번호
GROUP BY 제품.제품명;

-- 2. 주문, 주문세부, 제품 테이블을 활용하여 '아이스크림'제품에 대해서
-- (주문년도 제품명)별로 주문수량합을 보이시오.
SELECT YEAR(주문.주문일)AS `주문년도`, 제품.제품명, sum(주문세부.주문수량)AS `주문수량합`
FROM 제품
INNER JOIN 주문세부
ON 제품.제품번호 = 주문세부.제품번호
INNER JOIN 주문
ON 주문세부.주문번호 = 주문.주문번호
WHERE 제품명 LIKE '%아이스크림%'
GROUP BY 1, 2
ORDER BY YEAR(주문.주문일) ASC;


-- 3. 제품, 주문세부 테이블을 활용하여 제품명별로 주문수량합을 보이시오.
--   이때 주문이 한 번도 안 된 제품에 대한 정보도 함께 나타내시오.
SELECT 제품.제품명, sum(주문세부.주문수량)AS `주문수량합`
FROM 제품
LEFT JOIN 주문세부
ON 제품.제품번호 = 주문세부.제품번호 
GROUP BY 제품.제품명;

-- 4. 고객 회사 중 마일리지 등급이 'A'인 고객의 정보를 조회하시오. 
--  조회할 컬럼은 고객번호, 담당자명, 고객회사명, 등급명, 마일리지입니다.
SELECT 고객.고객번호, 고객.담당자명, 고객.고객회사명, 마일리지등급.등급명, 고객.마일리지
FROM 고객
INNER JOIN 마일리지등급
ON 마일리지 BETWEEN 하한마일리지 AND 상한마일리지
WHERE 등급명 = 'A';

SELECT *
FROM 고객

SELECT *
FROM 주문

SELECT *
FROM 주문세부

SELECT *
FROM 제품

-- 문제1
SELECT 고객.고객회사명
     , TRUNCATE(SUM(주문세부.단가*주문세부.주문수량*(1-주문세부.할인율)),0)AS 총주문금액
     , count(DISTINCT 주문세부.제품번호)AS 주문한제품종류수
     , count(DISTINCT 주문세부.주문번호)AS 총주문건수
     , TRUNCATE(AVG(주문세부.단가*주문세부.주문수량*(1-주문세부.할인율)),0)AS 평균주문금액
FROM 고객
INNER JOIN 주문
ON 고객.고객번호 = 주문.고객번호
INNER JOIN 주문세부
ON 주문.주문번호 = 주문세부.주문번호
GROUP BY 1
HAVING 총주문금액 >= 1000000
ORDER BY 총주문금액 DESC;

-- 문제2
SELECT 사원.이름, 사원.사원번호 , COUNT(주문.주문번호)AS 주문건수, MAX(주문일),MIN(주문일)
FROM 사원
INNER JOIN 주문
ON 사원.사원번호 = 주문.사원번호
GROUP BY 1, 2
ORDER BY 주문건수 DESC;

-- 문제3
SELECT *
FROM 제품

SELECT *
FROM 주문세부

SELECT *
FROM 주문

SELECT *
FROM 고객

SELECT 고객.도시
     ,COUNT(DISTINCT 주문세부.제품번호)AS 제품종류수
     , TRUNCATE(SUM(주문세부.단가*주문세부.주문수량*(1-주문세부.할인율)),0)AS 총매출액
     , SUM(주문세부.제품번호)AS 가장많이주문된제품명
     , COUNT(DISTINCT 고객.고객번호)AS 도시고객수
FROM 제품
INNER JOIN 주문세부
ON 제품.제품번호 = 주문세부.제품번호
INNER JOIN 주문
ON 주문세부.주문번호 = 주문.주문번호
INNER JOIN 고객
ON 주문.고객번호 = 고객.고객번호
GROUP BY 1
ORDER BY 총매출액 DESC
LIMIT 5 ;





-- 실전문제
-- 1. 마일리지 등급명별로 고객수를 보이시오.


SELECT 마일리지등급.등급명, COUNT(고객.고객번호)AS 고객수
FROM 고객
INNER JOIN 마일리지등급
ON 마일리지 BETWEEN 하한마일리지 AND 상한마일리지
GROUP BY 마일리지등급.등급명

-- 2. 주문번호 ‘H0249’를 주문한 고객의 모든 정보를 보이시오.
SELECT *
FROM 고객
INNER JOIN 주문
ON 고객.고객번호 = 주문.고객번호
WHERE 주문.주문번호 LIKE 'H0249'

-- 3. 2020년 4월 29일에 주문한 고객의 모든 정보를 보이시오.
SELECT *
FROM 고객
INNER JOIN 주문
ON 고객.고객번호 = 주문.고객번호
WHERE 주문.주문일 LIKE '2020-04-29'

-- 4. 도시별로 주문금액합을 보이되 주문금액합이 많은 상위 5개의 도시에 대한 
-- 결과만 보이시오.
SELECT 고객.도시
     ,TRUNCATE(SUM(주문세부.단가*주문세부.주문수량*(1-주문세부.할인율)),0)AS 주문금액합
FROM 고객
INNER JOIN 주문
ON 고객.고객번호 = 주문.고객번호
INNER JOIN 주문세부
ON 주문.주문번호 = 주문세부.주문번호
GROUP BY 고객.도시
ORDER BY 주문금액합 DESC
LIMIT 5;

SELECT 고객.고객번호
     ,TRUNCATE((주문세부.단가*주문세부.주문수량*(1-주문세부.할인율)),0)AS 주문금액
FROM 고객
JOIN 주문
ON 고객.고객번호 = 주문.고객번호
JOIN 주문세부
ON 주문.주문번호 = 주문세부.주문번호
WHERE 
(주문세부.단가*주문세부.주문수량*(1-주문세부.할인율)) >
(SELECT AVG(주문세부.단가*주문세부.주문수량*(1-주문세부.할인율))
FROM 주문
JOIN 주문세부
ON 주문.주문번호 = 주문세부.주문번호
WHERE 주문.고객번호 = 고객.고객번호
);

SELECT *
FROM 고객

SELECT *
FROM 주문세부

SELECT *
FROM 주문

SELECT *
FROM 사원

SELECT *
FROM 마일리지등급


-- 1번
SELECT TRUNCATE((단가*주문수량*(1-할인율)),0)AS 주문금액
FROM 주문세부

-- 2번
SELECT 주문.고객번호
  , 주문.주문번호
  , TRUNCATE((주문세부.단가*주문세부.주문수량*(1-할인율)),0)AS 주문금액
FROM 주문
INNER JOIN 주문세부
ON 주문.주문번호 = 주문세부.주문번호

-- 3번
SELECT 고객.고객번호, TRUNCATE(AVG(주문세부.단가*주문세부.주문수량*(1-할인율)),0)AS 평균주문금액
FROM 고객
INNER JOIN 주문
ON 고객.고객번호 = 주문.고객번호
INNER JOIN 주문세부
ON 주문.주문번호 = 주문세부.주문번호
GROUP BY 1;

-- 4번
SELECT 고객.고객번호, TRUNCATE(AVG(주문세부.단가*주문세부.주문수량*(1-할인율)),0)AS 평균주문금액
FROM 고객
LEFT OUTER JOIN 주문
ON 고객.고객번호 = 주문.고객번호
LEFT OUTER JOIN 주문세부
ON 주문.주문번호 = 주문세부.주문번호
GROUP BY 고객.고객번호
HAVING 평균주문금액 IS NULL;

-- 5번
SELECT TRUNCATE(AVG(단가*주문수량*(1-할인율)),0)AS 전체평균금액
FROM 주문세부

-- 6번
SELECT 주문.고객번호
      ,주문.주문번호
      ,TRUNCATE(주문세부.단가*주문세부.주문수량*(1-주문세부.할인율),0)AS 주문금액
FROM 주문
JOIN 주문세부
ON 주문.주문번호 = 주문세부.주문번호
WHERE (주문세부.단가*주문세부.주문수량*(1-주문세부.할인율)) >
      (SELECT AVG(단가*주문수량*(1-할인율)) FROM 주문세부);

-- 7번
SELECT 고객.고객번호
     , TRUNCATE((주문세부.단가*주문세부.주문수량*(1-주문세부.할인율)),0)AS 주문금액
FROM 고객
INNER JOIN 주문
ON 고객.고객번호 = 주문.고객번호
INNER JOIN 주문세부
ON 주문.주문번호 = 주문세부.주문번호
GROUP BY 고객.고객번호
HAVING (주문세부.단가*주문세부.주문수량*(1-주문세부.할인율)) >
       SELECT AVG(단가*주문수량*(1-할인율)) FROM 주문세부
       WHERE 고객.고객번호 = 주문.고객번호
       );