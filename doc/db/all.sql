CREATE TABLE `course`.`Untitled`  (
  `id` char(8) NOT NULL COMMENT 'ID',
  `course_id` char(8) NULL COMMENT '课程id',
  `name` varchar(50) NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4;

insert into `chapter`(id,course_id,name ) values(003,000,'蒸汽小飞机');
insert into `chapter`(id,course_id,name ) values(004,000,'电动小马达');
insert into `chapter`(id,course_id,name ) values(005,000,'蒸汽小火车');
insert into `chapter`(id,course_id,name ) values(006,000,'佩奇老野猪');
insert into `chapter`(id,course_id,name ) values(007,000,'红烧大鲤鱼');
insert into `chapter`(id,course_id,name ) values(008,000,'火烤铜锣烧');
insert into `chapter`(id,course_id,name ) values(009,000,'夏天凉白开');
insert into `chapter`(id,course_id,name ) values(010,000,'11-16扛把子');
insert into `chapter`(id,course_id,name ) values(011,000,'闪电绿壳龟');
insert into `chapter`(id,course_id,name ) values(012,000,'种猪罗志祥');
insert into `chapter`(id,course_id,name ) values(013,000,'云南彭于晏');
insert into `chapter`(id,course_id,name ) values(014,000,'野猪罗志祥');


insert into `test`(id,name ) values(3,'蒸汽小飞机')