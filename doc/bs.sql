/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : 127.0.0.1:3306
 Source Schema         : xtt_bs

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 01/05/2020 17:41:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountName` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名,默认是手机号',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '登陆密码',
  `realName` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '真实姓名',
  `phone` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `role` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户角色(admin 系统管理员, user 普通用户)',
  `state` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'normal' COMMENT '用户状态(normal 正常, abnormal 异常)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 104893776 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '账户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (6, 'haha01', '123456', 'qaqw', '15531304321', 'consumer', 'normal');
INSERT INTO `account` VALUES (7, 'hello', '123456', 'yukai', '15531304321', 'consumer', 'normal');
INSERT INTO `account` VALUES (8, 'xtt', '123456', 'xtt', '15531301597', 'consumer', 'normal');
INSERT INTO `account` VALUES (100, '彭于晏', '123', '彭彭', '122', 'consumer', 'normal');
INSERT INTO `account` VALUES (101, '张三', '123', 'zs', '123', 'consumer', 'normal');
INSERT INTO `account` VALUES (331, 'test', '1234567', 'yukai', '15531301234', 'consumer', 'normal');
INSERT INTO `account` VALUES (332, '12312', '123123', 'yukai', '15531304321', 'consumer', 'normal');
INSERT INTO `account` VALUES (333, '111', '123123', 'yukai', '15531301222', 'consumer', 'normal');
INSERT INTO `account` VALUES (334, '1123', '123123', 'yukai', '15531304311', 'consumer', 'normal');
INSERT INTO `account` VALUES (104893775, '123123', '123123', 'yukai', '15531304366', 'consumer', 'normal');

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountId` int(11) NOT NULL COMMENT '用户ID',
  `consignee` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '收货人姓名',
  `province` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '省份',
  `city` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '市',
  `district` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '区',
  `detailAddress` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '详细地址',
  `zipcode` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '邮政编码',
  `telephone` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '电话',
  `flag` int(1) DEFAULT NULL COMMENT '是否是默认收货地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '地址表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (4, 7, 'hello', '北京市', '北京市', '东城区', 'aaa', '123123', '15531301234', 0);
INSERT INTO `address` VALUES (5, 7, 'hello', '山西省', '太原市', '小店区', 'aaa', '123123', '15531301234', 1);
INSERT INTO `address` VALUES (6, 7, 'hello', '内蒙古自治区', '呼和浩特市', '新城区', 'aaa', '123123', '15531301234', 0);
INSERT INTO `address` VALUES (7, 7, 'hello', '江西省', '南昌市', '东湖区', 'aaa', '123123', '15531301234', 0);
INSERT INTO `address` VALUES (8, 8, 'hello', '浙江省', '杭州市', '上城区', 'qwe', '123123', '15531301234', 0);

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `products_type_id` int(11) NOT NULL COMMENT '商品类别ID',
  `goodsName` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品名称',
  `productDate` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '生产日期',
  `purchaseDate` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '进货日期',
  `purchaseNum` int(16) NOT NULL DEFAULT 0 COMMENT '进货数量',
  `amount` int(16) NOT NULL DEFAULT 0 COMMENT '库存数量',
  `purchasePrice` decimal(64, 18) NOT NULL COMMENT '进货价格',
  `sellPrice` decimal(64, 18) NOT NULL COMMENT '销售价格',
  `goodsImage` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '商品图片',
  `description` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '暂无描述' COMMENT '详细描述',
  `remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `colorNum` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '92号' COMMENT '色号',
  `area` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '广州' COMMENT '生产地区',
  `isbnNum` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'ISBN编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES (7, 100, '金币银币纪念币', '2020-04-10', '2020-04-28', 8, 3, 100.000000000000000000, 90.000000000000000000, '/images/products/p1.png', '金币银币定制', '666', '#999', '上海', NULL);
INSERT INTO `products` VALUES (8, 100, '保温杯套装礼品', '2020-04-10', '2020-04-28', 6, 3, 78.000000000000000000, 58.000000000000000000, '/images/products/p2.png', '开业周年庆典企业纪念品', '666', '#999', '上海', NULL);
INSERT INTO `products` VALUES (9, 100, '厦门大学书签纪念品', '2020-04-10', '2020-04-28', 6, 3, 78.000000000000000000, 58.000000000000000000, '/images/products/p3.png', '厦门大学书签', '666', '#999', '上海', NULL);
INSERT INTO `products` VALUES (10, 200, '婚庆周年纪念日礼品', '2020-04-10', '2020-04-28', 6, 3, 78.000000000000000000, 58.000000000000000000, '/images/products/p6.png', '执子之手创意结婚礼物', '666', '#999', '上海', NULL);
INSERT INTO `products` VALUES (11, 200, '鼠年纪念币', '2020-04-10', '2020-04-28', 5, 3, 20.000000000000000000, 20.000000000000000000, '/images/products/p7.png', '2020新款年鼠年纪念币福袋纪念章', '666', '#999', '上海', NULL);
INSERT INTO `products` VALUES (12, 200, '金属徽章', '2020-04-10', '2020-04-28', 2, 3, 10.000000000000000000, 10.000000000000000000, '/images/products/p8.png', '金属徽章', '666', '#999', '上海', NULL);
INSERT INTO `products` VALUES (13, 200, '杯子本电源鼠标笔优盘六件套', '2020-04-10', '2020-04-28', 2, 3, 10.000000000000000000, 10.000000000000000000, '/images/products/p4.png', '蓝黑-杯子本电源鼠标笔优盘六件套', '666', '#999', '上海', NULL);
INSERT INTO `products` VALUES (14, 300, '梳妆镜', '2020-04-10', '2020-04-28', 12, 1, 128.000000000000000000, 99.000000000000000000, '/images/products/p9.png', '创意化妆镜带灯梳妆镜', '666', '#999', '上海', NULL);
INSERT INTO `products` VALUES (15, 300, '陶瓷碗筷套装', '2020-04-10', '2020-04-28', 5, 3, 15.000000000000000000, 15.000000000000000000, '/images/products/p10.png', '商务纪念品企业周年活动奖品伴手礼物开业促销赠品', '666', '#999', '上海', NULL);
INSERT INTO `products` VALUES (16, 300, '木刻书', '2020-04-10', '2020-04-28', 2, 3, 328.000000000000000000, 299.000000000000000000, '/images/products/p11.png', '三面照片少量文字款+礼盒手礼袋', '666', '#999', '上海', NULL);
INSERT INTO `products` VALUES (17, 300, '餐具套装', '2020-04-10', '2020-04-28', 2, 3, 399.000000000000000000, 399.000000000000000000, '/images/products/p12.png', '百鸟朝凤6件套', '666', '#999', '上海', NULL);
INSERT INTO `products` VALUES (18, 300, '雕刻剪纸灯', '2020-04-10', '2020-04-28', 2, 3, 218.000000000000000000, 200.000000000000000000, '/images/products/p13.png', '龙猫.礼盒版', '666', '#999', '上海', NULL);
INSERT INTO `products` VALUES (19, 300, '吊坠纪念品', '2020-04-10', '2020-04-28', 2, 3, 358.000000000000000000, 300.000000000000000000, '/images/products/p14.png', '情侣款_轻奢玫瑰礼盒', '666', '#999', '上海', NULL);
INSERT INTO `products` VALUES (100, 100, '梳子纪念品', '2020-04-10', '2020-04-28', 6, 3, 78.000000000000000000, 58.000000000000000000, '/images/products/p5.png', '梳子纪念品', '666', '#999', '上海', NULL);

-- ----------------------------
-- Table structure for products_type
-- ----------------------------
DROP TABLE IF EXISTS `products_type`;
CREATE TABLE `products_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeNumber` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '类别编号',
  `typeName` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '类别名称',
  `typeOrder` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '类别顺序',
  `remark` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12320 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '商品类别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products_type
-- ----------------------------
INSERT INTO `products_type` VALUES (12301, '100', '精选', '1', '1');
INSERT INTO `products_type` VALUES (12302, '200', '推荐', '2', '2');
INSERT INTO `products_type` VALUES (12303, '300', '特价', '3', '3');
INSERT INTO `products_type` VALUES (12319, '400', 'test', '4', '4');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountId` int(11) NOT NULL COMMENT '用户ID',
  `goodsId` int(11) NOT NULL COMMENT '商品ID',
  `addressId` int(11) DEFAULT NULL COMMENT '地址ID',
  `imageUrl` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品图片URL',
  `goodsName` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品名称',
  `price` decimal(64, 18) NOT NULL COMMENT '商品价格',
  `num` int(16) NOT NULL COMMENT '下单数量',
  `totalPrice` decimal(64, 18) NOT NULL COMMENT '总金额',
  `orderNumber` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '订单编号',
  `paymentDate` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '付款日期',
  `state` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '订单状态(wait 等待支付,canceled 已取消,failed 失败,success 成功)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (44, 7, 8, NULL, '/images/sll.jpg', '圣罗兰', 500.000000000000000000, 1, 500.000000000000000000, '1558106045255', '2019-05-17 23:14:05', 'wait');
INSERT INTO `t_order` VALUES (45, 7, 12, NULL, '/images/zcx.jpg', '植村秀', 400.000000000000000000, 1, 400.000000000000000000, '1558106362343', '2019-05-17 23:19:22', 'wait');
INSERT INTO `t_order` VALUES (46, 7, 8, 7, '/images/sll.jpg', '圣罗兰', 500.000000000000000000, 1, 500.000000000000000000, '1558106682198', '2019-05-17 23:24:42', 'paymented');
INSERT INTO `t_order` VALUES (47, 7, 7, 6, '/images/dior.jpg', '迪奥', 500.000000000000000000, 1, 500.000000000000000000, '1558370962286', '2019-05-21 00:49:22', 'paymented');
INSERT INTO `t_order` VALUES (48, 8, 14, 8, '/images/products/p9.png', '梳妆镜', 99.000000000000000000, 1, 99.000000000000000000, '1588325436149', '2020-05-01 17:30:36', 'paymented');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'xtt', '123456', '15531301234');
INSERT INTO `t_user` VALUES (2, 'admin', 'admin', '15531301235');

SET FOREIGN_KEY_CHECKS = 1;
