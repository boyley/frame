/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : frame

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2015-08-19 11:48:47
*/

SET FOREIGN_KEY_CHECKS=0;

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
INSERT INTO `sys_config` VALUES ('8', 'WEIXIN', 'WEIXIN', null, '微信配置', null, null, null);
INSERT INTO `sys_config` VALUES ('9', 'WX_NAME', '微信二维码', 'WEIXIN', '微信二维码名称', null, null, '8');
INSERT INTO `sys_config` VALUES ('10', 'WX_SUFFIX', '.JPG', 'WEIXIN', '微信二维码后缀', null, null, '8');
INSERT INTO `sys_config` VALUES ('11', 'SCENE_ZHAOPIN', 'SCENE_ZHAOPIN', 'WEIXIN', '二维码招聘场景', null, null, '8');
INSERT INTO `sys_config` VALUES ('12', 'WX_TOKEN_KEY', 'fenlibao', 'WEIXIN', '微信Token', null, null, '8');
INSERT INTO `sys_config` VALUES ('13', 'WX_APP_ID', 'wx21b7659ce16b13d9', 'WEIXIN', 'AppID(应用ID)', null, null, '8');
INSERT INTO `sys_config` VALUES ('14', 'WX_APP_SECRET', '096da6c0e64e509a2bedf080c3bd45ef', 'WEIXIN', 'AppSecret(应用密钥)', null, null, '8');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

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
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', '', '', '', '', '2015-06-23 12:48:44');
INSERT INTO `sys_user` VALUES ('2', 'user', 'user', '', '', '', '', '2015-06-23 12:48:39');

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
-- Table structure for weixin_fans
-- ----------------------------
DROP TABLE IF EXISTS `weixin_fans`;
CREATE TABLE `weixin_fans` (
  `id` varchar(50) NOT NULL,
  `subscribe` int(11) DEFAULT NULL COMMENT '用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。',
  `openid` varchar(64) DEFAULT NULL COMMENT '用户的标识，对当前公众号唯一',
  `nickname` varchar(64) DEFAULT NULL COMMENT '用户的昵称',
  `sex` int(11) DEFAULT NULL COMMENT '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `city` varchar(64) DEFAULT NULL COMMENT '用户所在城市',
  `country` varchar(64) DEFAULT NULL COMMENT '用户所在国家',
  `province` varchar(64) DEFAULT NULL COMMENT '用户所在省份',
  `language` varchar(64) DEFAULT NULL COMMENT '用户的语言',
  `headimgurl` varchar(256) DEFAULT NULL COMMENT '用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。',
  `subscribe_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间',
  `unionid` varchar(64) DEFAULT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段',
  `remark` varchar(200) DEFAULT NULL COMMENT '公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注',
  `groupid` int(11) DEFAULT NULL COMMENT '用户所在的分组ID',
  `privilege` varchar(64) DEFAULT NULL COMMENT '用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）',
  `errcode` int(11) DEFAULT NULL COMMENT '返回编码',
  `errmsg` varchar(320) DEFAULT NULL COMMENT '错误消息',
  `token_id` varchar(50) DEFAULT NULL COMMENT 'token外键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信粉丝';

-- ----------------------------
-- Records of weixin_fans
-- ----------------------------

-- ----------------------------
-- Table structure for weixin_media
-- ----------------------------
DROP TABLE IF EXISTS `weixin_media`;
CREATE TABLE `weixin_media` (
  `id` varchar(64) NOT NULL,
  `message_id` varchar(64) DEFAULT NULL COMMENT '所属消息id',
  `type` varchar(64) DEFAULT NULL COMMENT '媒体类型enum',
  `media_id` varchar(64) DEFAULT NULL COMMENT '通过素材管理接口上传多媒体文件，得到的id',
  `title` varchar(64) DEFAULT NULL COMMENT '消息的标题',
  `description` varchar(64) DEFAULT NULL COMMENT '消息的描述',
  `music_url` varchar(256) DEFAULT NULL COMMENT '音乐链接',
  `hqmusic_url` varchar(256) DEFAULT NULL COMMENT '高质量音乐链接，WIFI环境优先使用该链接播放音乐',
  `thumb_media_id` varchar(64) DEFAULT NULL COMMENT '缩略图的媒体id，通过上传多媒体文件，得到的id',
  `pic_url` varchar(256) DEFAULT NULL COMMENT '图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致',
  `url` text COMMENT '点击图文消息跳转链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='媒体消息\r\n';

-- ----------------------------
-- Records of weixin_media
-- ----------------------------

-- ----------------------------
-- Table structure for weixin_message
-- ----------------------------
DROP TABLE IF EXISTS `weixin_message`;
CREATE TABLE `weixin_message` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` varchar(64) DEFAULT NULL COMMENT 'appID（应用ID）',
  `to_user_name` varchar(64) DEFAULT NULL COMMENT '接收方帐号（收到的OpenID）',
  `from_user_name` varchar(64) DEFAULT NULL COMMENT '发送方帐号（一个OpenID）',
  `create_time` bigint(20) DEFAULT NULL COMMENT '消息创建时间 （整型）',
  `msg_id` varchar(64) DEFAULT NULL COMMENT '消息id，64位整型',
  `msg_type` varchar(64) DEFAULT NULL COMMENT '消息类型，event',
  `event` varchar(64) DEFAULT NULL COMMENT '事件类型',
  `content` text COMMENT '消息内容',
  `pic_url` varchar(256) DEFAULT NULL COMMENT '消息内容格式',
  `media_id` varchar(64) DEFAULT NULL COMMENT '图片消息媒体id，可以调用多媒体文件下载接口拉取数据。',
  `format` varchar(64) DEFAULT NULL COMMENT '语音格式，如amr，speex等',
  `thumb_media_id` varchar(64) DEFAULT NULL COMMENT '视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据',
  `location_X` varchar(64) DEFAULT NULL COMMENT '地理位置维度',
  `location_Y` varchar(64) DEFAULT NULL COMMENT '地理位置经度',
  `scale` varchar(10) DEFAULT NULL COMMENT '地图缩放大小',
  `label` varchar(64) DEFAULT NULL COMMENT '地理位置信息',
  `title` varchar(64) DEFAULT NULL COMMENT '消息标题',
  `description` varchar(64) DEFAULT NULL COMMENT '消息描述',
  `url` varchar(500) DEFAULT NULL COMMENT '消息链接',
  `event_key` varchar(64) DEFAULT NULL COMMENT '事件KEY值',
  `ticket` varchar(300) DEFAULT NULL COMMENT '二维码的ticket',
  `latitude` varchar(64) DEFAULT NULL COMMENT '地理位置纬度(上报地理)',
  `longitude` varchar(64) DEFAULT NULL COMMENT '地理位置经度(上报地理)',
  `precision` varchar(64) DEFAULT NULL COMMENT '地理位置精度',
  `recognition` text COMMENT '语音识别结果',
  `article_count` int(11) DEFAULT NULL COMMENT '图文消息个数',
  `uniq_id` varchar(64) DEFAULT NULL COMMENT '商户自己内部ID',
  `poi_id` varchar(64) DEFAULT NULL COMMENT '微信的门店ID',
  `result` varchar(64) DEFAULT NULL COMMENT '审核结果',
  `msg` text COMMENT '成功的通知信息，或审核失败的驳回理由',
  `card_id` varchar(64) DEFAULT NULL COMMENT '卡券ID',
  `give_by_friend` bit(1) DEFAULT NULL COMMENT '是否为转赠，1代表是，0代表否。',
  `user_card_code` varchar(64) DEFAULT NULL COMMENT 'code序列号。自定义code及非自定义code的卡券被领取后都支持事件推送。',
  `old_user_card_code` varchar(64) DEFAULT NULL COMMENT '转赠前的code序列号',
  `outer_id` varchar(64) DEFAULT NULL COMMENT '领取场景值，用于领取渠道数据统计。可在生成二维码接口及添加JS API接口中自定义该字段的整型值。',
  `consume_source` varchar(64) DEFAULT NULL COMMENT '核销来源。支持开发者统计API核销（FROM_API）、公众平台核销（FROM_MP）、卡券商户助手核销（FROM_MOBILE_HELPER）（核销员微信号）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='微信交互的消息记录';

-- ----------------------------
-- Records of weixin_message
-- ----------------------------
INSERT INTO `weixin_message` VALUES ('1', 'wx21b7659ce16b13d9', 'gh_204badbf0fa2', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', '1439891785', '6184288126564169957', 'text', null, '好好', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `weixin_message` VALUES ('2', 'wx21b7659ce16b13d9', 'gh_204badbf0fa2', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', '1439891873', '6184288504521292036', 'image', null, null, 'http://mmbiz.qpic.cn/mmbiz/uicwcYgT2KsQncTezBFsIia4WZpCxoRgiaav9Prjjf4VDeW7NSjfMf2b8EibEBZm7UaD9IdygPK61QQUm4dCibia8biaA/0', '2wxG4KQ4jf8RC40DD8YZBvxnSLxmUka-ljJBxLXpXQQchrMoVpIhpYBscLi1MIC7', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `weixin_message` VALUES ('3', 'wx21b7659ce16b13d9', 'gh_204badbf0fa2', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', '1439891943', '6184288805169002765', 'voice', null, null, null, 'AijPoGjgBxUd6jX3dJGIzC3VPZ-T1jfCfQbm9dqyRSrYOeamny05cHRyMdMI--OU', 'amr', null, null, null, null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `weixin_message` VALUES ('4', 'wx21b7659ce16b13d9', 'gh_204badbf0fa2', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', '1439892227', '6184290024939714884', 'location', null, null, null, null, null, null, '23.118259', '113.243896', '16', '广东省广州市荔湾区华林', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `weixin_message` VALUES ('5', 'wx21b7659ce16b13d9', 'gh_204badbf0fa2', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', '1439892301', '6184290342767294810', 'text', null, 'www.qq.com', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `weixin_message` VALUES ('6', 'wx21b7659ce16b13d9', 'gh_204badbf0fa2', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', '1439892407', '6184290798033828219', 'image', null, null, 'http://mmbiz.qpic.cn/mmbiz/uicwcYgT2KsQncTezBFsIia4WZpCxoRgiaaticzv5GOMEbvZ80oWbEwzXsWoV3yIbt07vocpOMU1jDqDTaU4k6QiaEw/0', 'Uj7b6GM62Xc02Xk33J2BAdDavhT4Dc6xuzETOTLfW6tjX6dG6YIsz0IXV2vBg15l', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `weixin_message` VALUES ('7', 'wx21b7659ce16b13d9', 'gh_204badbf0fa2', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', '1439892429', '6184290892523108737', 'link', null, null, null, null, null, null, null, null, null, null, 'asad', 'asad', 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx61bd2c8875eff596&redirect_uri=http://fenlibao.tunnel.mobi/zhaopin/olHvdtxbz0odpHay_JWYif8bZVJA/48?redirectUrl=http://www.baidu.com&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `weixin_message` VALUES ('8', 'wx21b7659ce16b13d9', 'gh_204badbf0fa2', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', '1439892471', '6184291072911735183', 'link', null, null, null, null, null, null, null, null, null, null, '事实证明：分利网值得加入！我们在拉勾招牛人！', '我们公司正在拉勾上招募小伙伴，感兴趣的就戳进来看看吧！', 'http://c.lagou.com/cs/march/show/58690.html', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `weixin_message` VALUES ('9', 'wx21b7659ce16b13d9', 'gh_204badbf0fa2', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', '1439952479', '6184548805309243422', 'text', null, 'http://fenlibao.tunnel.mobi/weixin/oauthsns?view=zhaopin/zhaopin&code=041187fdb4f05de6782ec5e32aaa3c0f&state=fromUserName%3Do5D9TswsLX4QVk6Udi7Aeb0gX024%2Cid%3D61%2Credirect%3Dhttp%3A%2F%2Fwww.baidu.com', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for weixin_qrcode
-- ----------------------------
DROP TABLE IF EXISTS `weixin_qrcode`;
CREATE TABLE `weixin_qrcode` (
  `id` varchar(50) NOT NULL,
  `scene_name` varchar(50) DEFAULT NULL COMMENT '二维码场景,指定该二维码是做什么应用的，招聘，广告，登陆，等等，场景管理',
  `ticket_id` varchar(50) DEFAULT NULL COMMENT 'ticket外键',
  `create_time` bigint(20) DEFAULT NULL COMMENT '二维码的创建时间',
  `bytes` longblob COMMENT '二进制内容',
  `suffix` varchar(30) DEFAULT NULL COMMENT '文件后缀',
  `name` varchar(64) DEFAULT NULL COMMENT '文件名称',
  `enable` bit(1) DEFAULT NULL COMMENT '如果该二维码已经被引用后将变得不可以，如果是临时二维码需要判断类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='二维码';

-- ----------------------------
-- Records of weixin_qrcode
-- ----------------------------

-- ----------------------------
-- Table structure for weixin_ticket
-- ----------------------------
DROP TABLE IF EXISTS `weixin_ticket`;
CREATE TABLE `weixin_ticket` (
  `id` varchar(50) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `ticket` varchar(300) DEFAULT NULL,
  `expire_seconds` int(11) DEFAULT NULL COMMENT '二维码的有效时间，以秒为单位。',
  `expires_in` int(11) DEFAULT NULL COMMENT 'jsapi_adk ticket有效时间',
  `url` varchar(300) DEFAULT NULL COMMENT '二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片',
  `type` varchar(20) DEFAULT NULL COMMENT 'ticket类型',
  `code` varchar(20) DEFAULT NULL COMMENT 'use_custom_code字段为true的卡券必须填写，非自定义code不必填写。',
  `card_id` varchar(32) DEFAULT NULL COMMENT '卡券ID',
  `openid` varchar(32) DEFAULT NULL COMMENT '指定领取者的openid，只有该用户能领取。bind_openid字段为true的卡券必须填写，非指定openid不必填写。',
  `unique_code` bit(1) DEFAULT NULL COMMENT '指定下发二维码，生成的二维码随机分配一个code，领取后不可再次扫描。填写true或false。默认false。',
  `outer_id` int(11) DEFAULT NULL COMMENT '领取场景值，用于领取渠道的数据统计，默认值为0，字段类型为整型，长度限制为60位数字。用户领取卡券后触发的事件推送中会带上此自定义场景值。',
  `errcode` int(11) DEFAULT NULL COMMENT '返回编码',
  `errmsg` varchar(320) DEFAULT NULL COMMENT '错误消息',
  `token_id` varchar(50) DEFAULT NULL COMMENT 'token外键',
  `scene_id` int(11) DEFAULT NULL COMMENT '场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）',
  `scene_str` char(10) DEFAULT NULL COMMENT '场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信ticket';

-- ----------------------------
-- Records of weixin_ticket
-- ----------------------------
INSERT INTO `weixin_ticket` VALUES ('990ad2a2-0dff-49f3-b553-6d6bae75a93d', '1439953224517', 'sM4AOVdWfPE4DxkXGEs8VJdbP7zp7g7WStq7jYWhpmKyI6_aM8o4ZX7Hj8aEjZ3cMlp_-zT-MUcsfseJsXxF9A', null, '7200', null, 'JSAPI_TICKET', null, null, null, null, null, '0', 'ok', '617de50d-5058-4c1e-9d4b-6792203dd683', null, null);

-- ----------------------------
-- Table structure for weixin_token
-- ----------------------------
DROP TABLE IF EXISTS `weixin_token`;
CREATE TABLE `weixin_token` (
  `id` varchar(50) NOT NULL,
  `access_token` varchar(600) DEFAULT NULL COMMENT 'access_token凭证',
  `openid` varchar(300) DEFAULT NULL COMMENT '用户标识',
  `expires_in` int(11) DEFAULT NULL COMMENT 'access_token接口调用凭证超时时间，单位（秒）',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `refresh_token` varchar(600) DEFAULT NULL COMMENT '用户刷新access_token',
  `scope` varchar(600) DEFAULT NULL COMMENT 'scope',
  `unionid` varchar(600) DEFAULT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）',
  `type` varchar(600) DEFAULT NULL COMMENT '类型:网页和非网页的token',
  `errcode` int(11) DEFAULT NULL COMMENT '返回编码',
  `errmsg` varchar(320) DEFAULT NULL COMMENT '错误消息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信token';

-- ----------------------------
-- Records of weixin_token
-- ----------------------------
INSERT INTO `weixin_token` VALUES ('0d23e5a0-488a-4e32-a0bb-e3f09284e4ef', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsy9DfijE4zNrne_l01IiOEQkRZjynUJfr6wuFVGb9HiZ69rm_XbfmFgHMWYRniPwE7pBBw5DrWEq869uYwibVsDIoED0tFfkBKl6y1Xln5EXg', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', '7200', '1439952481603', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsy9DfijE4zNrne_l01IiOEQBId2G0_pEqKHeRA5Sbr0mz1NfSCHSvphWW5S0uy_z8RdTVWl94QloqafzBaSuhwpYWmTpjbUVMjN2xF3Efz4tw', 'snsapi_userinfo', 'ow9vet9TfunWtRG_3Ab6wv3-VziU', 'OAUTH2_ACCESS_TOKEN', '0', null);
INSERT INTO `weixin_token` VALUES ('11fd8a6b-6582-459b-aca3-ff170aeae02f', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsy9DfijE4zNrne_l01IiOEQJlNuawki4I-ZWPGKyDb506PVab9wU7_bKQzEPw3aVoERCjBtOqntmaq9Z5fCvsZbvyw88QHWk9WpVOTLf8L9fg', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', '7200', '1439953528008', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsy9DfijE4zNrne_l01IiOEQjM3jQrbtTLwk0G5l6pt-1BRy-GDuw7Yg8xFuXrv2LDKsFbDjQ8FRwKQCno8bu2sYerxR_1g7KEBo9r22LlpKtg', 'snsapi_userinfo', 'ow9vet9TfunWtRG_3Ab6wv3-VziU', 'OAUTH2_ACCESS_TOKEN', '0', null);
INSERT INTO `weixin_token` VALUES ('137445b6-3d2d-4185-93c6-295b7cb86747', null, null, null, '1439952878814', null, null, null, 'OAUTH2_ACCESS_TOKEN', '40029', 'req id: AIZASA0880ns42, invalid code');
INSERT INTO `weixin_token` VALUES ('13cec246-74e2-45d6-acf1-d588f95e8a04', null, null, null, '1439952404006', null, null, null, 'OAUTH2_ACCESS_TOKEN', '40029', 'req id: I3IWda0405ns69, invalid code');
INSERT INTO `weixin_token` VALUES ('1a1417f8-de4b-4762-ad2a-3f4faa115277', null, null, null, '1439952458381', null, null, null, 'OAUTH2_ACCESS_TOKEN', '40029', 'req id: tmMWuA0460ns22, invalid code');
INSERT INTO `weixin_token` VALUES ('1bb98ff7-5638-4915-8aa5-3acfb578a384', null, null, null, '1439952480812', null, null, null, 'OAUTH2_ACCESS_TOKEN', '40029', 'req id: 0482ns59, invalid code');
INSERT INTO `weixin_token` VALUES ('1ebe5ec0-da0a-42b4-8338-d5b11b1e3fef', null, null, null, '1439953524941', null, null, null, 'OAUTH2_ACCESS_TOKEN', '40029', 'req id: n7Xo3a0526ns55, invalid code');
INSERT INTO `weixin_token` VALUES ('3be9b13d-8aa0-4037-9c6a-99c79ea28c39', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsy9DfijE4zNrne_l01IiOEQUrEBbpemRXFKYKZw1zDM3N6Cvh2CfMjIRc2Lkvu9qxWtYMW5sAIP_JDrnBy_mPTh5LOIXkQtUYmwHoevHXbnwQ', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', '7200', '1439953222232', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsy9DfijE4zNrne_l01IiOEQKsGPOdkeSv71no0WPaEi0GnGjJpPAqmtBAA3GTdSz2hAT8PSTcHNkd5aELEq4vAnK7yQScxSsqxFovBbBT2Tbg', 'snsapi_userinfo', 'ow9vet9TfunWtRG_3Ab6wv3-VziU', 'OAUTH2_ACCESS_TOKEN', '0', null);
INSERT INTO `weixin_token` VALUES ('409785e5-9aa7-45bf-b714-be5d2eb8f737', null, null, null, '1439953101958', null, null, null, 'OAUTH2_ACCESS_TOKEN', '40029', 'req id: rYpRja0103ns31, invalid code');
INSERT INTO `weixin_token` VALUES ('417aa40e-bf06-4423-badb-0274d76298c0', null, null, null, '1439953245081', null, null, null, 'OAUTH2_ACCESS_TOKEN', '40029', 'req id: 0246ns50, invalid code');
INSERT INTO `weixin_token` VALUES ('4635e047-7cd0-4736-8857-9469d8925519', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsy9DfijE4zNrne_l01IiOEQ3yX7MlB0Gwcupn-mxje1hWIqcIHp13IgUqkd70d8Hu5tXUBoXsMh5xWA15MygFQ06zp3WhogtWEMNUvqwfiiTQ', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', '7200', '1439952461314', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsy9DfijE4zNrne_l01IiOEQ7MDFr1VMWVobodVzbwq0u83apsL_JZ4ZVs1yicWd9jOnmM5-EkOS0v9hS8rRAw3uit60Yot4JBOcgmPJyTEJjA', 'snsapi_userinfo', 'ow9vet9TfunWtRG_3Ab6wv3-VziU', 'OAUTH2_ACCESS_TOKEN', '0', null);
INSERT INTO `weixin_token` VALUES ('48331ad8-c1d5-4282-889b-54dccc4f0a8f', null, null, null, '1439953845914', null, null, null, 'OAUTH2_ACCESS_TOKEN', '40029', 'req id: DcIrUa0847ns30, invalid code');
INSERT INTO `weixin_token` VALUES ('617de50d-5058-4c1e-9d4b-6792203dd683', 'uQWogz9vVPFeaBbe3nxLtbDjr1ElrjhcnCDnTEhCGYxLYleOoPqUr81Fspv2lNkIbY9Xkh9fd0VJOtfpKdeQApGbFeQowy6LifRFlA6ELLM', null, '7200', '1439947140927', null, null, null, 'BASE_ACCESS_TOKEN', '0', null);
INSERT INTO `weixin_token` VALUES ('8f419043-da45-4024-8579-9e4b304aacce', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsy9DfijE4zNrne_l01IiOEQJeXw2u4RFTJJzIsQpy3oA8PMqbZQycHnnUK4095yVaLHXaAJ3TsZ5zhsxR3ElrVosR5oFyDasKycG_gTl5gEyQ', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', '7200', '1439952500980', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsy9DfijE4zNrne_l01IiOEQzc_IHnbJE2UDkXtQbWl7mHPAjt1txsIhgrvBmVmdbZlbmuPgP41hGgOtBmK4PTva93xp4ty9UvPus59DT9hbLw', 'snsapi_userinfo', 'ow9vet9TfunWtRG_3Ab6wv3-VziU', 'OAUTH2_ACCESS_TOKEN', '0', null);
INSERT INTO `weixin_token` VALUES ('91c91053-b680-4899-899e-35ba92cd6056', null, null, null, '1439951309469', null, null, null, 'OAUTH2_ACCESS_TOKEN', '40029', 'req id: WzBVha0311ns35, invalid code');
INSERT INTO `weixin_token` VALUES ('ae6cd7dd-f35e-4eb5-907c-03423d10a18d', null, null, null, '1439952403975', null, null, null, 'OAUTH2_ACCESS_TOKEN', '40029', 'req id: r0405ns58, invalid code');
INSERT INTO `weixin_token` VALUES ('b15ca38d-894e-4978-8e5a-03b84b413555', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsy9DfijE4zNrne_l01IiOEQS7SW5wxRlRhfkbzHzlL7PI3pcYB1tSC9R15NOMaftSnUVeJ3DbDX3s3aPAZyY6WgMYFqQytU3DxjXuRDB8-zfw', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', '7200', '1439953248229', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsy9DfijE4zNrne_l01IiOEQre535zAbWnvedGFn8KLMjaGS3fQLxW6Iu9g7-wLf_vzAjBTteJ3dukEpbKL8dbtypiVyNx7Yc8FKYnkbBlMNXw', 'snsapi_userinfo', 'ow9vet9TfunWtRG_3Ab6wv3-VziU', 'OAUTH2_ACCESS_TOKEN', '0', null);
INSERT INTO `weixin_token` VALUES ('b5eb0e3f-09e0-4331-a7f7-2cfe9a7a0d3d', null, null, null, '1439889290903', null, null, null, 'OAUTH2_ACCESS_TOKEN', '40029', 'req id: uavdCA0291ns23, invalid code');
INSERT INTO `weixin_token` VALUES ('b92578c9-1c80-4b76-976d-87b96babebea', null, null, null, '1439953257566', null, null, null, 'OAUTH2_ACCESS_TOKEN', '40029', 'req id: 8Ol7va0259ns37, invalid code');
INSERT INTO `weixin_token` VALUES ('baa803b0-19fb-4b19-9d52-94f48825f538', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsy9DfijE4zNrne_l01IiOEQzLIXQD2siOJlbjR9ScAFPILAEgqm-jL1r1V5RZzy7mvVoj2RN2UV0jUvMzDabEetdB0JTSLrOaymA-oKVzNhRQ', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', '7200', '1439953260566', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsy9DfijE4zNrne_l01IiOEQ7d3hyc2sKY-RE4YrkiavEiMheFV_hoduizjwLV5XMRVMZAdFO4tINI2jJpnAoenqIvTVQfqJCvIXzd8wedlpeQ', 'snsapi_userinfo', 'ow9vet9TfunWtRG_3Ab6wv3-VziU', 'OAUTH2_ACCESS_TOKEN', '0', null);
INSERT INTO `weixin_token` VALUES ('be2ebb9b-738c-4e42-96ad-1d8fb67381b6', null, null, null, '1439893188673', null, null, null, 'OAUTH2_ACCESS_TOKEN', '40029', 'req id: egSypa0188ns50, invalid code');
INSERT INTO `weixin_token` VALUES ('d28e296b-4d16-4b43-9288-e28e0fa7f969', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsy9DfijE4zNrne_l01IiOEQml3cHg0g9LX0tIpIFD0lGVDStIZIKrf3s_doYROeS1fjihhLVMQClHLl6ZkTIkYJDdKDg7CzqJcHQJvK7KF9bQ', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', '7200', '1439952444216', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsy9DfijE4zNrne_l01IiOEQaiPRC1ZkOy_-6DsbE45hCQ3nCS_Ffuf9jcM6PZqnbg0Xh6nTWk2CWqtmmoPACVxSpO8kGftto2sB45wkPqoaug', 'snsapi_userinfo', 'ow9vet9TfunWtRG_3Ab6wv3-VziU', 'OAUTH2_ACCESS_TOKEN', '0', null);
INSERT INTO `weixin_token` VALUES ('d5d5be31-6624-4c6b-91b3-2ee8612bae45', null, null, null, '1439953211082', null, null, null, 'OAUTH2_ACCESS_TOKEN', '40029', 'req id: LyYrta0212ns42, invalid code');
INSERT INTO `weixin_token` VALUES ('dd80b3a6-452c-4abb-abc5-e8ad33e503ef', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsy9DfijE4zNrne_l01IiOEQ2Bk1bmhplqn3rQRXiYOJH8QpRXhfpJABA1zH7aMfbmpE5n0Eo8SVX3kgAYbX-VPwU8v2_r8_6zkluofM_H4uYg', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', '7200', '1439952481396', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsy9DfijE4zNrne_l01IiOEQ_x_OW69TXDAXTYwnUGUibdVEVLM0J3ColNZT89FMY2qOjNgvpM_GGQ-hcktqsWBQDk0hzxTADKiTT7T3bNiXTQ', 'snsapi_userinfo', 'ow9vet9TfunWtRG_3Ab6wv3-VziU', 'OAUTH2_ACCESS_TOKEN', '0', null);
INSERT INTO `weixin_token` VALUES ('dfc1a693-1a1a-4095-b8c5-212a6be151fa', null, null, null, '1439893178925', null, null, null, 'OAUTH2_ACCESS_TOKEN', '40029', 'req id: JAFlpA0178ns14, invalid code');
INSERT INTO `weixin_token` VALUES ('e0e9825e-3268-46e8-be9a-efdbc7b8e884', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsy9DfijE4zNrne_l01IiOEQkTDx0w8VG7c_j_yxfkKjbRRS8_AlxebmHqVV0T9ztIYeG4ac2zuruLagfm5fC_YnJZHatcNXIetI7nh3Vuer4A', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', '7200', '1439952431109', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsy9DfijE4zNrne_l01IiOEQwkR3Bn5lumQCbJXOu0K9B_Pg-9oZOiHCs-Lqcti0XEZEkpLLIICcZDwojvAdWKF8Ke0ZbRniLBUIZGuwq0P9Rg', 'snsapi_userinfo', 'ow9vet9TfunWtRG_3Ab6wv3-VziU', 'OAUTH2_ACCESS_TOKEN', '0', null);
INSERT INTO `weixin_token` VALUES ('e0f0ca9c-578f-42f7-9e2d-7e863242273f', null, null, null, '1439953555086', null, null, null, 'OAUTH2_ACCESS_TOKEN', '40029', 'req id: 8yUxNa0556ns35, invalid code');
INSERT INTO `weixin_token` VALUES ('e677ed70-80b8-4171-a2e1-9ce9718959f4', null, null, null, '1439952497346', null, null, null, 'OAUTH2_ACCESS_TOKEN', '40029', 'req id: a95XEa0499ns41, invalid code');
INSERT INTO `weixin_token` VALUES ('edb52939-5b4a-4614-ace6-dc54eb915409', '4t6Oo_BEW_A0Nga35mY7lzEmvBGpJb2AFQOlWbSfrlMqaGa_HvJYXso-UmpaEo37E19d-oWPDgaJHl7lGi9J7voDdQEIMgzuFezA-t6zV78', null, '7200', '1439954952269', null, null, null, 'BASE_ACCESS_TOKEN', '0', null);
INSERT INTO `weixin_token` VALUES ('f09efff0-a2e6-4d86-9e44-a77ac38bbe60', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsw1VNM6pCjcQnS4K6SrFTxy48fF5h_UwE6ZZa4O965na4wQvlN_aJy0IbMuNV_f2g0fcIBOy0Avy_AiTrBV75TMBRlghLEcKOBuCm4glcykwA', 'o5D9TswnKC5V__e9ZmG-KPZcjrps', '7200', '1439953555832', 'OezXcEiiBSKSxW0eoylIeA-JKSHPLTQGbE3_Y8x7xsw1VNM6pCjcQnS4K6SrFTxyKO636nB3ywBcosggPrsCJUjnyDIyk-57GpAHyiZiPv1pwvJUrCPwMIYBHUeRw8Q1sRuyzSUeFmd384R5u9Caow', 'snsapi_userinfo', 'ow9vet3YJypW_d5-XzphheZyBCfs', 'OAUTH2_ACCESS_TOKEN', '0', null);
INSERT INTO `weixin_token` VALUES ('f1f6ee6b-b79c-4163-b838-3aec65350cad', null, null, null, '1439952441215', null, null, null, 'OAUTH2_ACCESS_TOKEN', '40029', 'req id: Ggw0FA0442ns39, invalid code');
INSERT INTO `weixin_token` VALUES ('f786d7d4-f2da-496f-8a4f-78b4335759e8', null, null, null, '1439952453805', null, null, null, 'OAUTH2_ACCESS_TOKEN', '40029', 'req id: n9TdXa0455ns66, invalid code');

-- ----------------------------
-- Table structure for wexin_template_msg
-- ----------------------------
DROP TABLE IF EXISTS `wexin_template_msg`;
CREATE TABLE `wexin_template_msg` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `token_id` varchar(50) DEFAULT NULL COMMENT 'token外键',
  `touser` varchar(100) DEFAULT NULL COMMENT '模板消息接收人',
  `template_id` varchar(150) DEFAULT NULL COMMENT '模板id',
  `url` varchar(256) DEFAULT NULL COMMENT '详情url',
  `topcolor` varchar(30) DEFAULT NULL COMMENT 'topcolor',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='模板消息';

-- ----------------------------
-- Records of wexin_template_msg
-- ----------------------------
INSERT INTO `wexin_template_msg` VALUES ('2', '1439888526356', 'd771fbe8-3233-4b15-ad94-07dbc896629d', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', 'tYapghmX2RVZHHYKi_O952Nj16DpSVR8r6HoZCbYwrI', 'www.baidu.com', '#FF0000');
INSERT INTO `wexin_template_msg` VALUES ('3', '1439888600467', 'd771fbe8-3233-4b15-ad94-07dbc896629d', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', 'tYapghmX2RVZHHYKi_O952Nj16DpSVR8r6HoZCbYwrI', 'www.baidu.com', '#FF0000');
INSERT INTO `wexin_template_msg` VALUES ('4', '1439888693915', 'd771fbe8-3233-4b15-ad94-07dbc896629d', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', 'tYapghmX2RVZHHYKi_O952Nj16DpSVR8r6HoZCbYwrI', 'www.baidu.com', '#FF0000');
INSERT INTO `wexin_template_msg` VALUES ('5', '1439888838146', 'd771fbe8-3233-4b15-ad94-07dbc896629d', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', 'tYapghmX2RVZHHYKi_O952Nj16DpSVR8r6HoZCbYwrI', 'www.baidu.com', '#FF0000');
INSERT INTO `wexin_template_msg` VALUES ('6', '1439888873022', 'd771fbe8-3233-4b15-ad94-07dbc896629d', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', 'tYapghmX2RVZHHYKi_O952Nj16DpSVR8r6HoZCbYwrI', 'www.baidu.com', '#FF0000');
INSERT INTO `wexin_template_msg` VALUES ('7', '1439888897653', 'd771fbe8-3233-4b15-ad94-07dbc896629d', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', 'tYapghmX2RVZHHYKi_O952Nj16DpSVR8r6HoZCbYwrI', 'www.baidu.com', '#FF0000');
INSERT INTO `wexin_template_msg` VALUES ('8', '1439889068962', 'd771fbe8-3233-4b15-ad94-07dbc896629d', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', 'tYapghmX2RVZHHYKi_O952Nj16DpSVR8r6HoZCbYwrI', 'www.baidu.com', '#FF0000');
INSERT INTO `wexin_template_msg` VALUES ('9', '1439889138041', 'd771fbe8-3233-4b15-ad94-07dbc896629d', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', 'tYapghmX2RVZHHYKi_O952Nj16DpSVR8r6HoZCbYwrI', 'www.baidu.com', '#FF0000');
INSERT INTO `wexin_template_msg` VALUES ('10', '1439889171385', 'd771fbe8-3233-4b15-ad94-07dbc896629d', 'o5D9Ts8qEfQy73VwwTOeUbG34Sfw', 'tYapghmX2RVZHHYKi_O952Nj16DpSVR8r6HoZCbYwrI', 'www.baidu.com', '#FF0000');

-- ----------------------------
-- Table structure for wexin_template_msg_data
-- ----------------------------
DROP TABLE IF EXISTS `wexin_template_msg_data`;
CREATE TABLE `wexin_template_msg_data` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `template_msg_id` bigint(20) DEFAULT NULL COMMENT '模板消息外键',
  `key` varchar(30) DEFAULT NULL COMMENT '字符串key',
  `value` varchar(300) DEFAULT NULL COMMENT '字符串值',
  `color` varchar(30) DEFAULT NULL COMMENT '文字颜色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='模板消息详情';

-- ----------------------------
-- Records of wexin_template_msg_data
-- ----------------------------
INSERT INTO `wexin_template_msg_data` VALUES ('33', '10', 'creditChange', '到账', '#173177');
INSERT INTO `wexin_template_msg_data` VALUES ('34', '10', 'number', '10000账', '#173177');
INSERT INTO `wexin_template_msg_data` VALUES ('35', '10', 'amount', '20000', '#173177');
INSERT INTO `wexin_template_msg_data` VALUES ('36', '10', 'remark', '您可以点击下方菜单-我的账户，随时查询账户余额', '#173177');
INSERT INTO `wexin_template_msg_data` VALUES ('37', '10', 'time', '2015-8-18 17:12:50', '#173177');
INSERT INTO `wexin_template_msg_data` VALUES ('38', '10', 'type', '线上消费赠返积分', '#173177');
INSERT INTO `wexin_template_msg_data` VALUES ('39', '10', 'first', '恭喜你购买成功！', '#173177');
INSERT INTO `wexin_template_msg_data` VALUES ('40', '10', 'creditName', '账户积分', '#173177');
