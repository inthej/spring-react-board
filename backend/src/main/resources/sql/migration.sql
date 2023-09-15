-- 20230823
create table t_env
(
    name varchar(10) not null comment '코드',
    val  varchar(20) not null comment '값',
    primary key (name)
) engine InnoDB DEFAULT charset=utf8 comment='환경값';

insert into t_env(name, val)
values ('MIGRATION', '20230823');

-- 20230905
CREATE TABLE `board_reply`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '댓글 번호',
    `bid`         bigint                                                        NOT NULL COMMENT '게시물 번호',
    `content`     text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '내용',
    `writer`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '작성자',
    `password`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL COMMENT '비밀번호',
    `created_id`  bigint                                                                 DEFAULT NULL COMMENT '작성자id',
    `created_dt`  datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일자',
    `nodified_dt` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자',
    PRIMARY KEY (`id`),
    KEY           `bid` (`bid`),
    CONSTRAINT `board_reply_ibfk_1` FOREIGN KEY (`bid`) REFERENCES `board` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

update t_env set val = '20230906' where name = 'MIGRATION';

-- 20230916
alter table board_reply rename board_comment;
update t_env set val = '20230916' where name = 'MIGRATION'