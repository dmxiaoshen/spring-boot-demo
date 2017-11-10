CREATE DATABASE IF NOT EXISTS `spring_boot_demo`;

USE `spring_boot_demo`;

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` CHAR(32) COLLATE utf8_bin NOT NULL,
  `name` VARCHAR(64) COLLATE utf8_bin DEFAULT NULL,
  `age` TINYINT(3) UNSIGNED DEFAULT NULL,
  `status` CHAR(1) COLLATE utf8_bin DEFAULT NULL COMMENT '状态 0 启用 1 停用',
  `birthday` DATE DEFAULT NULL,
  `information` VARCHAR(128) COLLATE utf8_bin DEFAULT NULL COMMENT '一些信息',
  `remarks` VARCHAR(256) COLLATE utf8_bin DEFAULT NULL,
  `create_by` CHAR(32) COLLATE utf8_bin DEFAULT NULL,
  `update_by` CHAR(32) COLLATE utf8_bin DEFAULT NULL,
  `create_time` DATETIME DEFAULT NULL,
  `update_time` DATETIME DEFAULT NULL,
  `del_flag` CHAR(1) COLLATE utf8_bin DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;