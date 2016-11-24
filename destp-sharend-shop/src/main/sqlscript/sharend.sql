/*
Navicat MySQL Data Transfer

Source Server         : local-db
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : sharend

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-11-24 17:06:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_fun`
-- ----------------------------
DROP TABLE IF EXISTS `t_fun`;
CREATE TABLE `t_fun` (
  `funid` tinyint(4) NOT NULL,
  `funname` varbinary(10) NOT NULL,
  `valid` bit(1) NOT NULL,
  `createuser` int(11) NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`funid`),
  UNIQUE KEY `index_funid` (`funid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_fun
-- ----------------------------

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `roleid` tinyint(4) NOT NULL,
  `rolename` varchar(10) NOT NULL,
  `valid` bit(1) NOT NULL,
  `createuser` int(11) NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------


-- ----------------------------
-- Table structure for `t_user_group`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_group`;
CREATE TABLE `t_user_group` (
  `groupid` tinyint(4) NOT NULL,
  `groupname` varchar(10) NOT NULL,
  `valid` bit(1) NOT NULL,
  `createuser` int(11) NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`groupid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_group
-- ----------------------------

-- ----------------------------
-- Table structure for `x_group_role`
-- ----------------------------
DROP TABLE IF EXISTS `x_group_role`;
CREATE TABLE `x_group_role` (
  `id` int(11) NOT NULL,
  `groupid` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `valid` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_group` (`groupid`,`roleid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of x_group_role
-- ----------------------------

-- ----------------------------
-- Table structure for `x_role_fun`
-- ----------------------------
DROP TABLE IF EXISTS `x_role_fun`;
CREATE TABLE `x_role_fun` (
  `id` int(11) NOT NULL,
  `roleid` int(11) NOT NULL,
  `funid` int(11) NOT NULL,
  `valid` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_role` (`roleid`,`funid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of x_role_fun
-- ----------------------------

-- ----------------------------
-- Table structure for `x_user_group`
-- ----------------------------
DROP TABLE IF EXISTS `x_user_group`;
CREATE TABLE `x_user_group` (
  `id` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `groupid` int(11) NOT NULL,
  `valid` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_user` (`userid`,`groupid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of x_user_group
-- ----------------------------
