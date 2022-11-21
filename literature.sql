/*
 Navicat Premium Data Transfer

 Source Server         : Dzy
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : literature

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 24/04/2021 15:21:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lt_collect
-- ----------------------------
DROP TABLE IF EXISTS `lt_collect`;
CREATE TABLE `lt_collect`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) DEFAULT NULL,
  `work_id` int(20) DEFAULT NULL,
  `collect_time` datetime(0) DEFAULT NULL COMMENT '收藏时间',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `del` int(20) DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for lt_read
-- ----------------------------
DROP TABLE IF EXISTS `lt_read`;
CREATE TABLE `lt_read`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) DEFAULT NULL COMMENT '用户id',
  `work_id` int(20) DEFAULT NULL COMMENT '文学作品id',
  `read_time` datetime(0) DEFAULT NULL COMMENT '最近一次阅读时间',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注信息',
  `del` int(255) DEFAULT 0 COMMENT '是否删除，默认0，没有被删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for lt_user
-- ----------------------------
DROP TABLE IF EXISTS `lt_user`;
CREATE TABLE `lt_user`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `motto` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '座右铭',
  `del` int(11) DEFAULT 0 COMMENT '是否注销 0：没有注销，1：已经注销 。默认没有注销',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `role` int(11) DEFAULT 2 COMMENT '0:admin, 1:作者 ，2：读者 。 3:读者申请作者中\r\n默认已注册就是读者用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for lt_work
-- ----------------------------
DROP TABLE IF EXISTS `lt_work`;
CREATE TABLE `lt_work`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '内容',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '作者',
  `author_id` int(20) DEFAULT NULL COMMENT '上传者id',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类型',
  `permission` int(20) DEFAULT 0 COMMENT '0:待审核 ，1 ：许可 2.被驳回',
  `brief` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '简介',
  `publish_time` datetime(0) DEFAULT NULL COMMENT '作品发布时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '作品最新修改时间',
  `read_count` int(20) DEFAULT 0 COMMENT '阅读量',
  `del` int(20) DEFAULT 0 COMMENT '是否删除，默认0（没有删除）',
  `memo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
