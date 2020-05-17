CREATE TABLE `course`.`Untitled`  (
  `id` char(8) NOT NULL COMMENT 'ID',
  `course_id` char(8) NULL COMMENT '课程id',
  `name` varchar(50) NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4;


insert into `test`(id,name ) values(3,'蒸汽小飞机')