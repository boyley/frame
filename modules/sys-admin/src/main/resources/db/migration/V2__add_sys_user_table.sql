/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : pms1

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2015-07-23 16:29:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_regex
-- ----------------------------
DROP TABLE IF EXISTS `sys_regex`;
CREATE TABLE `sys_regex` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(300) DEFAULT NULL COMMENT '权限名称',
  `regex` varchar(300) DEFAULT NULL COMMENT 'ant 的url表达式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='权限表达式信息,由技术人员管理，使用ant表达式';

-- ----------------------------
-- Records of sys_regex
-- ----------------------------
INSERT INTO `sys_regex` VALUES ('1', '普通用户权限', '/**');

-- ----------------------------
-- Table structure for sys_regex_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_regex_role`;
CREATE TABLE `sys_regex_role` (
  `regex_id` bigint(20) DEFAULT NULL COMMENT '权限表达式id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限角色,使用ant表达式配置权限角色之间的关系';

-- ----------------------------
-- Records of sys_regex_role
-- ----------------------------
INSERT INTO `sys_regex_role` VALUES ('1', '2');

-- ----------------------------
-- Table structure for sys_resc
-- ----------------------------
DROP TABLE IF EXISTS `sys_resc`;
CREATE TABLE `sys_resc` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(500) DEFAULT NULL COMMENT '权限url',
  `res_type` varchar(300) DEFAULT NULL COMMENT '资源类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Records of sys_resc
-- ----------------------------
INSERT INTO `sys_resc` VALUES ('1', 'relationship/list', '1');
INSERT INTO `sys_resc` VALUES ('2', 'rule/list', '1');
INSERT INTO `sys_resc` VALUES ('3', 'channel/list', '1');
INSERT INTO `sys_resc` VALUES ('4', 'history/list', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(300) DEFAULT NULL COMMENT '角色名称',
  `authority` varchar(300) DEFAULT NULL COMMENT '角色code',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'ROLE_ADMIN');
INSERT INTO `sys_role` VALUES ('2', '普通用户', 'ROLE_USER');

-- ----------------------------
-- Table structure for sys_role_resc
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resc`;
CREATE TABLE `sys_role_resc` (
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `resc_id` bigint(20) DEFAULT NULL COMMENT '资源id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源';

-- ----------------------------
-- Records of sys_role_resc
-- ----------------------------
INSERT INTO `sys_role_resc` VALUES ('1', '1');
INSERT INTO `sys_role_resc` VALUES ('1', '2');
INSERT INTO `sys_role_resc` VALUES ('1', '3');
INSERT INTO `sys_role_resc` VALUES ('2', '1');
INSERT INTO `sys_role_resc` VALUES ('2', '2');
INSERT INTO `sys_role_resc` VALUES ('2', '3');
INSERT INTO `sys_role_resc` VALUES ('1', '4');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(300) DEFAULT NULL,
  `account_non_expired` bit(1) DEFAULT NULL COMMENT '账号是否未过期',
  `account_non_locked` bit(1) DEFAULT NULL COMMENT '账号是否未被锁定',
  `credentials_non_expire` bit(1) DEFAULT NULL COMMENT '凭证是否为过期',
  `enabled` bit(1) DEFAULT NULL COMMENT '账号是否可用',
  `register_time` bigint DEFAULT NULL COMMENT '注册日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', '', '', '', '', '2015-06-23 12:48:44');
INSERT INTO `sys_user` VALUES ('2', 'user', 'user', '', '', '', '', '2015-06-23 12:48:39');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');



-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '组名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户组表';

-- ----------------------------
-- Records of sys_group
-- ----------------------------
INSERT INTO `sys_group` VALUES ('1', '技术组');

-- ----------------------------
-- Table structure for sys_group_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_group_role`;
CREATE TABLE `sys_group_role` (
  `group_id` bigint(20) DEFAULT NULL COMMENT '用户组id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组-角色表';

-- ----------------------------
-- Records of sys_group_role
-- ----------------------------
INSERT INTO `sys_group_role` VALUES ('1', '3');
INSERT INTO `sys_group_role` VALUES ('1', '4');
INSERT INTO `sys_group_role` VALUES ('1', '5');
INSERT INTO `sys_group_role` VALUES ('1', '2');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(80) DEFAULT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单',
  `resc_id` bigint(20) DEFAULT NULL COMMENT '所属资源id',
  `icon` varchar(256) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_group`;
CREATE TABLE `sys_user_group` (
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `group_id` bigint(20) DEFAULT NULL COMMENT '用户组id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-用户组表';

-- ----------------------------
-- Records of sys_user_group
-- ----------------------------
INSERT INTO `sys_user_group` VALUES ('1', '1');