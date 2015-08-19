/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : pms1

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2015-07-03 12:38:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'SMS', 'SMS', null, '易美软通短信配置', null, null, null);
INSERT INTO `sys_config` VALUES ('2', 'SMS_SPECIAL_NO', '370413', 'SMS', '易美软通特服号', null, null, '1');
INSERT INTO `sys_config` VALUES ('3', 'SMS_KEY', 'fenlibao', 'SMS', '易美软通注册KEY', null, null, '1');
INSERT INTO `sys_config` VALUES ('4', 'SMS_AUTO_REGISTER', 'true', 'SMS', '易美软通自动注册', null, null, '1');
INSERT INTO `sys_config` VALUES ('5', 'SMS_SIGN', '【分利宝】', 'SMS', '易美软通签名', null, null, '1');
INSERT INTO `sys_config` VALUES ('6', 'SMS_SOFTWARE_SERIAL_NO', '6SDK-EMY-6688-KKWQP', 'SMS', '易美软通序列号', null, null, '1');
INSERT INTO `sys_config` VALUES ('7', 'SMS_PASSWORD', '439365', 'SMS', '易美软通密码', null, null, '1');

-- ----------------------------
-- Table structure for sms_charge_log
-- ----------------------------
DROP TABLE IF EXISTS `sms_charge_log`;
CREATE TABLE `sms_charge_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `cardNo` varchar(100) DEFAULT NULL COMMENT '充值卡号',
  `card_pass` varchar(100) DEFAULT NULL COMMENT '充值卡密码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `log_id` bigint(20) DEFAULT NULL COMMENT '充值log',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='短信充值记录';

-- ----------------------------
-- Table structure for sms_log
-- ----------------------------
DROP TABLE IF EXISTS `sms_log`;
CREATE TABLE `sms_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `software_serial_no` varchar(100) DEFAULT NULL,
  `target` varchar(100) DEFAULT NULL COMMENT '请求目标',
  `invoke` varchar(500) DEFAULT NULL COMMENT '执行方法',
  `argus` text COMMENT '执行参数',
  `code` varchar(20) DEFAULT NULL COMMENT '返回状态码',
  `err_source` varchar(100) DEFAULT NULL COMMENT '错误侧',
  `err_msg` varchar(100) DEFAULT NULL COMMENT '错误描述',
  `thing` varchar(100) DEFAULT NULL COMMENT '短信执行动作enum',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 COMMENT='短信记录';

-- ----------------------------
-- Table structure for sms_register_info
-- ----------------------------
DROP TABLE IF EXISTS `sms_register_info`;
CREATE TABLE `sms_register_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `software_serial_no` varchar(100) DEFAULT NULL COMMENT '序列号',
  `name` varchar(60) DEFAULT NULL COMMENT '企业名称',
  `link_man` varchar(20) DEFAULT NULL COMMENT '联系人姓名',
  `phone_num` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `mobile` varchar(15) DEFAULT NULL COMMENT '联系手机',
  `email` varchar(60) DEFAULT NULL COMMENT '电子邮件',
  `fax` varchar(20) DEFAULT NULL COMMENT '联系传真',
  `address` varchar(60) DEFAULT NULL COMMENT '公司地址',
  `postcode` varchar(20) DEFAULT NULL COMMENT '邮政编码',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  `log_id` bigint(20) DEFAULT NULL COMMENT 'sms日志记录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='注册企业信息';

-- ----------------------------
-- Table structure for sms_sent
-- ----------------------------
DROP TABLE IF EXISTS `sms_sent`;
CREATE TABLE `sms_sent` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `sms_task_id` bigint(20) DEFAULT NULL COMMENT '待发送短信id',
  `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  `sms_log_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='已发短信';

-- ----------------------------
-- Table structure for sms_sign
-- ----------------------------
DROP TABLE IF EXISTS `sms_sign`;
CREATE TABLE `sms_sign` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `software_serial_no` varchar(100) DEFAULT NULL COMMENT '序列号',
  `pwd` varchar(100) DEFAULT NULL COMMENT '密码',
  `special_no` varchar(100) DEFAULT NULL COMMENT '特服号',
  `key` varchar(100) DEFAULT NULL COMMENT '要注册的关键字',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `log_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='短信客服端注册信息';

-- ----------------------------
-- Table structure for sms_task
-- ----------------------------
DROP TABLE IF EXISTS `sms_task`;
CREATE TABLE `sms_task` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `mobiles` text COMMENT '手机号码',
  `content` text COMMENT '短信内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `send_status` varchar(10) DEFAULT NULL COMMENT '发送状态,W:未发送，Z:正在发送',
  `expire_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '过期时间',
  `sms_id` bigint(20) DEFAULT NULL COMMENT '短信自定义id',
  `log_id` bigint(20) DEFAULT NULL COMMENT '短信记录id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='待发短信';
