CREATE TABLE `board`
(
    `no`          bigint unsigned                                               NOT NULL AUTO_INCREMENT COMMENT '번호',
    `title`       varchar(255)                                                  NOT NULL COMMENT '제목',
    `content`     mediumtext                                                    NOT NULL COMMENT '내용',
    `writer`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '작성자',
    `password`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL COMMENT '비밀번호',
    `view_count`  bigint                                                        NOT NULL DEFAULT '0' COMMENT '조회수',
    `created_id`  bigint                                                                 DEFAULT NULL COMMENT '작성자id',
    `created_dt`  datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일자',
    `modified_dt` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',
    PRIMARY KEY (`no`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

create table t_env
(
    name varchar(10) not null comment '코드',
    val  varchar(20) not null comment '값',
    primary key (name)
) engine InnoDB
  DEFAULT charset = utf8 comment ='환경값';

CREATE TABLE `board_comment`
(
    `no`          bigint unsigned                                               NOT NULL AUTO_INCREMENT COMMENT '댓글 번호',
    `bno`         bigint                                                        NOT NULL COMMENT '게시물 번호',
    `content`     text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci         NOT NULL COMMENT '내용',
    `writer`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '작성자',
    `password`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL COMMENT '비밀번호',
    `created_id`  bigint                                                                 DEFAULT NULL COMMENT '작성자id',
    `created_dt`  datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일자',
    `modified_dt` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',
    PRIMARY KEY (`no`),
    KEY `bid` (`bno`),
    CONSTRAINT `board_comment_ibfk_1` FOREIGN KEY (`bno`) REFERENCES `board` (`no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;