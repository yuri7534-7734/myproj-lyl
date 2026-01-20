-- ch02.sql

use 세계무역;

-- *은 모든 컬럼을 의미한다.
SELECT *
FROM 고객;

-- 행의 갯수 (별칭(Alias)는 출력창에 임시로 생성되는 이름, as 생략가능)
SELECT COUNT(*) AS '행의 갯수'
FROM 고객;

-- select 결과 화면에서만 잠깐 만들어진 '가상 열'
-- 계산 결과만 잠깐
SELECT 고객번호, 담당자명, 고객회사명, 마일리지 as 포인트,
       마일리지 * 1.1 as '10%인상된 마일리지'
FROM 고객;


-- where절 조건적 조회
SELECT 고객번호, 담당자명, 마일리지 as 포인트
FROM 고객
WHERE 마일리지 >= 100000;


-- order by절 정렬(내림차순, 오름차순)
SELECT 고객번호, 담당자명, 도시, 마일리지 as 포인트
FROM 고객
WHERE 도시 = '서울특별시'
ORDER by 마일리지 asc; -- DESC

-- limit n : 갯수 제한
SELECT *
FROM 고객
LIMIT 3;

-- 마일리지 상위 3명 / 하위 3명
SELECT *
FROM 고객
ORDER by 마일리지 ASC
limit 3;

-- distinct 중복 데이터 제거
SELECT DISTINCT 도시
FROM 고객;