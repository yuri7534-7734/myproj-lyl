USE 세계무역;

-- 서브쿼리(Sub query)

-- 단일행 서브쿼리
-- 최고 마일리지를 보유한 고객의 정보를 보이시오.
SELECT *
FROM 고객

SELECT max(마일리지)
FROM 고객;

SELECT *
FROM 고객
WHERE 마일리지 = 128790;

SELECT *
FROM 고객
WHERE 마일리지 = (SELECT max(마일리지) FROM 고객);

-- 주문번호 'H0250'인 고객의 고객회사명, 담당자명을 출력하시오.
SELECT 고객번호
FROM 주문
WHERE 주문번호 = 'H0250';

SELECT 고객회사명, 담당자명
FROM 고객
WHERE 고객번호 = (
SELECT 고객번호
FROM 주문
WHERE 주문번호 = 'H0250');

-- 복수행 서브쿼리 : 복수행 비교연산자 IN, ANY(SOME), ALL, EXISTS
-- '부산광역시' 고객이 주문한 주문건수를 출력하기

SELECT 고객번호
FROM 고객
WHERE 도시 = '부산광역시'; -- 5행

SELECT COUNT(주문.주문번호)AS '주문건수'
FROM 주문
WHERE 고객번호 IN(SELECT 고객번호 -- 도시가 '부산광역시'인 고객이라면 모두 COUNT
FROM 고객
WHERE 도시 = '부산광역시'
);

-- ANY(SOME) 연산자
-- 마일리지가 부산광역시인 고객의 마일리지보다 높은 고객은?
SELECT 마일리지
FROM 고객
WHERE 도시 = '부산광역시'; -- 7795 ~ 806

SELECT 담당자명, 고객회사명, 마일리지
FROM 고객
WHERE 마일리지 > ANY( -- 마일리지가 한개라도 더 크면 참(true)
SELECT 마일리지 FROM 고객 WHERE 도시='부산광역시')
ORDER BY 마일리지 ASC;

-- ALL : 모든 비교 조건이 참이면
SELECT 담당자명, 고객회사명, 마일리지
FROM 고객
WHERE 마일리지 > ALL( -- 지역별 평균 마일리지의 최대값보다 큰 고객의 정보
SELECT AVG(마일리지) FROM 고객 
GROUP BY 지역); 

SELECT 고객회사명, ROUND(AVG(마일리지))
FROM 고객 
GROUP BY 고객회사명; 

-- ESISTS : 행의 존재여부로 비교
-- 주문이력이 있는 고객의 정보만 출력하기
SELECT 고객번호, 고객회사명
FROM 고객
WHERE EXISTS( 
SELECT *
FROM 주문
WHERE 고객번호 = 고객.고객번호 
);

-- 서브쿼리에서 메인쿼리의 테이블을 조회할 수 있다.

-- INNER JOIN으로 변환해 보자.
SELECT 고객.고객번호, 고객.고객회사명
FROM 고객
INNER JOIN 주문
ON 고객.고객번호 = 주문.고객번호 -- 830
GROUP BY 고객.고객번호, 고객.고객회사명;

SELECT DISTINCT 고객.고객번호, 고객.고객회사명
FROM 고객
INNER JOIN 주문
ON 고객.고객번호 = 주문.고객번호; -- 830

-- 인라인뷰(ININE VIEW) : FROM절 안의 서브쿼리
-- 모든 도시의 평균 마일리지를 임시테이블(인라인뷰)로 사용하자.
SELECT 도시, ROUND(AVG(마일리지)) AS 도시별_평균마일리지
FROM 고객
GROUP BY 도시
ORDER BY AVG(마일리지) DESC;

-- 고객의 마일리지와 도시별 평균마일리지의 차이를 출력하시오.
SELECT 담당자명, 고객회사명, 마일리지, 고객.도시, 도시_평균마일리지,
       도시_평균마일리지 - 마일리지 AS 차이
FROM 고객, -- FROM절에 테이블이 2개 들어가면, Non-ANSI INNER 조인이다.
(SELECT 도시, AVG(마일리지) AS 도시_평균마일리지
FROM 고객 GROUP BY 도시) AS 도시별_평균마일리지
WHERE 고객.도시 = 도시별_평균마일리지.도시;

SELECT *
FROM 고객

-- CTE(WITH)절로 바꿔보자.
WITH 도시별_평균마일리지2 AS
(SELECT 도시, ROUND(AVG(마일리지)) AS 도시_평균마일리지
FROM 고객 GROUP BY 도시)
SELECT 담당자명, 고객회사명, 마일리지, 고객.도시
,도시별_평균마일리지2.도시_평균마일리지
,도시별_평균마일리지2.도시_평균마일리지 - 마일리지 AS 차이
FROM 고객
INNER JOIN 도시별_평균마일리지2
ON 고객.도시 = 도시별_평균마일리지2.도시;

WITH 도시별_평균마일리지2 AS
(SELECT 도시, ROUND(AVG(마일리지)) AS 도시_평균마일리지
FROM 고객 GROUP BY 도시)
SELECT 담당자명, 고객회사명, 마일리지, 고객.도시,
     도시별_평균마일리지2.도시_평균마일리지,
     도시별_평균마일리지2.도시_평균마일리지 - 마일리지 AS 차이
FROM 고객
INNER JOIN 도시별_평균마일리지2
ON 고객.도시 = 도시별_평균마일리지2.도시;


SELECT 고객.도시 , COUNT(*)AS 고객수
FROM 고객
GROUP BY 고객.도시
HAVING 고객수 > 5;

-- 스칼라 서브쿼리 : 메인 쿼리의 SELECT문 안에 사용되는 서브쿼리
-- 한개의 행, 한개의 값을 반환하고, 행이 0개이면 NULL이 됨.
-- 2개이상의 행을 반환하면, 오류가 남.

SELECT MAX(주문일)
FROM 주문
INNER JOIN 고객
ON 주문.고객번호 = 고객.고객번호;

-- 최종주문일
SELECT 고객번호, 담당자명
,(SELECT MAX(주문일) FROM 주문 INNER JOIN 고객 ON 주문.고객번호 = 고객.고객번호) AS 최종주문일
FROM 고객;

-- CTE(Common Table Expression) - WITH절
WITH 도시별요약 AS
(
SELECT 도시, AVG(마일리지)AS 도시_평균마일리지
FROM 고객
GROUP BY 도시
)
SELECT 담당자명, 고객회사명, 마일리지, 고객.도시, 도시_평균마일리지,
     도시_평균마일리지 - 마일리지 AS 차이
FROM 고객,도시별요약
WHERE 고객.도시 = 도시별요약.도시;

-- having절에서
-- 도시별 평균 마일리지가 전체 고객의 평균 마일리지보다 높은 도시만 출력
SELECT 도시, round(avg(마일리지)) AS 도시별_평균
FROM 고객
GROUP BY 도시
HAVING avg(마일리지) > (SELECT avg(마일리지) FROM 고객)
ORDER BY round(avg(마일리지)) DESC;

SELECT 도시, avg(마일리지)AS 도시별_평균
FROM 고객
GROUP BY 도시
HAVING avg(마일리지) > (SELECT avg(마일리지) FROM 고객 )
ORDER BY avg(마일리지) DESC;

SELECT *
FROM 주문세부

SELECT *
FROM 제품

SELECT *
FROM 사원

SELECT *
FROM 부서

SELECT *
FROM 주문

SELECT *
FROM 고객

-- 연습문제
-- 1. ‘배재용’ 사원의 부서명을 보이시오.
--     서브쿼리 또는 조인 방법으로 해결하시오.




SELECT 부서.부서명
FROM 사원	
INNER JOIN 부서
ON 사원.부서번호 = 부서.부서번호
WHERE 이름='배재용';

-- 2.  한번도 주문한 적이 없는 제품의 정보를 보이시오.
--     서브쿼리 또는 조인 방법으로 해결하시오.
SELECT *
FROM 제품
LEFT JOIN 주문세부 
ON 제품.제품번호 = 주문세부.제품번호
WHERE 주문세부.제품번호 IS null;

SELECT *
FROM 제품
WHERE NOT EXISTS (
SELECT *
FROM 주문세부
)



-- 3.  담당자명, 고객회사명, 주문건수, 최초주문일과 최종주문일을 보이시오.
--     인라인뷰로 해결하시오.
SELECT 고객.담당자명, 고객.고객회사명, 주문건수, 최초주문일, 최종주문일
FROM 고객, (
SELECT 주문.고객번호, COUNT(주문.주문번호)AS 주문건수, MIN(주문일)AS 최초주문일, MAX(주문일)AS 최종주문일
FROM 주문 GROUP BY 주문.고객번호)AS 주문2
WHERE 고객.고객번호 = 주문2.고객번호;

-- CTE 버전
WITH 주문2 AS (
SELECT 주문.고객번호
, COUNT(주문.주문번호)AS 주문건수
, MIN(주문일)AS 최초주문일
, MAX(주문일)AS 최종주문일
FROM 주문
GROUP BY 주문.고객번호)
SELECT 고객.담당자명, 고객.고객회사명, 주문건수, 최초주문일, 최종주문일
FROM 고객, 주문2
WHERE 고객.고객번호 = 주문2.고객번호;

-- 실전문제
-- 1. 제품 테이블에 있는 제품 중 단가가 가장 높은 제품명은 무엇인가?
SELECT *
FROM 제품

SELECT 제품명,단가 
FROM 제품
ORDER BY 단가 DESC
LIMIT 1;

-- 2. 제품 테이블에 있는 제품 중 단가가 가장 높은 제품의 주문수량합은 얼마인가?
SELECT *
FROM 주문세부

SELECT *
FROM 제품


SELECT 제품.제품명, (SELECT SUM(주문세부.주문수량) FROM 주문세부 WHERE 제품.제품번호=주문세부.제품번호)AS 주문수량합
FROM 제품
WHERE 단가 = (SELECT MAX(단가) FROM 제품);




-- 3. ‘아이스크림’ 제품의 주문수량합은 얼마인가?
SELECT sum(주문세부.주문수량)AS 주문수량합
FROM 제품
INNER JOIN 주문세부
ON 제품.제품번호 = 주문세부.제품번호
WHERE 제품명 like ('%아이스크림%');

SELECT SUM(주문세부.주문수량)AS 주문수량합
FROM 주문세부
WHERE 제품번호 IN(
SELECT 제품번호
FROM 제품
WHERE 제품명 LIKE ('%아이스크림%')
);


-- 4. ‘서울특별시’ 고객들에 대한 주문년도별 주문건수를 보이시오.

SELECT year(주문일), count(주문.주문번호)AS 주문건수
FROM 주문
INNER JOIN 고객
ON 주문.고객번호 = 고객.고객번호
WHERE 도시 LIKE '서울특별시'
GROUP BY 1 WITH rollup;

SELECT YEAR(주문일)AS 주문년도, COUNT(주문.주문번호)AS 주문건수
FROM 주문
WHERE 고객번호 IN(
SELECT 고객번호
FROM 고객
WHERE 도시 LIKE '서울특별시'
)
GROUP BY YEAR(주문일)
WITH ROLLUP;


