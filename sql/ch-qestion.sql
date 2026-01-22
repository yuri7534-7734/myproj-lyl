SELECT count(*)AS '전체고객',
       count(지역)AS '지역이 있는 고객 수',
       count(DISTINCT 지역)AS '지역종류 수',
       SUM(CASE WHEN 지역 IS NULL THEN 1 ELSE 0 END) AS '지역 NULL 고객수'
FROM 고객


-- HAVING절 : SELECT문에 들어가는 컬럼과 집계함수에만 적용 가능
--          : GROUP BY절과 함께 사용한다.
SELECT 도시
      , COUNT(*) AS '도시별 고객수'
FROM 고객
GROUP BY 도시
HAVING count(*) >= 3
ORDER BY 2 DESC;

SELECT 도시
     , count(*) AS '도시별 고객수'
     , avG(마일리지)AS 평균마일리지
FROM 고객
WHERE 도시 LIKE '%광역시' -- 집계에 참여할 행을 미리 선별한다.
GROUP BY  도시 -- 도시를 기준으로 집계하자.
HAVING COUNT(*) >= 5; -- 집계 후의 결과물에서 선별한다.
-- ORDER BY
-- LIMIT N

SELECT 도시, 담당자직위, SUM(마일리지)
FROM 고객
WHERE 고객번호 LIKE 'T%'
-- GROUP BY절에는 SELECT문의 컬럼명을 모두 적어야 됨.(집계함수 제외!)
GROUP BY 1, 2;
HAVING SUM(마일리지) >= 1000;


-- 연습문제
-- 1. 고객 테이블에서 담당자직위 별로 집계를 하되,
--   담당자직위와 최대 마일리지(Max() 함수)를 출력하시오.
-- 다만, 집계에 참여하는 고객은 '광역시'에 거주해야 함.
-- 최대 마일리지는 10000이상인 행만 출력하시오.

SELECT 담당자직위
       ,MAX(마일리지)AS '최대 마일리지'
FROM 고객
WHERE 도시 LIKE '%광역시'
GROUP BY 담당자직위
HAVING MAX(마일리지) >= 10000;



