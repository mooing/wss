/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50130
Source Host           : localhost:3306
Source Database       : wssdb

Target Server Type    : MYSQL
Target Server Version : 50130
File Encoding         : 65001

Date: 2013-12-28 15:23:42
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `cert_apply`
-- ----------------------------
DROP TABLE IF EXISTS `cert_apply`;
CREATE TABLE `cert_apply` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `apply_num` int(11) DEFAULT NULL COMMENT '申请数量',
  `apply_time` datetime DEFAULT NULL COMMENT '申请时间',
  `applyer_id` int(11) DEFAULT NULL COMMENT '申请人id',
  `unit_id` int(11) DEFAULT NULL COMMENT '申请单位id',
  `punit_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='领用申请';

-- ----------------------------
-- Records of cert_apply
-- ----------------------------

-- ----------------------------
-- Table structure for `cert_issue_detail`
-- ----------------------------
DROP TABLE IF EXISTS `cert_issue_detail`;
CREATE TABLE `cert_issue_detail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `info_id` varchar(20) DEFAULT NULL COMMENT '儿童id',
  `bir_pro_code` varchar(20) DEFAULT NULL COMMENT '孕妇id',
  `bir_ciry_code` varchar(20) DEFAULT NULL COMMENT '出生证id，关联出生证库存表cert_stock',
  `bir_country_code` varchar(20) DEFAULT NULL COMMENT '医生id',
  `bir_address` varchar(50) DEFAULT NULL COMMENT '出生证编号',
  `week` datetime DEFAULT NULL COMMENT '办证时间',
  `health_status` int(11) DEFAULT NULL COMMENT '状态',
  `weight` float(6,2) DEFAULT NULL,
  `height` float(6,2) DEFAULT NULL,
  `nation` varchar(20) DEFAULT NULL,
  `m_age` int(11) DEFAULT NULL,
  `m_idcard` varchar(30) DEFAULT NULL,
  `f_name` varchar(30) DEFAULT NULL,
  `f_age` int(11) DEFAULT NULL,
  `f_nationality` varchar(30) DEFAULT NULL,
  `f_nation` varchar(20) DEFAULT NULL,
  `f_card_type` int(11) DEFAULT NULL,
  `f_card` varchar(30) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='儿童出生证签发详情';

-- ----------------------------
-- Records of cert_issue_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `cert_issue_info`
-- ----------------------------
DROP TABLE IF EXISTS `cert_issue_info`;
CREATE TABLE `cert_issue_info` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `unit_id` int(11) DEFAULT NULL COMMENT '单位id',
  `cert_code` varchar(20) DEFAULT NULL,
  `new_cert_code` char(10) DEFAULT NULL,
  `cert_id` int(11) DEFAULT NULL,
  `child_name` varchar(30) DEFAULT NULL COMMENT '儿童姓名',
  `sex` int(11) DEFAULT NULL COMMENT '性别 1男 2女',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `m_name` varchar(30) DEFAULT NULL COMMENT '母亲姓名',
  `m_idcard` varchar(30) DEFAULT NULL COMMENT '母亲身份证',
  `admission_number` varchar(30) DEFAULT NULL COMMENT '住院号',
  `deliver` varchar(30) DEFAULT NULL COMMENT '接生人',
  `pro_code` varchar(20) DEFAULT NULL COMMENT '家庭住址省code',
  `city_code` varchar(20) DEFAULT NULL COMMENT '家庭住址市code',
  `country_code` varchar(20) DEFAULT NULL COMMENT '家庭住址县code',
  `address` varchar(50) DEFAULT NULL COMMENT '详细地址',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `issue_status` int(11) DEFAULT NULL COMMENT '签发状态',
  `print_status` int(11) DEFAULT NULL COMMENT '打印状态',
  `issue_time` datetime DEFAULT NULL COMMENT '签发日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='儿童出生证签发信息';

-- ----------------------------
-- Records of cert_issue_info
-- ----------------------------

-- ----------------------------
-- Table structure for `cert_log`
-- ----------------------------
DROP TABLE IF EXISTS `cert_log`;
CREATE TABLE `cert_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `apply_num` int(11) DEFAULT NULL COMMENT '申请数量',
  `apply_time` datetime DEFAULT NULL COMMENT '申请时间',
  `applyer_id` int(11) DEFAULT NULL COMMENT '申请人id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出生证操作记录';

-- ----------------------------
-- Records of cert_log
-- ----------------------------

-- ----------------------------
-- Table structure for `cert_put_stock`
-- ----------------------------
DROP TABLE IF EXISTS `cert_put_stock`;
CREATE TABLE `cert_put_stock` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `start_code` varchar(20) DEFAULT NULL COMMENT '起始编号',
  `end_code` varchar(20) DEFAULT NULL COMMENT '终止编号',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `put_time` datetime DEFAULT NULL COMMENT '入库时间',
  `put_perion_id` int(11) DEFAULT NULL COMMENT '入库人',
  `opetion_time` datetime DEFAULT NULL COMMENT '操作时间',
  `opetion_person_id` int(11) DEFAULT NULL COMMENT '操作人',
  `unit_id` int(11) DEFAULT NULL COMMENT '所属单位id,关联hospital表id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入库调库记录 ';

-- ----------------------------
-- Records of cert_put_stock
-- ----------------------------

-- ----------------------------
-- Table structure for `cert_stock`
-- ----------------------------
DROP TABLE IF EXISTS `cert_stock`;
CREATE TABLE `cert_stock` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `put_stock_id` int(11) DEFAULT NULL COMMENT '入库表id',
  `unit_id` int(11) DEFAULT NULL COMMENT '所属单位',
  `cert_code` varchar(20) DEFAULT NULL COMMENT '出生证编号',
  `create_time` datetime DEFAULT NULL COMMENT '入库日期',
  `status` int(11) DEFAULT NULL COMMENT '状态 1:有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出生证库存表';

-- ----------------------------
-- Records of cert_stock
-- ----------------------------

-- ----------------------------
-- Table structure for `dic_big_type`
-- ----------------------------
DROP TABLE IF EXISTS `dic_big_type`;
CREATE TABLE `dic_big_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `text` varchar(50) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统共用字典大类表';

-- ----------------------------
-- Records of dic_big_type
-- ----------------------------

-- ----------------------------
-- Table structure for `dic_child_type`
-- ----------------------------
DROP TABLE IF EXISTS `dic_child_type`;
CREATE TABLE `dic_child_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `text` varchar(50) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统共用字典小类表';

-- ----------------------------
-- Records of dic_child_type
-- ----------------------------

-- ----------------------------
-- Table structure for `dic_system`
-- ----------------------------
DROP TABLE IF EXISTS `dic_system`;
CREATE TABLE `dic_system` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `big_type_id` int(11) DEFAULT NULL COMMENT '大类编号',
  `child_type_id` int(11) DEFAULT NULL COMMENT '小类',
  `text` varchar(50) DEFAULT NULL COMMENT '名称',
  `code` int(11) DEFAULT NULL COMMENT '编号',
  `status` int(11) DEFAULT '1' COMMENT '状态',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统共用字典';

-- ----------------------------
-- Records of dic_system
-- ----------------------------

-- ----------------------------
-- Table structure for `hos_doctor`
-- ----------------------------
DROP TABLE IF EXISTS `hos_doctor`;
CREATE TABLE `hos_doctor` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id  对应用户表',
  `name` varchar(50) DEFAULT NULL COMMENT '医生姓名',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别  1 男；2 女',
  `hospital_id` int(11) DEFAULT NULL COMMENT '工作单位id',
  `card_type` int(11) DEFAULT NULL,
  `card_code` varchar(50) DEFAULT NULL,
  `isdoctor` tinyint(4) DEFAULT NULL COMMENT '是否医生 0:不是；1：是医生',
  `isdeliver` tinyint(4) DEFAULT NULL COMMENT '是否接生人 0:不是；1：是 ',
  `office_code` varchar(50) DEFAULT NULL COMMENT '所在科室',
  `offer_title` varchar(50) DEFAULT NULL COMMENT '职称',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `ca_name` varchar(50) DEFAULT NULL COMMENT 'ca证书名',
  `ca_expire` datetime DEFAULT NULL COMMENT 'ca证书有效期',
  `ca_code` varchar(50) DEFAULT NULL COMMENT 'ca证书编码',
  `readcard_type` tinyint(4) DEFAULT NULL COMMENT '读卡器类型',
  `readcard_code` varchar(50) DEFAULT NULL COMMENT '读卡器序号',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态  0:无效；1：有效 默认有效',
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医生表';

-- ----------------------------
-- Records of hos_doctor
-- ----------------------------

-- ----------------------------
-- Table structure for `hos_hospital`
-- ----------------------------
DROP TABLE IF EXISTS `hos_hospital`;
CREATE TABLE `hos_hospital` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '单位名称',
  `hos_code` varchar(20) DEFAULT NULL COMMENT '单位编码',
  `org_code` varchar(20) DEFAULT NULL COMMENT '机构编码',
  `region_code` varchar(20) DEFAULT NULL COMMENT '所在地区',
  `pid` int(11) DEFAULT NULL,
  `ext_address` varchar(50) DEFAULT NULL COMMENT '补充地址',
  `sort` int(11) DEFAULT NULL COMMENT '序号',
  `isbjy` tinyint(4) DEFAULT NULL COMMENT '是否是妇幼保健院 0:不是；1 ：是',
  `contacts` varchar(50) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `zipcode` varchar(10) DEFAULT NULL COMMENT '邮政编码',
  `hos_property` tinyint(4) DEFAULT NULL COMMENT '单位性质 1:国企 2：私企',
  `level` tinyint(4) DEFAULT NULL COMMENT '医院等级',
  `classify_code` varchar(200) DEFAULT NULL COMMENT '单位分类：可选 新筛中心 产筛中心等字典id，用逗号分隔',
  `sms_account` varchar(50) DEFAULT NULL,
  `sms_password` varchar(50) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '状态  0:无效；1：有效 默认有效',
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医院单位表';

-- ----------------------------
-- Records of hos_hospital
-- ----------------------------

-- ----------------------------
-- Table structure for `subject`
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '业务项目名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务项目';

-- ----------------------------
-- Records of subject
-- ----------------------------

-- ----------------------------
-- Table structure for `subject_ref`
-- ----------------------------
DROP TABLE IF EXISTS `subject_ref`;
CREATE TABLE `subject_ref` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `base_id` int(11) DEFAULT NULL COMMENT '基础项目id',
  `subject_id` int(11) DEFAULT NULL COMMENT '业务项目id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='儿童业务项目关联表';

-- ----------------------------
-- Records of subject_ref
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_module`
-- ----------------------------
DROP TABLE IF EXISTS `sys_module`;
CREATE TABLE `sys_module` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `modname` varchar(30) NOT NULL COMMENT '模块名称',
  `leaf` bit(1) DEFAULT NULL COMMENT '是否叶子节点 1:是；0：不是',
  `pid` int(11) DEFAULT NULL COMMENT '父id',
  `sen` varchar(50) DEFAULT NULL COMMENT '英文名',
  `sort` int(11) DEFAULT NULL COMMENT '序号',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态:0:不可用；1：可用 默认可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='系统模块表';

-- ----------------------------
-- Records of sys_module
-- ----------------------------
INSERT INTO sys_module VALUES ('1', '妇幼系统', '', '0', null, '1', '1');
INSERT INTO sys_module VALUES ('2', '系统管理', '', '0', null, null, '1');
INSERT INTO sys_module VALUES ('3', '单位管理', '', '0', null, null, '1');
INSERT INTO sys_module VALUES ('4', '出生证明', '', '0', null, null, '1');
INSERT INTO sys_module VALUES ('5', '用户管理', '', '2', null, null, '1');
INSERT INTO sys_module VALUES ('6', '角色管理', '', '2', null, null, '1');
INSERT INTO sys_module VALUES ('7', '日志管理', '', '2', null, null, '1');
INSERT INTO sys_module VALUES ('8', '基础配置', '', '2', null, null, '1');
INSERT INTO sys_module VALUES ('9', '证件管理', '', '4', null, null, '1');
INSERT INTO sys_module VALUES ('10', '证件办理', '', '4', null, null, '1');
INSERT INTO sys_module VALUES ('11', '查询统计', '', '4', null, null, '1');
INSERT INTO sys_module VALUES ('12', '证件入库', '', '4', null, null, '1');

-- ----------------------------
-- Table structure for `sys_region`
-- ----------------------------
DROP TABLE IF EXISTS `sys_region`;
CREATE TABLE `sys_region` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL COMMENT '地区编号',
  `level` tinyint(4) DEFAULT NULL COMMENT '层级',
  `name` varchar(30) DEFAULT NULL COMMENT '名称',
  `pcode` varchar(20) DEFAULT NULL COMMENT '父编号',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态 0：不可用；1：可用 默认可用',
  PRIMARY KEY (`id`),
  KEY `Index_region_code` (`code`),
  KEY `Index_region_pcode` (`pcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地区表';

-- ----------------------------
-- Records of sys_region
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `rolename` varchar(30) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`),
  KEY `Index_role_name` (`rolename`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO sys_role VALUES ('3', '乡镇');
INSERT INTO sys_role VALUES ('4', '县区');
INSERT INTO sys_role VALUES ('2', '地市');
INSERT INTO sys_role VALUES ('1', '系统管理员');

-- ----------------------------
-- Table structure for `sys_role_module`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_module`;
CREATE TABLE `sys_role_module` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `obj_id` int(11) NOT NULL COMMENT '角色或用户id',
  `module_id` int(11) NOT NULL COMMENT '模块id',
  `type` int(11) DEFAULT NULL COMMENT '类型 ,1:角色；2：用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色用户--模块表';

-- ----------------------------
-- Records of sys_role_module
-- ----------------------------
INSERT INTO sys_role_module VALUES ('5', '1', '1', '2');
INSERT INTO sys_role_module VALUES ('6', '1', '2', '2');
INSERT INTO sys_role_module VALUES ('7', '1', '4', '2');
INSERT INTO sys_role_module VALUES ('8', '1', '5', '2');
INSERT INTO sys_role_module VALUES ('9', '1', '3', '2');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `realname` varchar(30) DEFAULT NULL,
  `password` varchar(60) NOT NULL,
  `usertype` tinyint(4) DEFAULT NULL COMMENT '用户类型  1：管理员 2：医生',
  `reg_time` datetime DEFAULT NULL COMMENT '注册时间',
  `lastlogin_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `ip` varchar(30) DEFAULT NULL,
  `random` varchar(30) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '用户状态  0：无效；1：有效 默认有效',
  PRIMARY KEY (`id`),
  KEY `Index_user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO sys_user VALUES ('1', 'admin', '', '7bb081dd65e675210697ee2c799ac92f636ac5', '1', '2013-12-06 09:30:57', '2013-12-06 09:30:57', null, 'SVymOOiCKovMdsEI9YG2', '1');
INSERT INTO sys_user VALUES ('2', 'test1', '111', '6ce65e2812c1b570c55a1e24c4be50f786d3cc', '0', '2013-12-28 12:22:27', '2013-12-28 12:22:27', null, '15ttSubnTga8J6ZnLtm5', '1');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO sys_user_role VALUES ('15', '2', '3');
INSERT INTO sys_user_role VALUES ('16', '2', '4');
INSERT INTO sys_user_role VALUES ('17', '1', '4');
INSERT INTO sys_user_role VALUES ('18', '1', '1');
