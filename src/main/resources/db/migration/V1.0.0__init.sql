/*
Navicat MySQL Data Transfer

Source Server         : chain_cloud
Source Server Version : 50718
Source Host           : cdb-nyg9m8iq.cd.tencentcdb.com:10178
Source Database       : chain_test

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2020-12-18 17:15:57
*/
create database if not exists perms;
use perms;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('100', '0', '0', '若依科技', '0', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-12-14 16:50:41', '', null);
INSERT INTO `sys_dept` VALUES ('101', '100', '0,100', '深圳总公司', '1', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-12-14 16:50:41', '', null);
INSERT INTO `sys_dept` VALUES ('102', '100', '0,100', '长沙分公司', '2', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-12-14 16:50:41', '', null);
INSERT INTO `sys_dept` VALUES ('103', '101', '0,100,101', '研发部门', '1', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-12-14 16:50:41', '', null);
INSERT INTO `sys_dept` VALUES ('104', '101', '0,100,101', '市场部门', '2', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-12-14 16:50:41', '', null);
INSERT INTO `sys_dept` VALUES ('105', '101', '0,100,101', '测试部门', '3', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-12-14 16:50:41', '', null);
INSERT INTO `sys_dept` VALUES ('106', '101', '0,100,101', '财务部门', '4', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-12-14 16:50:41', '', null);
INSERT INTO `sys_dept` VALUES ('107', '101', '0,100,101', '运维部门', '5', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-12-14 16:50:41', '', null);
INSERT INTO `sys_dept` VALUES ('108', '102', '0,100,102', '市场部门', '1', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-12-14 16:50:41', '', null);
INSERT INTO `sys_dept` VALUES ('109', '102', '0,100,102', '财务部门', '2', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2020-12-14 16:50:41', '', null);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `path` varchar(50) DEFAULT NULL,
  `component` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `meta` varchar(100) DEFAULT NULL,
  `redirect` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', null, '/', 'Layout', 'Home', null, '/dashboard');
INSERT INTO `sys_menu` VALUES ('2', '1', '/dashboard', 'views/dashboard/index', 'Dashboard', '{ title: \'首页\', icon: \'dashboard\' }', null);
INSERT INTO `sys_menu` VALUES ('3', null, '/ads', 'Layout', 'Ads', '{ title: \'广告管理\', icon: \'el-icon-help\' }', '/ads/banner');
INSERT INTO `sys_menu` VALUES ('4', '3', '/ads/banner', 'views/banner/index', 'Banner', '{ title: \'轮播图\' }', null);
INSERT INTO `sys_menu` VALUES ('5', '3', '/ads/link', 'views/link/index', 'Link', '{ title: \'友情链接\' }', null);
INSERT INTO `sys_menu` VALUES ('6', null, '/article', 'Layout', 'Article', '{ title: \'文章管理\', icon: \'el-icon-document\' }', '/article/list');
INSERT INTO `sys_menu` VALUES ('7', '6', '/article/list', 'views/article/index', 'ArticleList', '{ title: \'文章管理\' }', null);
INSERT INTO `sys_menu` VALUES ('8', '6', '/article/category', 'views/article/category', 'ArticleCategory', '{ title: \'文章分类\' }', null);
INSERT INTO `sys_menu` VALUES ('9', null, '/data', 'Layout', 'Data', '{ title: \'数据统计\', icon: \'el-icon-s-data\' }', '/data/article');
INSERT INTO `sys_menu` VALUES ('10', '9', '/data/article', 'views/data/index', 'Article', '{ title: \'文章统计\' }', null);
INSERT INTO `sys_menu` VALUES ('11', null, '/nested', 'Layout', 'Nested', '{title: \'多级菜单\',icon: \'el-icon-s-operation\'}', '/nested/menu1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'admin', '1', '1', '0', '0', 'admin', '2020-12-14 14:34:16', '', null, '超级管理员');
INSERT INTO `sys_role` VALUES ('2', '普通角色', 'common', '2', '2', '0', '0', 'admin', '2020-12-14 14:34:16', '', null, '普通角色');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) DEFAULT '' COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户 01注册用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phone` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像路径',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `salt` varchar(20) DEFAULT '' COMMENT '盐加密',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `pwd_update_date` datetime DEFAULT NULL COMMENT '密码最后更新时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '103', 'admin', 'admin', '00', 'ry@163.com', '15888888888', '1', '', '$2a$10$5ZRmL1GntMNqrrDj1a3Ua.kRUxAFTJYYh7I92nRrPvVEoxwROUIh2', '111111', '0', '0', '127.0.0.1', '2020-12-14 16:50:20', '2020-12-14 16:50:20', 'admin', '2020-12-14 16:50:20', '', null, '管理员');
INSERT INTO `sys_user` VALUES ('2', '105', 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$5ZRmL1GntMNqrrDj1a3Ua.kRUxAFTJYYh7I92nRrPvVEoxwROUIh2', '222222', '0', '0', '127.0.0.1', '2020-12-14 16:49:46', '2020-12-14 16:49:46', 'admin', '2020-12-14 16:49:46', '', null, '测试员');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2', '2');

DROP TABLE IF EXISTS `drone`;
CREATE TABLE `drone` (
  `id` varchar(64) NOT NULL,
  `model` varchar(64) NOT NULL COMMENT '型号',
  `model_photo` varchar(64) DEFAULT NULL COMMENT '图片地址',
  `serial_number` varchar(64) NOT NULL COMMENT '序列号',
  `policy_no` varchar(64) DEFAULT NULL COMMENT '保险单号',
  `network_status` char(1) DEFAULT '0' COMMENT '入网状态（0已入网 1未入网）',
  `online_status` char(1) DEFAULT '0' COMMENT '在线状态（0在线 1离线）',
  `device_status` char(1) DEFAULT '0' COMMENT '设备状态（0状态好 1状态良 2状态差 3维保中 4已报废）',
  `purchasing_date` datetime DEFAULT NULL COMMENT '购买时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='无人机基本信息';

DROP TABLE IF EXISTS `drone_maintenance_records`;
CREATE TABLE `drone_maintenance` (
  `id` varchar(64) NOT NULL,
  `drone_id` varchar(64) NOT NULL COMMENT '无人机ID',
  `maintenance_date` datetime NOT NULL COMMENT '维保时间',
  `maintenance_reason` blob NOT NULL COMMENT '维保原因',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='无人机维保记录';

DROP TABLE IF EXISTS `drone_maintenance_records`;
CREATE TABLE `drone_flight` (
  `id` varchar(64) NOT NULL,
  `drone_id` varchar(64) NOT NULL COMMENT '无人机ID',
  `flight_mileage` varchar(64) NOT NULL COMMENT '本次飞行里程',
  `flight_consume` varchar(64) NOT NULL COMMENT '本次飞行耗时',
  `flight_time` datetime NOT NULL COMMENT '飞行时间',
  `last_online_time` datetime NOT NULL COMMENT '最后在线时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='无人机维保记录';