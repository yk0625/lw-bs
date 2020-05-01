-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='后台用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'xtt', '123456', '15531301234');
INSERT INTO `t_user` VALUES ('2', 'admin', 'admin', '15531301235');

--
-- Table structure for table `account`
--
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountName` varchar(64) NOT NULL COMMENT '用户名,默认是手机号',
  `password` varchar(64) NOT NULL COMMENT '登录密码',
  `realName` varchar(64) NOT NULL COMMENT '真实姓名',
  `phone` varchar(64) NOT NULL COMMENT '手机号',
  `role` varchar(16) NOT NULL COMMENT '用户角色(admin 系统管理员, user 普通用户)',
  `state` varchar(16) DEFAULT 'normal' COMMENT '用户状态(normal 正常, abnormal 异常)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='账户表';

--
-- Table structure for table `order`
--
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountId` int(11) NOT NULL COMMENT '用户ID',
  `goodsId` int(11) NOT NULL COMMENT '商品ID',
  `addressId` int(11) COMMENT '地址ID',
  `imageUrl` varchar(64) NOT NULL COMMENT '商品图片URL',
  `goodsName` varchar(64) NOT NULL COMMENT '商品名称',
  `price` decimal(64,18) NOT NULL COMMENT '商品价格',
  `num` int(16) NOT NULL COMMENT '下单数量',
  `totalPrice` decimal(64,18) NOT NULL COMMENT '总金额',
  `orderNumber` varchar(64) NOT NULL COMMENT '订单编号',
  `paymentDate` varchar(64) NOT NULL COMMENT '付款日期',
  `state` varchar(16) NOT NULL COMMENT '订单状态(wait 等待支付,canceled 已取消,failed 失败,success 成功)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单表';

--
-- Table structure for table `products`
--
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `products_type_id` int(11) NOT NULL COMMENT '商品类别ID',
  `goodsName` varchar(64) NOT NULL COMMENT '商品名称',
  `colorNum` varchar(64) DEFAULT '92号' COMMENT '色号',
  `area` varchar(64) DEFAULT '广州' COMMENT '生产地区',
  `productDate` varchar(64) COMMENT '生产日期',
  `purchaseDate` varchar(64) COMMENT '进货日期',
  `purchaseNum` int(16) NOT NULL DEFAULT 0 COMMENT '进货数量',
  `amount` int(16) NOT NULL DEFAULT 0 COMMENT '库存数量',
  `purchasePrice` decimal(64,18) NOT NULL COMMENT '进货价格',
  `sellPrice` decimal(64,18) NOT NULL COMMENT '销售价格',
  `goodsImage` varchar(128) COMMENT '商品图片',
  `description` varchar(128) NOT NULL DEFAULT '暂无描述' COMMENT '详细描述',
  `remark` varchar(128) COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品表';

--
-- Table structure for table `products_type`
--
DROP TABLE IF EXISTS `products_type`;
CREATE TABLE `products_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeNumber` varchar(64) NOT NULL COMMENT '类别编号',
  `typeName` varchar(64) NOT NULL COMMENT '类别名称',
  `typeOrder` varchar(64) NOT NULL COMMENT '类别顺序',
  `remark` varchar(64) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品类别表';

--
-- Table structure for table `sys_info`
--
DROP TABLE IF EXISTS `sys_info`;
CREATE TABLE `sys_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodsName` varchar(64) NOT NULL COMMENT '商品名称',
  `sellNum` varchar(64) NOT NULL COMMENT '销售数量',
  `sellAmount` varchar(64) NOT NULL COMMENT '销售金额',
  `updateDate` varchar(64) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='统计信息表';

--
-- Table structure for table `address`
--
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountId` int(11) NOT NULL COMMENT '用户ID',
  `consignee` varchar(64) NOT NULL COMMENT '收货人姓名',
  `province` varchar(64) NOT NULL COMMENT '省份',
  `city` varchar(64) NOT NULL COMMENT '市',
  `district` varchar(64) NOT NULL COMMENT '区',
  `detailAddress` varchar(64) NOT NULL COMMENT '详细地址',
  `zipcode` varchar(64) NOT NULL COMMENT '邮政编码',
  `telephone` varchar(64) NOT NULL COMMENT '电话',
  `flag` int(1) COMMENT '是否是默认收货地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='地址表';