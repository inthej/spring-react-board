-- 20230823
CREATE TABLE t_env
(
    name varchar(10) not null comment '코드',
    val  varchar(20) not null comment '값',
    primary key (name)
) engine InnoDB DEFAULT charset=utf8 comment='환경값';

INSERT INTO t_env(name, val)
VALUES ('MIGRATION', '20230823');

-- 20230905
CREATE TABLE `board_comment`
(
    `no`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '댓글 번호',
    `bno`         bigint                                                        NOT NULL COMMENT '게시물 번호',
    `content`     text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '내용',
    `writer`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '작성자',
    `password`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL COMMENT '비밀번호',
    `created_id`  bigint                                                                 DEFAULT NULL COMMENT '작성자id',
    `created_dt`  datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일자',
    `nodified_dt` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',
    PRIMARY KEY (`no`),
    KEY           `bno` (`bno`),
    CONSTRAINT `board_comment_idfk_1` FOREIGN KEY (`bno`) REFERENCES `board` (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

UPDATE t_env SET val = '20230906' WHERE name = 'MIGRATION';

-- 20230916
ALTER TABLE board_reply rename board_comment;
UPDATE t_env SET val = '20230916' WHERE name = 'MIGRATION'

-- 20230918
ALTER TABLE board_comment CHANGE nodified_dt modified_dt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자';
UPDATE t_env SET val = '20230918' WHERE name = 'MIGRATION';

-- 20231028
ALTER TABLE board_comment CHANGE id no BIGINT NOT NULL AUTO_INCREMENT;
ALTER TABLE board_comment CHANGE bid bno BIGINT NOT NULL AUTO_INCREMENT;
ALTER TABLE board_comment DROP FOREIGN KEY board_comment_ibfk_1;
ALTER TABLE board CHANGE id no BIGINT;
ALTER TABLE board_comment ADD CONSTRAINT board_comment_idfk_1 FOREIGN KEY (bno) REFERENCES board(no);
UPDATE t_env SET val = '20231028' WHERE name = 'MIGRATION';