USE mydb;

-- 회원가입 테이블 만들기

DROP TABLE member_security;

CREATE TABLE member_security (
                                 id 			BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                 username    VARCHAR(255) NOT NULL, -- 아이디
                                 password    VARCHAR(255) NOT NULL, -- 암호
                                 nick_name   VARCHAR(255),
                                 user_role   VARCHAR(255) DEFAULT 'ROLE_USER',
                                 join_date	DATE DEFAULT (CURRENT_DATE())
)

    -- 암호는 Bcrypt 암호화하여 저장한다.
-- bcrypt-generator.com 강도 10으로 1234로 만들기
-- $2a$10$DZE7bb0LdnxH5/9XJF9TDeB.XA0PUCrMypRFPekahCUlaAgtuy8yq

    INSERT INTO member_security VALUES
	(0, 'hong', '$2a$10$DZE7bb0LdnxH5/9XJF9TDeB.XA0PUCrMypRFPekahCUlaAgtuy8yq', '홍길동', 'ROLE_USER', '2026-03-12')

SELECT * FROM member_security;