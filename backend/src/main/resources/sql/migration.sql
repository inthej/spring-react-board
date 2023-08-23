-- 20230823
create table t_env
(
    name varchar(10) not null comment '코드',
    val  varchar(20) not null comment '값',
    primary key (name)
) engine InnoDB DEFAULT charset=utf8 comment='환경값';

insert into t_env(name, val)
values ('MIGRATION', '20230823');