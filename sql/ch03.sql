-- ch03.sql
-- 내장 함수 : MySQL에서 기본적으로 지원하는 함수
-- 1. 단일행 함수
-- 2. 여러행 함수(집계 함수)

-- CHAR_LENGTH, LENGTH 문자열의 길이
-- CHAR_LENGTH : 글자길이(몇 글자)
-- LENGTH : 바이트 수(영문자-ASCII, 한글(중,일)-유니코드)
SELECT CHAR_LENGTH('HELLO')
      ,LENGTH('HELLO');
SELECT CHAR_LENGTH('한글')
      ,LENGTH('한글'); -- 한글자 3바이트
      
-- 문자열 연결
SELECT CONCAT('DREAMS','COM','TRUE');
-- WS : With Seperator 약자 ( 구분자와 함께 )
SELECT CONCAT_WS('-','2026','01','21');

-- 문자열 일부 가져오기
SELECT LEFT('SQL 완전정복', 3);
SELECT RIGHT('SQL 완전정복', 4);
SELECT SUBSTR('SQL 완전정복', 2, 5); -- 시작인덱스 1부터 시작, 갯수
SELECT SUBSTR('SQL 완전정복', 2); -- 인덱스만 주어지면 인덱스부터 끝까지

-- 문자열 일부 가져오기(구분자 이용)
-- 2번째 구분자 이후를 지우고 가져온다.
SELECT SUBSTRING_INDEX('서울시 동작구 흑석동', ' ', 2);
-- 2번째 구분자 이전을 지우고 가져온다.
SELECT SUBSTRING_INDEX('서울시 동작구 흑석동', ' ', -2);

-- 자릿수 채우기 함수
SELECT LPAD('SQL', 10, "#"); -- LEFT PAD
SELECT RPAD('SQL', 5, "*"); -- LEFT PAD
SELECT RPAD('SQL', 5, " "); -- LEFT PAD
SELECT RPAD('SQL', 10, "0"); -- LEFT PAD

-- 공백 제거
SELECT LTRIM(' SQL '); -- 왼쪽 공백을 제거
SELECT RTRIM(' SQL '); -- 오른쪽 공백을 제거
SELECT TRIM(' SQL '); -- 양쪽 공백을 제거
SELECT REPLACE(' SQL ', ' ',''); -- 모든 공백을 제거

-- 특정문자 제거(TREM)
SELECT TRIM(both '###' from '###SQL###');
SELECT TRIM(both '#' from '###SQL###');
SELECT TRIM(both '##' from '###SQL###');

SELECT TRIM(both 'abc' from 'abcSQLLababc');
SELECT TRIM(leading 'abc' from 'abcSQLLababc');
SELECT TRIM(trailing 'abc' from 'abcSQLLababc');


-- 문자열 인덱스 찾기
SELECT FIELD('JAVA', 'SQL', 'JAVA', 'C'); -- 2인덱스(두번째)
SELECT FIND_IN_SET('JAVA', 'SQL,JAVA,C'); -- 2인덱스
SELECT INSTR('네 인생을 살아라', '인생'); -- 3인덱스
SELECT ELT(2, 'SQL','JAVA','C'); -- 인덱스 2인 'JAVA' 반환

-- 문자열 중복
select REPEAT('*', 5); -- ******
SELECT CONCAT( repeat('*',5),'star'); -- *****star









-- 문자열 치환
select replace ('010.123.4567','.','-');

-- 문자열 거꾸로
SELECT REVERSE('0LLEH');

-- 소숫점 관련 함수들
-- 올림 0이상이면 자릿수증가
-- 버림 소숫점 버림
-- 반올림 5이상이면 올림
SELECT CEILING(123.33); -- 124 소숫점 첫째자리에서 올림

SELECT FLOOR(123.56); -- 123 소숫점 첫째자리에서 버림
-- FLOOR()에서는 두번째 매개변수(인자) 쓸 수 없음

SELECT ROUND(123.56); -- 124 소숫점 첫째자리에서 반올림
SELECT ROUND(123.56, 1); -- 123.6 소숫점 둘째자리에서 반올림
SELECT ROUND(123.567, 2); -- 123.56 소숫점 셋째자리에서 반올림
SELECT ROUND(3456.1234, -1); -- 3460 일의 자리에서 반올림
SELECT ROUND(3456.1234, -2); -- 3500 십의 자리에서 반올림


SELECT TRUNCATE(3456.1234, 1);  -- 3456.1 소숫점 둘째자리에서 버림
SELECT TRUNCATE(3456.1234, 2); -- 3456.12
SELECT TRUNCATE(3456.1234, -1); -- 3450
SELECT TRUNCATE(3456.1234, -2); -- 3400

-- 절대값
SELECT ABS( -120 );
SELECT ABS( 120 );
-- 부호
SELECT SIGN( -120 ); -- 음수이므로 -1을 리턴
SELECT SIGN( 120 ); -- 양수이므로 1을 리턴
-- 나누기 함수
SELECT 203 % 4;
SELECT 203 mod 4;
SELECT MOD( 203, 4);
-- 제곱승
SELECT POWER(2, 3);
-- 제곱근
SELECT SQRT( 16 );
-- 랜덤값
SELECT RAND(); -- JS의 random()함수와 유사. 0.0 ~ 0.99999...
-- 1~100사이의 랜덤 정수
SELECT FLOOR(RAND() * 100) +1;

-- 현재 날짜시간 가져오기
-- now() : 쿼리가 시작된 시각 ( 기본으로 사용하면 됨 )
-- SYSDATE() : 함수가 호출된 실시간(찰나)
SELECT now(), SLEEP(2), SYSDATE();

-- 현재 날짜 가져오기
SELECT CURDATE();
-- 현재 시간 가져오기
SELECT CURTIME();

-- 날짜 간격 가져오기 (예-설날까지 남은 날짜 = D-day)
SELECT now()
         ,DATEDIFF('2026-02-16', now()) -- 남은 날짜(D-day)
                                        -- 해당 날짜가 아직 안왔으면 양수, 지났으면 음수
         ,DATEDIFF( now(),'2026-02-16'); -- 지나간 날짜(D+day)
                                         -- 해당 날짜가 아직 안왔으면 음수, 지났으면 양수
SELECT now()
        ,TIMESTAMPDIFF(year, NOW(), '2027-01-30') -- 1
        ,TIMESTAMPDIFF(MONTH, NOW(), '2027-01-30') -- 12
        ,TIMESTAMPDIFF(DAY, NOW(), '2027-01-30') -- 373
        
SELECT now()
        ,DATEDIFF('2026-01-22 10:00', now()) -- 자정 기준
        ,TIMESTAMPDIFF(DAY, NOW(), '2026-01-22 10:00'); -- 만24시 기준
        
-- 며칠 후 계산
SELECT now()
      ,ADDDATE(now(), 5) -- 5일 후
      ,ADDDATE(now(), INTERVAL -50 day) -- 50일 전/후
      ,ADDDATE(now(), INTERVAL 50 month) --
      ,ADDDATE(now(), INTERVAL -50 hour);

select now()
	,LAST_DAY(NOW()) -- 이번달의 마지막 일(29,30,31)
	,DAYOFYEAR(NOW()) -- 올해 1월1일에서 몇번째 날인가? 21번째
	,MONTHNAME(NOW()) -- 이번달 영어 이름
	,WEEKDAY(NOW()); -- 월(0)~일(6) 오늘 수요일(2)
	
-- 형변환 함수
SELECT CAST('1' as unsigned); -- 부호 없는 정수로 변환 (0 이상만 정상적으로 표현 가능)                   
SELECT CAST('-1' as SIGNED); -- 부호 있는 정수로 변환 (음수, 양수 모두 가능)
	

SELECT CAST(2 as CHAR(1)); -- char형(문자한자) 한 자리로 변환. '2'
SELECT convert('1', unsigned); -- 숫자 1로 변환
SELECT convert(2, char(1));

-- cast() : ANSI 표준 ( 추천 )
-- conver() : MySQL 전용

-- 조건함수 ( JS 삼항연산자와 유사 )
SELECT if(10 > 20, '10', '20');
SELECT if(12500 * 450 > 500000, '초과달성', '미달성') as 달성여부;

-- null 체크 함수
SELECT IFNULL('123', 0); -- 첫항이 null이면, 2번째항을 반환,
                         -- null이 아니면 , 1번째항(그대로)를 반환.
SELECT IFNULL(null, 0); -- null이므로 0을 반환.
SELECT ifnull(null, 'null입니다.'); -- null이므로 'null입니다'반환
SELECT ifnull(null, '지역명없음'); -- null이므로 '지역명없음'반환

SELECT nullif(12*10,120); -- 1항과 2항이 같으면 null반환
SELECT nullif(12*10,1200); -- 1항과 2항이 같지 않으면, 1항을 반환

-- CASE WHEN구문( JS if else구문 유사 )
SELECT CASE 
	when 20 < 20 then '20보다 작음'
	when 20 < 30 then '30보다 작음'
    else '그 외의 수'
end as 결과;

-- 연습문제
-- 1. 다음 조건에 따라 고객 테이블에서 고객회사명과 전화번호를 
--    다른 형태로 보이도록 함수를 사용해봅시다. 
-- 고객회사명2와 전화번호2를 만드는 조건은 아래와 같습니다.

-- 조건
-- 1. 고객회사명2 : 기존 고객회사명 중 앞의 두 자리를 *로 변환한다.
-- 2. 전화번호2 : 기존 전화번호의 (xxx)xxx-xxxx 형식을 xxx-xxx-xxxx형식으로 변환한다.

-- 2. 다음 조건에 따라 주문 세부 테이블의 모든 컬럼과 주문금액, 할인금액, 실제 주문금액을 보이시오. 
-- 이때 모든 금액은 1의 단위에서 버림을 하고 10원 단위까지 보이시오.
-- 조건
-- 1. 주문금액: 주문수량 * 단가
-- 2. 할인금액 : 주문수량 * 단가 * 할인율
-- 3. 실주문금액 : 주문금액 - 할인금액

-- 3. 사원 테이블에서 전체 사원의 이름, 생일, 만나이, 입사일, 입사일수, 
-- 입사한 지 500일 후의 날짜를 보이시오.

-- 4. 고객 테이블에서 도시 컬럼의 데이터를 다음 조건에 따라 ‘대도시’와 ‘도시’로 구분하고, 
-- 마일리지 점수에 따라서 ‘VVIP’, ‘VIP’, ‘일반 고객’으로 구분하시오.
-- 조건
-- 1. 도시 구분: ‘특별시’나 ‘광역시’는 ‘대도시’로, 그 나머지 도시는 ‘도시’로 구분한다.
-- 2. 마일리지 구분 : 마일리지가 100,000점 이상이면 ‘VVIP고객’, 10,000점 이상이면 ‘VIP고객’, 그 나머지는 ‘일반고객’으로 구분한다



SELECT concat(repeat('*',2),(SUBSTR(고객회사명, 3))) as 고객회사명2
      ,replace(replace(전화번호,'(',''),')','-') as 전화번호2
FROM 고객

SELECT 주문번호, 제품번호, TRUNCATE(단가,-1) as 단가, 주문수량, 할인율
,TRUNCATE(주문수량 * 단가,-1) as 주문금액
,TRUNCATE(주문수량*단가*할인율,-1) as 할인금액
,TRUNCATE((주문수량*단가)-(주문수량*단가*할인율),-1) as 실주문금액
FROM 주문세부

-- CTE(WITH절) : 모든 DBMS에서 사용가능, 단 mysql 5.x대 사용불가
WITH 주문금액 AS(
     SELECT *, (주문수량*단가) AS 주문금액
     FROM 주문세부
),
할인금액 AS(
SELECT *, TRUNCATE(주문수량 * 단가 * 할인율, -1) AS 할인금액
FROM 주문금액
)
SELECT *, (주문금액 - 할인금액) AS 실주문금액
FROM 할인금액;
)


SELECT 이름, 생일
, TIMESTAMPDIFF(year, 생일, now())as 만나이, 입사일
, datediff(now(),입사일)as 입사일수
, adddate(입사일,interval 50 day)as '입사+500'
from 사원

SELECT 도시,
   CASE 
	WHEN 도시 like '%광역시' OR 도시 LIKE '%특별시' THEN '대도시'
ELSE '도시'
	END AS '도시 구분'
FROM 고객

SELECT 도시,
   CASE 
	WHEN 도시 like '%광역시' OR 도시 LIKE '%특별시' THEN '대도시'
ELSE '도시'
	END AS '도시 구분'
FROM 고객





-- CTRL + SHIFT + F ( 수동 포맷팅 )


