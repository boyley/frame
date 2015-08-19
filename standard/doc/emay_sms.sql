/*
Navicat MySQL Data Transfer

Source Server         : 192.168.22.236
Source Server Version : 50625
Source Host           : 192.168.22.236:3306
Source Database       : emay_sms

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2015-08-19 11:54:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for schema_version
-- ----------------------------
DROP TABLE IF EXISTS `schema_version`;
CREATE TABLE `schema_version` (
  `version_rank` int(11) NOT NULL,
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) NOT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`version`),
  KEY `schema_version_vr_idx` (`version_rank`),
  KEY `schema_version_ir_idx` (`installed_rank`),
  KEY `schema_version_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schema_version
-- ----------------------------
INSERT INTO `schema_version` VALUES ('1', '1', '1', 'add config table', 'SQL', 'V1__add_config_table.sql', '-358448610', 'develop', '2015-08-19 11:50:09', '370', '1');
INSERT INTO `schema_version` VALUES ('2', '2', '4', 'add sms table', 'SQL', 'V4__add_sms_table.sql', '-2139119654', 'develop', '2015-08-19 11:50:12', '3101', '1');

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
-- Records of sms_charge_log
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 COMMENT='短信记录';

-- ----------------------------
-- Records of sms_log
-- ----------------------------
INSERT INTO `sms_log` VALUES ('74', '6SDK-EMY-6688-KKWQP', 'com.fenlibao.p2p.sms.service.impl.SmsApiImpl', 'public java.io.Serializable com.fenlibao.p2p.sms.service.impl.SmsApiImpl.registEx(java.lang.String)', '[\"439365\"]', '0', '服务器端', '成功', 'REGISTER_EX', '2015-08-19 11:50:20');
INSERT INTO `sms_log` VALUES ('75', '6SDK-EMY-6688-KKWQP', 'com.fenlibao.p2p.sms.service.impl.SmsApiImpl', 'public java.io.Serializable com.fenlibao.p2p.sms.service.impl.SmsApiImpl.sendSMS(java.lang.String[],java.lang.String,int)', '[[\"13751763759\"],\"【分利宝】header测试\",5]', '0', '服务器端', '成功', 'SEND_SMS', '2015-08-19 11:52:02');
INSERT INTO `sms_log` VALUES ('76', '6SDK-EMY-6688-KKWQP', 'com.fenlibao.p2p.sms.service.impl.SmsApiImpl', 'public java.io.Serializable com.fenlibao.p2p.sms.service.impl.SmsApiImpl.sendSMS(java.lang.String[],java.lang.String,int)', '[[\"13822117771\"],\"【分利宝】header测试\",5]', '0', '服务器端', '成功', 'SEND_SMS', '2015-08-19 11:52:58');
INSERT INTO `sms_log` VALUES ('77', '6SDK-EMY-6688-KKWQP', 'com.fenlibao.p2p.sms.service.impl.SmsApiImpl', 'public java.io.Serializable com.fenlibao.p2p.sms.service.impl.SmsApiImpl.sendSMS(java.lang.String[],java.lang.String,int)', '[[\"13660448298\"],\"【分利宝】header测试\",5]', '0', '服务器端', '成功', 'SEND_SMS', '2015-08-19 11:53:51');

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
-- Records of sms_register_info
-- ----------------------------

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
-- Records of sms_sent
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='短信客服端注册信息';

-- ----------------------------
-- Records of sms_sign
-- ----------------------------
INSERT INTO `sms_sign` VALUES ('10', '6SDK-EMY-6688-KKWQP', '439365', '370413', 'fenlibao', '2015-08-19 11:50:20', '74');

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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='待发短信';

-- ----------------------------
-- Records of sms_task
-- ----------------------------
INSERT INTO `sms_task` VALUES ('35', '[13751763759]', '【分利宝】header测试', '2015-08-19 11:52:03', 'Z', '2015-08-19 11:52:03', '1439956322997', '75');
INSERT INTO `sms_task` VALUES ('36', '[13822117771]', '【分利宝】header测试', '2015-08-19 11:52:58', 'Z', '2015-08-19 11:52:58', '1439956378955', '76');
INSERT INTO `sms_task` VALUES ('37', '[13660448298]', '【分利宝】header测试', '2015-08-19 11:53:51', 'Z', '2015-08-19 11:53:51', '1439956431812', '77');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `key` varchar(128) DEFAULT NULL COMMENT '菜单KEY值',
  `value` varchar(128) DEFAULT NULL COMMENT 'value',
  `type` varchar(100) DEFAULT NULL COMMENT '菜单的响应动作类型',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `url` varchar(256) DEFAULT NULL COMMENT '网页链接，用户点击菜单可打开链接',
  `media_id` varchar(50) DEFAULT NULL COMMENT '调用新增永久素材接口返回的合法media_id',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父节点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='配置信息';

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
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', '', '', '', '', '2015-06-23 12:48:44');
INSERT INTO `sys_user` VALUES ('2', 'user', 'user', '', '', '', '', '2015-06-23 12:48:39');
