SELECT
*
FROM 주문

SELECT *
FROM 주문세부

SELECT *
FROM 고객

SELECT *
FROM 사원

-- 1번
SELECT 사원.이름
      ,부서.부서명
FROM 사원
JOIN 부서
ON 사원.부서번호 = 부서.부서번호;

-- 2번
SELECT 주문.주문번호
      ,고객.고객회사명
FROM 주문
JOIN 고객
ON 주문.고객번호 = 고객.고객번호;

-- 3번
SELECT 주문.주문번호
      ,고객.고객회사명
      ,주문.주문일
FROM 주문
JOIN 고객
ON 주문.고객번호 = 고객.고객번호;

-- 4번
SELECT 고객.고객회사명
     , COUNT(주문.주문번호)AS 주문건수
FROM 고객
JOIN 주문
ON 고객.고객번호 = 주문.고객번호
GROUP BY 고객.고객회사명;

-- 5번
SELECT 사원.이름
     , COUNT(주문.주문번호)AS 주문건수
FROM 사원
JOIN 주문
ON 사원.사원번호 = 주문.사원번호
GROUP BY 사원.이름;

-- 6번
SELECT 고객.고객회사명
     , SUM(주문세부.단가 * 주문세부.주문수량)AS 주문금액
FROM 주문
JOIN 주문세부
ON 주문.주문번호 = 주문세부.주문번호
JOIN 고객
ON 주문.고객번호 = 고객.고객번호
GROUP BY 고객.고객회사명;

-- 7번
SELECT 고객.고객회사명
     , SUM(주문세부.단가 * 주문세부.주문수량)AS 주문금액
     FROM 주문
JOIN 주문세부
ON 주문.주문번호 = 주문세부.주문번호
JOIN 고객
ON 주문.고객번호 = 고객.고객번호
GROUP BY 고객.고객회사명
HAVING 주문금액 >= 100000;

-- 8번
SELECT 고객.고객회사명
       ,COUNT(주문.주문번호)AS 주문건수
FROM 고객
JOIN 주문
ON 고객.고객번호 = 주문.고객번호
GROUP BY 고객.고객회사명
HAVING 주문건수 >= 5;

-- 9번
SELECT 고객.고객회사명
FROM 고객
LEFT JOIN 주문
ON 고객.고객번호 = 주문.고객번호
where 주문번호 IS NULL;
