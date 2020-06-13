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
insert into `chapter`(id,course_id,name ) values(015,000,'蒸汽小飞机1');
insert into `chapter`(id,course_id,name ) values(016,000,'电动小马达1');
insert into `chapter`(id,course_id,name ) values(017,000,'蒸汽小火车1');
insert into `chapter`(id,course_id,name ) values(018,000,'佩奇老野猪1');
insert into `chapter`(id,course_id,name ) values(019,000,'红烧大鲤鱼1');
insert into `chapter`(id,course_id,name ) values(020,000,'火烤铜锣烧1');
insert into `chapter`(id,course_id,name ) values(021,000,'夏天凉白开1');
insert into `chapter`(id,course_id,name ) values(022,000,'11-16扛把子1');
insert into `chapter`(id,course_id,name ) values(023,000,'闪电绿壳龟1');
insert into `chapter`(id,course_id,name ) values(024,000,'种猪罗志祥1');
insert into `chapter`(id,course_id,name ) values(025,000,'云南彭于晏1');
insert into `chapter`(id,course_id,name ) values(026,000,'野猪罗志祥1');


insert into `test`(id,name ) values(3,'蒸汽小飞机')

# 创建小节
CREATE TABLE `course`.`Untitled`  (
    `id` char(8) NOT NULL COMMENT 'ID',
    `title` varchar(50) NULL COMMENT '标题',
    `course_id` char(8) NULL COMMENT '课程id',
    `chapter_id` char(8) NULL COMMENT '大章id',
    `video` varchar(200) NULL COMMENT '视频',
    `time` int(100) NULL COMMENT '时长',
    `charge` char(1) NULL COMMENT '是否收费',
    `sort` int(255) NULL COMMENT '顺序',
    `created_at` datetime NULL COMMENT '创建时间',
    `updated_at` datetime NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
);

#创建课程表
CREATE TABLE `course`.`course`  (
  `id` char(8) NOT NULL COMMENT '课程id',
  `name` varchar(50) NULL COMMENT '课程名',
  `summary` varchar(2000) NULL COMMENT '简介',
  `time` int(255) NULL COMMENT '时长（秒）',
  `price` decimal(8,2) NULL COMMENT '价格',
  `image` varchar(100) NULL COMMENT '封面',
  `level` char(5) NULL COMMENT '级别',
  `charge` char(5) NULL COMMENT '是否收费',
  `status` char(5) NULL COMMENT '课程状态',
  `enroll` integer(255) NULL COMMENT '报名人数',
  `sort` int(255) NULL COMMENT '顺序',
  `created_at` datetime NULL COMMENT '创建时间',
  `updated_at` datetime NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
);

CREATE TABLE `course`.`Untitled`  (
  `id` char(8) NOT NULL COMMENT 'id',
  `parent` char(8) NOT NULL COMMENT '父id',
  `name` varchar(50) NULL COMMENT '名称',
  `sort` int(255) NULL COMMENT '顺序',
  PRIMARY KEY (`id`)
);