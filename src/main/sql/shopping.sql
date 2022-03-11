/*
 Navicat Premium Data Transfer

 Source Server         : aliyun
 Source Server Type    : MySQL
 Source Server Version : 50650
 Source Host           : 47.95.195.128:3306
 Source Schema         : shopping

 Target Server Type    : MySQL
 Target Server Version : 50650
 File Encoding         : 65001

 Date: 05/03/2022 20:15:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `admin_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员名称',
  `admin_number` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员账号',
  `admin_pwd` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员密码',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '状态',
  `CREATED_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATED_BY` int(11) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '测试员1', '22222222', '00000000', 1, 0, '2022-01-16 10:49:29', 1);
INSERT INTO `admin` VALUES (2, '测试员2', '11111111', '00000000', 1, 0, '2022-02-28 16:59:20', 1);

-- ----------------------------
-- Table structure for auction_schedule
-- ----------------------------
DROP TABLE IF EXISTS `auction_schedule`;
CREATE TABLE `auction_schedule`  (
  `auction_schedule_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '拍卖过程id',
  `goods_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `auction_schedule_price` decimal(32, 8) NULL DEFAULT NULL COMMENT '拍卖价格',
  `CREATED_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`auction_schedule_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = ' ' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of auction_schedule
-- ----------------------------
INSERT INTO `auction_schedule` VALUES (1, 25, 24, 2.00000000, '2022-03-03 14:43:08');
INSERT INTO `auction_schedule` VALUES (2, 25, 24, 3.00000000, '2022-03-03 14:44:15');
INSERT INTO `auction_schedule` VALUES (3, 25, 24, 4.00000000, '2022-03-03 14:48:44');
INSERT INTO `auction_schedule` VALUES (4, 25, 24, 5.00000000, '2022-03-03 14:50:50');
INSERT INTO `auction_schedule` VALUES (6, 25, 24, 6.00000000, '2022-03-03 15:25:12');
INSERT INTO `auction_schedule` VALUES (7, 25, 24, 7.00000000, '2022-03-03 15:30:13');
INSERT INTO `auction_schedule` VALUES (9, 25, 24, 8.00000000, '2022-03-03 15:33:07');
INSERT INTO `auction_schedule` VALUES (10, 25, 24, 31.00000000, '2022-03-03 15:36:03');
INSERT INTO `auction_schedule` VALUES (37, 25, 24, 32.00000000, '2022-03-03 17:03:27');
INSERT INTO `auction_schedule` VALUES (38, 25, 24, 101.00000000, '2022-03-03 18:31:17');
INSERT INTO `auction_schedule` VALUES (39, 26, 30, 30.00000000, '2022-03-03 23:58:07');
INSERT INTO `auction_schedule` VALUES (40, 26, 24, 13.00000000, '2022-03-04 01:01:21');
INSERT INTO `auction_schedule` VALUES (41, 26, 24, 15.00000000, '2022-03-04 01:01:28');
INSERT INTO `auction_schedule` VALUES (42, 31, 24, 31.00000000, '2022-03-04 13:32:45');
INSERT INTO `auction_schedule` VALUES (43, 25, 24, 101.00000000, '2022-03-04 13:33:51');
INSERT INTO `auction_schedule` VALUES (44, 26, 24, 16.00000000, '2022-03-04 14:05:06');
INSERT INTO `auction_schedule` VALUES (45, 26, 24, 51.00000000, '2022-03-04 14:05:19');

-- ----------------------------
-- Table structure for auctions
-- ----------------------------
DROP TABLE IF EXISTS `auctions`;
CREATE TABLE `auctions`  (
  `auctions_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '场次id',
  `goods_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `auctions_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '场次名称',
  `start` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `present_price` decimal(32, 8) NULL DEFAULT NULL COMMENT '目前价格',
  `present_person` int(11) NULL DEFAULT NULL COMMENT '目前标价人id',
  `fixed_price` decimal(32, 8) NULL DEFAULT NULL COMMENT '一口价',
  `starting_price` decimal(32, 8) NULL DEFAULT NULL COMMENT '起拍价',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '状态',
  `CREATED_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATED_BY` int(11) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`auctions_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '竞拍场次表 ' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of auctions
-- ----------------------------
INSERT INTO `auctions` VALUES (1, 1, '日常', '2022-01-16 19:45:34', '2022-01-16 19:45:34', 11.00000000, 1, 11.00000000, 11.00000000, 1, '2022-01-16 21:11:31', 1);
INSERT INTO `auctions` VALUES (10, 25, '钢笔拍卖', '2022-03-04 00:25:48', '2022-03-04 23:26:09', 101.00000000, 24, 100.00000000, 30.00000000, 1, '2022-03-03 13:27:07', 1);
INSERT INTO `auctions` VALUES (20, 26, '耳机', '2022-02-01 00:00:00', '2022-04-01 00:00:00', 51.00000000, 24, 50.00000000, 10.00000000, 1, '2022-03-04 01:00:21', 24);
INSERT INTO `auctions` VALUES (21, 30, '手机拍卖', '2022-03-04 00:00:00', '2022-03-20 00:00:00', NULL, NULL, 850.00000000, 778.00000000, 0, '2022-03-04 08:41:47', 30);
INSERT INTO `auctions` VALUES (22, 31, '二手书', '2022-03-04 00:00:00', '2022-03-20 00:00:00', 31.00000000, 24, 30.00000000, 15.00000000, 1, '2022-03-04 08:44:00', 30);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `category_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '状态',
  `CREATED_BY` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物品类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '宿舍用品', 0, 1, '2022-01-16 10:50:05');
INSERT INTO `category` VALUES (2, '电子产品', 0, 1, '2022-02-28 23:07:18');
INSERT INTO `category` VALUES (3, '二手书', 0, 1, '2022-02-28 23:07:56');
INSERT INTO `category` VALUES (4, '文具', 0, 1, '2022-02-28 23:08:13');

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat`  (
  `chat_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `sell_user` int(11) NULL DEFAULT NULL COMMENT '发送方id',
  `buy_user` int(11) NULL DEFAULT NULL COMMENT '接收方id',
  `msg_type` int(11) NULL DEFAULT 0 COMMENT '消息类型 0 文字 1 图片',
  `msg_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息内容',
  `read_status_sell` int(11) NULL DEFAULT 1 COMMENT '接收消息方是否已读，1未读,0已读',
  `read_status_buy` int(11) NULL DEFAULT 1 COMMENT '买家是否已读',
  `other` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留',
  `is_delete_buy` int(11) NULL DEFAULT NULL COMMENT '买家消息状态',
  `is_delete_sell` int(11) NULL DEFAULT 0 COMMENT '卖家消息状态',
  `CREATED_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '消息时间',
  PRIMARY KEY (`chat_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of chat
-- ----------------------------
INSERT INTO `chat` VALUES (1, 30, 24, 0, '你好你好', 0, 0, '30', 0, 0, '2022-03-04 16:56:08');
INSERT INTO `chat` VALUES (2, 30, 24, 0, '很高兴认识你', 0, 0, '24', 0, 0, '2022-03-04 16:56:23');
INSERT INTO `chat` VALUES (3, 30, 24, 1, 'https://ihchina.slienceme.xyz/image/file/20220303/20220303ac85a5e2d0f14abc885c8c6abfaf0b15.jpg', 0, 0, '30', 0, 0, '2022-03-04 16:56:54');
INSERT INTO `chat` VALUES (4, 30, 24, 1, 'https://ihchina.slienceme.xyz/image/file/20220304/2022030413523c5b4f574a58a397331d6a080282.jpg', 0, 0, '24', 0, 0, '2022-03-04 16:57:04');
INSERT INTO `chat` VALUES (5, 24, 30, 1, 'https://ihchina.slienceme.xyz/image/file/20220303/20220303ac85a5e2d0f14abc885c8c6abfaf0b15.jpg', 0, 0, '30', 0, 0, '2022-03-04 16:56:54');
INSERT INTO `chat` VALUES (6, 24, 30, 0, '你好', 0, 0, '30', 0, 0, '2022-03-03 16:56:54');
INSERT INTO `chat` VALUES (7, 24, 30, 0, 's', 0, 0, NULL, 0, 0, '2022-03-05 12:01:37');
INSERT INTO `chat` VALUES (8, 24, 30, 0, '4', 0, 0, NULL, 0, 0, '2022-03-05 12:09:55');
INSERT INTO `chat` VALUES (9, 24, 30, 0, '你说什么', 0, 0, NULL, 0, 0, '2022-03-05 12:10:13');
INSERT INTO `chat` VALUES (10, 24, 30, 0, 'yaosai', 0, 0, NULL, 0, 0, '2022-03-05 12:13:59');
INSERT INTO `chat` VALUES (11, 24, 30, 0, '你这商品不行啊', 0, 0, NULL, 0, 0, '2022-03-05 12:17:37');
INSERT INTO `chat` VALUES (12, 24, 30, 0, '有问题', 0, 0, NULL, 0, 0, '2022-03-05 12:17:50');
INSERT INTO `chat` VALUES (13, 24, 30, 0, '你退货吧', 0, 0, NULL, 0, 0, '2022-03-05 12:21:06');
INSERT INTO `chat` VALUES (14, 24, 30, 1, 'https://ihchina.slienceme.xyz/image/file/20220305/202203059261f0989a0142059970c15096e0d373.jpg', 0, 0, NULL, 0, 0, '2022-03-05 13:57:34');
INSERT INTO `chat` VALUES (15, 24, 30, 1, 'https://ihchina.slienceme.xyz/image/file/20220305/2022030548ce0e02ca444faa966aa4c11921445b.jpg', 0, 0, NULL, 0, 0, '2022-03-05 13:57:57');
INSERT INTO `chat` VALUES (16, 1, 24, 0, '你好', 0, 0, NULL, 0, 0, '2022-03-04 15:51:21');
INSERT INTO `chat` VALUES (18, 30, 24, 0, '可以', 0, 0, NULL, 0, 0, '2022-03-05 18:27:49');
INSERT INTO `chat` VALUES (20, 30, 24, 1, 'https://ihchina.slienceme.xyz/image/file/20220305/2022030540572e683ea142aa8561439063e24e75.jpg', 0, 0, NULL, 0, 0, '2022-03-05 18:29:32');
INSERT INTO `chat` VALUES (21, 24, 30, 0, '好吃', 0, 0, NULL, 0, 0, '2022-03-05 18:29:50');
INSERT INTO `chat` VALUES (22, 30, 24, 0, '没问题', 0, 0, NULL, 0, 0, '2022-03-05 18:30:01');
INSERT INTO `chat` VALUES (23, 30, 24, 0, '可以可以', 0, 0, NULL, 0, 0, '2022-03-05 18:30:08');
INSERT INTO `chat` VALUES (24, 30, 24, 0, '十分合理', 0, 0, NULL, 0, 0, '2022-03-05 18:30:24');
INSERT INTO `chat` VALUES (25, 24, 30, 0, '哈哈哈', 0, 1, NULL, NULL, 0, '2022-03-05 18:41:20');
INSERT INTO `chat` VALUES (26, 30, 24, 1, 'https://ihchina.slienceme.xyz/image/file/20220305/2022030595f17f2998ae4f949e1b116cf2993f67.jpg', 0, 1, NULL, NULL, 0, '2022-03-05 18:43:00');
INSERT INTO `chat` VALUES (27, 30, 24, 0, '突然', 0, 1, NULL, NULL, 0, '2022-03-05 18:43:07');
INSERT INTO `chat` VALUES (28, 30, 24, 0, '可以', 0, 1, NULL, NULL, 0, '2022-03-05 18:43:11');
INSERT INTO `chat` VALUES (29, 30, 24, 1, 'https://ihchina.slienceme.xyz/image/file/20220305/202203050f6a8c011a034be59cb19be339f332fb.jpg', 0, 1, NULL, NULL, 0, '2022-03-05 18:47:06');
INSERT INTO `chat` VALUES (30, 24, 30, 0, '你好', 0, 1, NULL, NULL, 0, '2022-03-05 18:49:11');
INSERT INTO `chat` VALUES (31, 30, 24, 0, '没有已读标识', 0, 1, NULL, NULL, 0, '2022-03-05 18:51:27');
INSERT INTO `chat` VALUES (32, 30, 24, 1, 'https://ihchina.slienceme.xyz/image/file/20220305/20220305c2d84b8f8e4f408b9220af19df508938.jpg', 0, 1, NULL, NULL, 0, '2022-03-05 18:51:45');
INSERT INTO `chat` VALUES (33, 24, 30, 0, '好', 0, 1, NULL, NULL, 0, '2022-03-05 18:52:41');
INSERT INTO `chat` VALUES (34, 24, 30, 0, '你好', 0, 1, NULL, NULL, 0, '2022-03-05 18:58:18');
INSERT INTO `chat` VALUES (35, 30, 24, 0, '你好', 0, 1, NULL, NULL, 0, '2022-03-05 18:58:49');
INSERT INTO `chat` VALUES (36, 24, 30, 0, '你好', 0, 1, NULL, NULL, 0, '2022-03-05 19:00:00');
INSERT INTO `chat` VALUES (37, 24, 30, 0, '你好', 0, 1, NULL, NULL, 0, '2022-03-05 19:00:22');
INSERT INTO `chat` VALUES (38, 30, 24, 0, '非常好', 0, 1, NULL, NULL, 0, '2022-03-05 19:00:25');
INSERT INTO `chat` VALUES (42, 30, 24, 0, '完美', 0, 1, NULL, NULL, 0, '2022-03-05 19:11:17');
INSERT INTO `chat` VALUES (43, 30, 24, 0, '你好', 0, 1, NULL, NULL, 0, '2022-03-05 19:21:09');
INSERT INTO `chat` VALUES (44, 30, 24, 0, '感觉很舒服', 0, 1, NULL, NULL, 0, '2022-03-05 19:26:14');
INSERT INTO `chat` VALUES (45, NULL, NULL, 0, '测试', 1, 1, NULL, NULL, 0, '2022-03-05 19:28:25');
INSERT INTO `chat` VALUES (46, NULL, NULL, 0, '测试', 1, 1, NULL, NULL, 0, '2022-03-05 19:30:49');
INSERT INTO `chat` VALUES (47, 35, 24, 0, '测试', 0, 1, NULL, NULL, 0, '2022-03-05 19:49:06');
INSERT INTO `chat` VALUES (48, 24, 35, 0, '你好', 0, 1, NULL, NULL, 0, '2022-03-05 19:51:17');
INSERT INTO `chat` VALUES (49, 35, 24, 0, '测试', 0, 1, NULL, NULL, 0, '2022-03-05 19:53:58');

-- ----------------------------
-- Table structure for community_show
-- ----------------------------
DROP TABLE IF EXISTS `community_show`;
CREATE TABLE `community_show`  (
  `show_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '展示id',
  `show_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '展示名称',
  `show_img_id` int(11) NULL DEFAULT NULL COMMENT '照片',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '状态',
  `CREATED_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATED_BY` int(11) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`show_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工艺展示表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of community_show
-- ----------------------------
INSERT INTO `community_show` VALUES (1, '为山区儿童捐赠日用品', 20, 0, '2022-02-28 18:03:13', 1);
INSERT INTO `community_show` VALUES (2, '参加社区清洁公益活动', 21, 0, '2022-03-01 22:51:39', 1);
INSERT INTO `community_show` VALUES (3, '积极开展保护楠溪江环保公益活动', 22, 0, '2022-03-01 22:51:41', 1);

-- ----------------------------
-- Table structure for complaint
-- ----------------------------
DROP TABLE IF EXISTS `complaint`;
CREATE TABLE `complaint`  (
  `complaint_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '投诉id',
  `orders_id` int(11) NULL DEFAULT NULL COMMENT '订单id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '申请人',
  `remark` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注-可以填写申诉结果',
  `complaint_status` int(11) NULL DEFAULT NULL COMMENT '投诉状态',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '状态',
  `CREATED_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATED_BY` int(11) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`complaint_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '投诉表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of complaint
-- ----------------------------
INSERT INTO `complaint` VALUES (1, 1, 1, '1212', 1, 0, '2022-02-28 18:12:17', 1);
INSERT INTO `complaint` VALUES (2, 29, 24, NULL, 1, 0, '2022-03-03 22:13:45', 24);
INSERT INTO `complaint` VALUES (3, 29, 24, NULL, 1, 0, '2022-03-03 22:14:16', 24);
INSERT INTO `complaint` VALUES (4, 29, 24, NULL, 1, 0, '2022-03-03 22:15:12', 24);
INSERT INTO `complaint` VALUES (5, 29, 24, NULL, 1, 0, '2022-03-03 22:15:12', 24);
INSERT INTO `complaint` VALUES (6, 29, 24, NULL, 1, 0, '2022-03-03 22:15:55', 24);
INSERT INTO `complaint` VALUES (7, 29, 24, 'j', 1, 0, '2022-03-03 22:20:24', 24);
INSERT INTO `complaint` VALUES (8, 30, 30, '测试一下投诉', 1, 1, '2022-03-04 00:00:57', 30);

-- ----------------------------
-- Table structure for complaint_status
-- ----------------------------
DROP TABLE IF EXISTS `complaint_status`;
CREATE TABLE `complaint_status`  (
  `complaint_status_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '投诉状态id',
  `complaint_status_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投诉状态类型名称',
  PRIMARY KEY (`complaint_status_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '投诉状态类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of complaint_status
-- ----------------------------
INSERT INTO `complaint_status` VALUES (1, '待受理');
INSERT INTO `complaint_status` VALUES (2, '已受理');
INSERT INTO `complaint_status` VALUES (3, '待商家处理');
INSERT INTO `complaint_status` VALUES (4, '商家已处理');
INSERT INTO `complaint_status` VALUES (5, '待买家同意协商');
INSERT INTO `complaint_status` VALUES (6, '已完成');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `goods_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `goods_info` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `goods_price` decimal(32, 8) NULL DEFAULT NULL COMMENT '商品价格',
  `state_on` int(11) NULL DEFAULT NULL COMMENT '是否上架',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '商品分类id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '所属用户id',
  `goods_img_id` int(11) NULL DEFAULT NULL COMMENT '商品照片',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '状态',
  `CREATED_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '床上桌', '就是放到床上的桌子', 80.00000000, 3, 1, 1, 14, 0, '2022-01-16 10:51:11');
INSERT INTO `goods` VALUES (5, '罗技鼠标', '罗技鼠标99新', 80.00000000, 4, 2, 24, 15, 0, '2022-02-28 23:21:18');
INSERT INTO `goods` VALUES (15, '数据库原理及应用', '本书是学校专业老师教学用书，有需要的可以买', 10.00000000, 5, 3, 24, 25, 0, '2022-03-01 23:45:31');
INSERT INTO `goods` VALUES (24, '数据结构c语言版', '数据结构c语言版，严蔚敏，李冬梅、吴伟民编著', 11.00000000, 4, 3, 24, 32, 0, '2022-03-02 16:32:15');
INSERT INTO `goods` VALUES (25, '钢笔', '毕加索(PICASSO903钢笔/墨水笔/签字笔商务办公成人学生用笔', 96.00000000, 3, 4, 24, 33, 0, '2022-03-02 16:35:01');
INSERT INTO `goods` VALUES (26, '英语考试耳机', '四级耳机46级调频六级听力大学专用英语耳麦考试头戴式', 20.00000000, 3, 4, 24, 34, 0, '2022-03-02 17:58:20');
INSERT INTO `goods` VALUES (27, '记事贴', '得力(deli)400张4色浅调系列淡彩简约便利贴 76*76mm便签本/记事贴/ 自粘留言本', 5.00000000, 5, 4, 24, 35, 0, '2022-03-02 18:03:16');
INSERT INTO `goods` VALUES (29, '拉面丸子干吃面边角料零食爆品款解馋小吃即食怀旧网红掌心脆装罐', '好吃不贵', 9.90000000, 5, 1, 30, 38, 0, '2022-03-04 08:38:54');
INSERT INTO `goods` VALUES (30, '二手苹果iPhone8手机正品原装iPhoneX 8Plus全网通8代8P国行7代X', '先到先得', 778.00000000, 2, 2, 30, 39, 0, '2022-03-04 08:40:55');
INSERT INTO `goods` VALUES (31, '二手书', '非常好用', 20.00000000, 3, 3, 30, 40, 0, '2022-03-04 08:43:32');
INSERT INTO `goods` VALUES (32, '笔记本电脑', '高配置Lenovo/联想笔记本电脑专用办公游戏本独显轻薄便携学生', 3000.00000000, 5, 2, 24, 41, 0, '2022-03-04 10:17:01');
INSERT INTO `goods` VALUES (33, '高等数学', '高等数学同济大学第七版上下册教材课本+同步辅导书讲义及习题集全解练习题册指南7版同济七版高等教育出版社大一高数学习指导考研', 40.00000000, 5, 3, 24, 42, 0, '2022-03-04 10:19:25');
INSERT INTO `goods` VALUES (34, '演草纸', '草稿纸大学生高中生考研专用a4草稿本米黄护眼草纸演算纸演草纸稿纸空白文稿纸学生用加厚实惠装批发', 5.80000000, 5, 4, 24, 43, 0, '2022-03-04 10:21:41');
INSERT INTO `goods` VALUES (35, '台灯', '希尔顿led台灯护眼灯AAA级学习专用防近视学生宿舍卧室书桌灯插电', 15.00000000, 1, 1, 24, 44, 0, '2022-03-04 10:26:03');
INSERT INTO `goods` VALUES (36, '圆规', '得力圆规金属专业绘图设计不锈钢学生文具考试标准工程制图工具圆规套尺套装含鸭嘴笔2.0mm圆规专用替芯', 8.00000000, 5, 4, 24, 45, 0, '2022-03-04 10:27:23');

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image`  (
  `image_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片id',
  `image_url` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `CREATED_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`image_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '图片表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of image
-- ----------------------------
INSERT INTO `image` VALUES (1, 'https://ihchina.slienceme.xyz/image/file/20220228/2022022803ba4e8ce1d5439fbf4fb664e49e3a15.jpg', '2022-02-28 18:00:08');
INSERT INTO `image` VALUES (5, 'D:/Java_study/project_shop/target/classes/file/5d452564282346f7bf676353d9ee57fb.jpg', '2022-01-17 16:07:32');
INSERT INTO `image` VALUES (6, 'D:/Java_study/project_shop/target/file/22c05bb9969f4cb9915be96bf5d81355.jpg', '2022-01-17 16:09:36');
INSERT INTO `image` VALUES (7, 'D:/Java_study/project_shop/target/file/ee19b379036045498d8ec3f1aa525575.jpg', '2022-01-17 16:21:09');
INSERT INTO `image` VALUES (8, 'D:/Java_study/project_shop/target/classes/file/380d26d37d904b28842de09ea71915b4.jpg', '2022-01-17 17:14:20');
INSERT INTO `image` VALUES (9, 'D:/Java_study/project_shop/target/file/3b29d3592b554542b859419d99e47967.jpg', '2022-01-17 17:17:53');
INSERT INTO `image` VALUES (10, 'D:/Java_study/project_shop/target/file/c6dfdb4e6992430fbfa49e1c0192ef4e.jpg', '2022-01-17 17:20:18');
INSERT INTO `image` VALUES (11, 'D:/Java_study/project_shop/target/file//2022-01-17/7474fa1b490b4e40b2a006b8cc1c6cb2.jpg', '2022-01-17 17:28:55');
INSERT INTO `image` VALUES (12, 'D:/Java_study/project_shop/target/file/20220117/b876b83ba0644f5899bab6f3acedcb07.jpg', '2022-01-17 17:29:50');
INSERT INTO `image` VALUES (14, 'https://ihchina.slienceme.xyz/image/file/20220228/2022022866ba5ce6cefc46479c92f628233c99cc.png', '2022-02-28 23:13:20');
INSERT INTO `image` VALUES (15, 'https://ihchina.slienceme.xyz/image/file/20220228/20220228842ab268d5044e0990f94e5c95af8e2b.png', '2022-02-28 23:20:09');
INSERT INTO `image` VALUES (16, 'https://ihchina.slienceme.xyz/image/file/20220301/2022030187007bc022f84ff0b73c58c47e9aff7a.jpg', '2022-03-01 15:17:15');
INSERT INTO `image` VALUES (17, 'https://ihchina.slienceme.xyz/image/file/20220301/20220301d9a05a37bbf0481e80b6f50ed476e569.jpg', '2022-03-01 15:19:05');
INSERT INTO `image` VALUES (18, 'https://ihchina.slienceme.xyz/image/file/20220301/202203011fb39f9f706e4cf481175f3e31773e77.jpg', '2022-03-01 15:27:09');
INSERT INTO `image` VALUES (19, 'https://ihchina.slienceme.xyz/image/file/20220301/20220301bc3a179e82ab4ee197b1ef01fb7428ad.jpg', '2022-03-01 15:29:52');
INSERT INTO `image` VALUES (20, 'https://ihchina.slienceme.xyz/image/file/20220301/2022030122cdaa4912ac45c3a84b9ae0e74ab1ff.webp', '2022-03-01 22:51:20');
INSERT INTO `image` VALUES (21, 'https://ihchina.slienceme.xyz/image/file/20220301/20220301cd5e6b8ba14c40d1aad3bba0e99bfb4e.jpeg', '2022-03-01 22:52:32');
INSERT INTO `image` VALUES (22, 'https://ihchina.slienceme.xyz/image/file/20220301/20220301d6836c1127b54afcb449672d36026732.webp', '2022-03-01 22:53:30');
INSERT INTO `image` VALUES (23, 'https://ihchina.slienceme.xyz/image/file/20220301/202203018df1ca5ab991493f9fa11de16e29162c.png', '2022-03-01 23:44:52');
INSERT INTO `image` VALUES (24, 'https://ihchina.slienceme.xyz/image/file/20220301/20220301a6585351d0a14da29f7a778c6258cbf9.png', '2022-03-01 23:48:28');
INSERT INTO `image` VALUES (25, 'https://ihchina.slienceme.xyz/image/file/20220301/20220301b621877557944c19a4b0beb10b46700a.png', '2022-03-01 23:49:07');
INSERT INTO `image` VALUES (26, 'https://ihchina.slienceme.xyz/image/file/20220301/202203018a76018178ec4055ae285f1bfde8dc77.png', '2022-03-01 23:52:53');
INSERT INTO `image` VALUES (27, 'https://ihchina.slienceme.xyz/image/file/20220301/20220301acc6cfaa8cf04c0d8acd0a355c380624.png', '2022-03-01 23:54:23');
INSERT INTO `image` VALUES (28, 'https://ihchina.slienceme.xyz/image/file/20220302/202203026af701c9b43b470487ffb8d7a1445c86.png', '2022-03-02 00:09:30');
INSERT INTO `image` VALUES (29, 'https://ihchina.slienceme.xyz/image/file/20220302/20220302ed03ed36dd384a1db201624cdf860a95.webp', '2022-03-02 00:10:01');
INSERT INTO `image` VALUES (30, 'https://ihchina.slienceme.xyz/image/file/20220302/202203024c3f0c6ed73e4202b59668c691d7ca9b.webp', '2022-03-02 00:10:52');
INSERT INTO `image` VALUES (31, 'https://ihchina.slienceme.xyz/image/file/20220302/20220302409634eacb0d49e4bbd7d071016a8107.webp', '2022-03-02 00:11:32');
INSERT INTO `image` VALUES (32, 'https://ihchina.slienceme.xyz/image/file/20220302/202203021d7fd8159586457cac31d6e1370bb036.png', '2022-03-02 16:31:24');
INSERT INTO `image` VALUES (33, 'https://ihchina.slienceme.xyz/image/file/20220302/20220302e49e79079b8846a299a83ea759cbc9c5.jfif', '2022-03-02 16:34:57');
INSERT INTO `image` VALUES (34, 'https://ihchina.slienceme.xyz/image/file/20220302/20220302e5c8566529b643349e69f3fe100072df.png', '2022-03-02 17:58:17');
INSERT INTO `image` VALUES (35, 'https://ihchina.slienceme.xyz/image/file/20220302/20220302f9836bcde9e142f28049f7a58a2dd82c.png', '2022-03-02 18:03:13');
INSERT INTO `image` VALUES (36, 'https://ihchina.slienceme.xyz/image/file/20220302/202203021343d21cd2a448c5a6f99d530b43d213.webp', '2022-03-02 18:20:03');
INSERT INTO `image` VALUES (37, 'https://ihchina.slienceme.xyz/image/file/20220303/20220303ac85a5e2d0f14abc885c8c6abfaf0b15.jpg', '2022-03-03 23:55:54');
INSERT INTO `image` VALUES (38, 'https://ihchina.slienceme.xyz/image/file/20220304/2022030413523c5b4f574a58a397331d6a080282.jpg', '2022-03-04 08:37:47');
INSERT INTO `image` VALUES (39, 'https://ihchina.slienceme.xyz/image/file/20220304/202203044bd3527fd2ce45aa87914dcc18d9bc0e.jpg', '2022-03-04 08:40:50');
INSERT INTO `image` VALUES (40, 'https://ihchina.slienceme.xyz/image/file/20220304/2022030498c5731a90cd4aecb60deef2ab289401.jpg', '2022-03-04 08:43:23');
INSERT INTO `image` VALUES (41, 'https://ihchina.slienceme.xyz/image/file/20220304/2022030431ef609e206c4e168fd2dc343b01ca8f.jpg', '2022-03-04 10:16:55');
INSERT INTO `image` VALUES (42, 'https://ihchina.slienceme.xyz/image/file/20220304/202203046d2202e8d47241be96c34754c8ca8eed.jpg', '2022-03-04 10:18:27');
INSERT INTO `image` VALUES (43, 'https://ihchina.slienceme.xyz/image/file/20220304/20220304f970df8ae2c145ca94e96189df90d55e.jpg', '2022-03-04 10:21:29');
INSERT INTO `image` VALUES (44, 'https://ihchina.slienceme.xyz/image/file/20220304/202203042641fbae8bb041abb2da6d06e3050b45.png', '2022-03-04 10:25:56');
INSERT INTO `image` VALUES (45, 'https://ihchina.slienceme.xyz/image/file/20220304/20220304a906677b1a1a4c058a5abf262b425f54.jpg', '2022-03-04 10:27:04');
INSERT INTO `image` VALUES (46, 'https://ihchina.slienceme.xyz/image/file/20220304/20220304caf23259bd134aec870ccd93e86e6eb4.jpg', '2022-03-04 19:15:42');
INSERT INTO `image` VALUES (47, 'https://ihchina.slienceme.xyz/image/file/20220305/202203059261f0989a0142059970c15096e0d373.jpg', '2022-03-05 13:57:34');
INSERT INTO `image` VALUES (48, 'https://ihchina.slienceme.xyz/image/file/20220305/2022030548ce0e02ca444faa966aa4c11921445b.jpg', '2022-03-05 13:57:56');
INSERT INTO `image` VALUES (49, 'https://ihchina.slienceme.xyz/image/file/20220305/2022030540572e683ea142aa8561439063e24e75.jpg', '2022-03-05 18:29:32');
INSERT INTO `image` VALUES (50, 'https://ihchina.slienceme.xyz/image/file/20220305/2022030520ac44b439e7491f8f68e7e9aa3b64ce.jpg', '2022-03-05 18:34:12');
INSERT INTO `image` VALUES (51, 'https://ihchina.slienceme.xyz/image/file/20220305/2022030595f17f2998ae4f949e1b116cf2993f67.jpg', '2022-03-05 18:43:00');
INSERT INTO `image` VALUES (52, 'https://ihchina.slienceme.xyz/image/file/20220305/202203050f6a8c011a034be59cb19be339f332fb.jpg', '2022-03-05 18:47:06');
INSERT INTO `image` VALUES (53, 'https://ihchina.slienceme.xyz/image/file/20220305/20220305c2d84b8f8e4f408b9220af19df508938.jpg', '2022-03-05 18:51:45');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id 自增id',
  `menu_title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称 中文名',
  `menu_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单文件名 英文名',
  `menu_level` int(11) NULL DEFAULT NULL COMMENT '菜单级别',
  `menu_root_id` int(11) NULL DEFAULT NULL COMMENT '菜单根id 最根部的菜单id',
  `menu_parent_id` int(11) NULL DEFAULT NULL COMMENT '菜单父id 直属的父菜单id',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型',
  `menu_path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单路径 路由路径',
  `menu_router` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单完整路由 菜单完整路由',
  `menu_icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标 图标',
  `method` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口方法 get post put delete',
  `api_path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口路径 api_path',
  `hidden` int(11) NULL DEFAULT NULL COMMENT '是否隐藏',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态 0正常 1删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '平台菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '订单管理', 'OrdersIndex', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 08:58:11', '2021-08-10 08:58:11');
INSERT INTO `menu` VALUES (2, '新增', 'add', 2, 1, 1, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 08:58:57', '2021-08-10 08:58:57');
INSERT INTO `menu` VALUES (3, '编辑', 'edit', 2, 1, 1, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 08:59:00', '2021-08-10 08:59:00');
INSERT INTO `menu` VALUES (4, '删除', 'delete', 2, 1, 1, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 08:59:11', '2021-08-10 08:59:11');
INSERT INTO `menu` VALUES (5, '典当场次管理', 'PawnIndex', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 08:59:18', '2021-08-10 08:59:18');
INSERT INTO `menu` VALUES (6, '编辑', 'edit', 2, 5, 5, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 08:59:28', '2021-08-10 08:59:28');
INSERT INTO `menu` VALUES (7, '删除', 'delete', 2, 5, 5, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 08:59:34', '2021-08-10 08:59:34');
INSERT INTO `menu` VALUES (8, '竞拍场次管理', 'AuctionsIndex', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 09:02:42', '2021-08-10 09:02:42');
INSERT INTO `menu` VALUES (9, '新增', 'add', 2, 8, 8, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 09:04:22', '2021-08-10 09:04:22');
INSERT INTO `menu` VALUES (10, '编辑', 'edit', 2, 8, 8, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 09:06:32', '2021-08-10 09:06:32');
INSERT INTO `menu` VALUES (11, '删除', 'delete', 2, 8, 8, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 09:06:33', '2021-08-10 09:06:33');
INSERT INTO `menu` VALUES (12, '物品类型管理', 'CategoryIndex', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 09:06:34', '2021-08-10 09:06:34');
INSERT INTO `menu` VALUES (13, '新增', 'add', 2, 12, 12, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 09:06:35', '2021-08-10 09:06:35');
INSERT INTO `menu` VALUES (14, '编辑', 'edit', 2, 12, 12, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 09:06:38', '2021-08-10 09:06:38');
INSERT INTO `menu` VALUES (15, '删除', 'delete', 2, 12, 12, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 09:06:39', '2021-08-10 09:06:39');
INSERT INTO `menu` VALUES (16, '管理设置', 'ManageIndex', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 09:06:40', '2021-08-10 09:06:40');
INSERT INTO `menu` VALUES (17, '管理员管理', 'AdminIndex', 2, 16, 16, 0, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 09:06:41', '2021-08-10 09:06:41');
INSERT INTO `menu` VALUES (18, '新增', 'add', 3, 16, 17, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 09:06:43', '2021-08-10 09:06:43');
INSERT INTO `menu` VALUES (19, '重置密码', 'reset', 3, 16, 17, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 09:06:44', '2021-08-10 09:06:44');
INSERT INTO `menu` VALUES (20, '编辑', 'edit', 3, 16, 17, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 09:06:46', '2021-08-10 09:06:46');
INSERT INTO `menu` VALUES (21, '删除', 'delete', 3, 16, 17, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 09:15:17', '2021-08-10 09:15:17');
INSERT INTO `menu` VALUES (22, '角色管理', 'RoleIndex', 2, 16, 16, 0, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 09:15:18', '2021-08-10 09:15:18');
INSERT INTO `menu` VALUES (23, '新增', 'add', 3, 16, 22, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 09:15:19', '2021-08-10 09:15:19');
INSERT INTO `menu` VALUES (24, '编辑', 'edit', 3, 16, 22, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 09:15:21', '2021-08-10 09:15:21');
INSERT INTO `menu` VALUES (25, '删除', 'delete', 3, 16, 22, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2021-08-10 09:15:22', '2021-08-10 09:15:22');
INSERT INTO `menu` VALUES (26, '商品管理', 'GoodsManageIndex', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:13:14', '2022-02-28 17:13:14');
INSERT INTO `menu` VALUES (28, '新增', 'add', 3, 26, 27, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:14:55', '2022-02-28 17:14:55');
INSERT INTO `menu` VALUES (29, '编辑', 'edit', 3, 26, 27, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:14:57', '2022-02-28 17:14:57');
INSERT INTO `menu` VALUES (30, '删除', 'delete', 3, 26, 27, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:15:00', '2022-02-28 17:15:00');
INSERT INTO `menu` VALUES (31, '查看', 'select', 3, 26, 27, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:15:03', '2022-02-28 17:15:03');
INSERT INTO `menu` VALUES (32, '未上架商品', 'GoodsNoIndex', 2, 26, 26, 0, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:15:06', '2022-02-28 17:15:06');
INSERT INTO `menu` VALUES (33, '新增', 'add', 3, 26, 32, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:15:08', '2022-02-28 17:15:08');
INSERT INTO `menu` VALUES (34, '编辑', 'edit', 3, 26, 32, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:15:12', '2022-02-28 17:15:12');
INSERT INTO `menu` VALUES (35, '删除', 'delete', 3, 26, 32, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:15:14', '2022-02-28 17:15:14');
INSERT INTO `menu` VALUES (36, '查看', 'select', 3, 26, 32, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:15:22', '2022-02-28 17:15:22');
INSERT INTO `menu` VALUES (37, '已售商品', 'GoodsDoneIndex', 2, 26, 26, 0, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:15:56', '2022-02-28 17:15:56');
INSERT INTO `menu` VALUES (38, '新增', 'add', 3, 26, 37, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:16:08', '2022-02-28 17:16:08');
INSERT INTO `menu` VALUES (39, '编辑', 'edit', 3, 26, 37, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:16:09', '2022-02-28 17:16:09');
INSERT INTO `menu` VALUES (40, '删除', 'delete', 3, 26, 37, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:16:14', '2022-02-28 17:16:14');
INSERT INTO `menu` VALUES (41, '查看', 'select', 3, 26, 37, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:16:22', '2022-02-28 17:16:22');
INSERT INTO `menu` VALUES (42, '上架商品', 'GoodsOnIndex', 2, 26, 26, 0, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:16:24', '2022-02-28 17:16:24');
INSERT INTO `menu` VALUES (43, '新增', 'add', 3, 26, 42, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:16:26', '2022-02-28 17:16:26');
INSERT INTO `menu` VALUES (44, '编辑', 'edit', 3, 26, 42, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:16:27', '2022-02-28 17:16:27');
INSERT INTO `menu` VALUES (45, '删除', 'delete', 3, 26, 42, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:16:29', '2022-02-28 17:16:29');
INSERT INTO `menu` VALUES (46, '查看', 'select', 3, 26, 42, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:16:30', '2022-02-28 17:16:30');
INSERT INTO `menu` VALUES (47, '上架典当', 'GoodsPawnIndex', 2, 26, 26, 0, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:16:31', '2022-02-28 17:16:31');
INSERT INTO `menu` VALUES (48, '新增', 'add', 3, 26, 47, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:16:33', '2022-02-28 17:16:33');
INSERT INTO `menu` VALUES (49, '编辑', 'edit', 3, 26, 47, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:16:34', '2022-02-28 17:16:34');
INSERT INTO `menu` VALUES (50, '删除', 'delete', 3, 26, 47, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:16:46', '2022-02-28 17:16:46');
INSERT INTO `menu` VALUES (51, '查看', 'select', 3, 26, 47, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:16:50', '2022-02-28 17:16:50');
INSERT INTO `menu` VALUES (52, '成员管理', 'MemberIndex', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:16:53', '2022-02-28 17:16:53');
INSERT INTO `menu` VALUES (53, '新增', 'add', 2, 52, 52, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:16:54', '2022-02-28 17:16:54');
INSERT INTO `menu` VALUES (54, '编辑', 'edit', 2, 52, 52, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:16:56', '2022-02-28 17:16:56');
INSERT INTO `menu` VALUES (55, '删除', 'delete', 2, 52, 52, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:16:59', '2022-02-28 17:16:59');
INSERT INTO `menu` VALUES (56, '查看', 'select', 2, 52, 52, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:17:08', '2022-02-28 17:17:08');
INSERT INTO `menu` VALUES (57, '公益展示', 'CommunityIndex', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:17:09', '2022-02-28 17:17:09');
INSERT INTO `menu` VALUES (58, '新增', 'add', 2, 57, 57, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:17:11', '2022-02-28 17:17:11');
INSERT INTO `menu` VALUES (59, '编辑', 'edit', 2, 57, 57, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:17:13', '2022-02-28 17:17:13');
INSERT INTO `menu` VALUES (60, '删除', 'delete', 2, 57, 57, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:17:21', '2022-02-28 17:17:21');
INSERT INTO `menu` VALUES (61, '查看', 'select', 2, 57, 57, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:17:37', '2022-02-28 17:17:37');
INSERT INTO `menu` VALUES (62, '投诉管理', 'ComplaintIndex', 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:17:38', '2022-02-28 17:17:38');
INSERT INTO `menu` VALUES (63, '新增', 'add', 2, 62, 62, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:17:42', '2022-02-28 17:17:42');
INSERT INTO `menu` VALUES (64, '编辑', 'edit', 2, 62, 62, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:17:43', '2022-02-28 17:17:43');
INSERT INTO `menu` VALUES (65, '删除', 'delete', 2, 62, 62, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:18:13', '2022-02-28 17:18:13');
INSERT INTO `menu` VALUES (66, '查看', 'select', 2, 62, 62, 1, NULL, NULL, NULL, NULL, NULL, 0, 99, 0, '2022-02-28 17:20:06', '2022-02-28 17:20:06');

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role`  (
  `menu_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `menu_id` int(11) NULL DEFAULT NULL COMMENT '菜单ID',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`menu_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 578 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES (491, 0, 15);
INSERT INTO `menu_role` VALUES (492, 2, 1);
INSERT INTO `menu_role` VALUES (493, 1, 1);
INSERT INTO `menu_role` VALUES (494, 3, 1);
INSERT INTO `menu_role` VALUES (495, 4, 1);
INSERT INTO `menu_role` VALUES (496, 5, 1);
INSERT INTO `menu_role` VALUES (497, 6, 1);
INSERT INTO `menu_role` VALUES (498, 7, 1);
INSERT INTO `menu_role` VALUES (499, 8, 1);
INSERT INTO `menu_role` VALUES (500, 9, 1);
INSERT INTO `menu_role` VALUES (501, 10, 1);
INSERT INTO `menu_role` VALUES (502, 11, 1);
INSERT INTO `menu_role` VALUES (503, 12, 1);
INSERT INTO `menu_role` VALUES (504, 13, 1);
INSERT INTO `menu_role` VALUES (505, 14, 1);
INSERT INTO `menu_role` VALUES (506, 15, 1);
INSERT INTO `menu_role` VALUES (507, 17, 1);
INSERT INTO `menu_role` VALUES (508, 18, 1);
INSERT INTO `menu_role` VALUES (509, 19, 1);
INSERT INTO `menu_role` VALUES (510, 20, 1);
INSERT INTO `menu_role` VALUES (511, 21, 1);
INSERT INTO `menu_role` VALUES (512, 16, 1);
INSERT INTO `menu_role` VALUES (513, 22, 1);
INSERT INTO `menu_role` VALUES (514, 23, 1);
INSERT INTO `menu_role` VALUES (515, 24, 1);
INSERT INTO `menu_role` VALUES (516, 25, 1);
INSERT INTO `menu_role` VALUES (517, 32, 1);
INSERT INTO `menu_role` VALUES (518, 33, 1);
INSERT INTO `menu_role` VALUES (519, 34, 1);
INSERT INTO `menu_role` VALUES (520, 35, 1);
INSERT INTO `menu_role` VALUES (521, 36, 1);
INSERT INTO `menu_role` VALUES (522, 26, 1);
INSERT INTO `menu_role` VALUES (523, 37, 1);
INSERT INTO `menu_role` VALUES (524, 38, 1);
INSERT INTO `menu_role` VALUES (525, 39, 1);
INSERT INTO `menu_role` VALUES (526, 40, 1);
INSERT INTO `menu_role` VALUES (527, 41, 1);
INSERT INTO `menu_role` VALUES (528, 42, 1);
INSERT INTO `menu_role` VALUES (529, 43, 1);
INSERT INTO `menu_role` VALUES (530, 44, 1);
INSERT INTO `menu_role` VALUES (531, 45, 1);
INSERT INTO `menu_role` VALUES (532, 46, 1);
INSERT INTO `menu_role` VALUES (533, 47, 1);
INSERT INTO `menu_role` VALUES (534, 48, 1);
INSERT INTO `menu_role` VALUES (535, 49, 1);
INSERT INTO `menu_role` VALUES (536, 50, 1);
INSERT INTO `menu_role` VALUES (537, 51, 1);
INSERT INTO `menu_role` VALUES (538, 52, 1);
INSERT INTO `menu_role` VALUES (539, 53, 1);
INSERT INTO `menu_role` VALUES (540, 54, 1);
INSERT INTO `menu_role` VALUES (541, 55, 1);
INSERT INTO `menu_role` VALUES (542, 56, 1);
INSERT INTO `menu_role` VALUES (543, 57, 1);
INSERT INTO `menu_role` VALUES (544, 58, 1);
INSERT INTO `menu_role` VALUES (545, 59, 1);
INSERT INTO `menu_role` VALUES (546, 60, 1);
INSERT INTO `menu_role` VALUES (547, 61, 1);
INSERT INTO `menu_role` VALUES (548, 62, 1);
INSERT INTO `menu_role` VALUES (549, 63, 1);
INSERT INTO `menu_role` VALUES (550, 64, 1);
INSERT INTO `menu_role` VALUES (551, 65, 1);
INSERT INTO `menu_role` VALUES (552, 66, 1);
INSERT INTO `menu_role` VALUES (553, 5, 2);
INSERT INTO `menu_role` VALUES (554, 7, 2);
INSERT INTO `menu_role` VALUES (555, 8, 2);
INSERT INTO `menu_role` VALUES (556, 10, 2);
INSERT INTO `menu_role` VALUES (557, 12, 2);
INSERT INTO `menu_role` VALUES (558, 13, 2);
INSERT INTO `menu_role` VALUES (559, 15, 2);
INSERT INTO `menu_role` VALUES (560, 17, 2);
INSERT INTO `menu_role` VALUES (561, 18, 2);
INSERT INTO `menu_role` VALUES (562, 19, 2);
INSERT INTO `menu_role` VALUES (563, 20, 2);
INSERT INTO `menu_role` VALUES (564, 22, 2);
INSERT INTO `menu_role` VALUES (565, 23, 2);
INSERT INTO `menu_role` VALUES (566, 24, 2);
INSERT INTO `menu_role` VALUES (567, 25, 2);
INSERT INTO `menu_role` VALUES (568, 27, 2);
INSERT INTO `menu_role` VALUES (569, 28, 2);
INSERT INTO `menu_role` VALUES (570, 29, 2);
INSERT INTO `menu_role` VALUES (571, 30, 2);
INSERT INTO `menu_role` VALUES (572, 32, 2);
INSERT INTO `menu_role` VALUES (573, 33, 2);
INSERT INTO `menu_role` VALUES (574, 34, 2);
INSERT INTO `menu_role` VALUES (575, 35, 2);
INSERT INTO `menu_role` VALUES (576, 16, 2);
INSERT INTO `menu_role` VALUES (577, 26, 2);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `orders_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `serial_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流水号',
  `goods_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `sell_users_id` int(11) NULL DEFAULT NULL COMMENT '竞拍商品拥有者id',
  `buy_users_id` int(11) NULL DEFAULT NULL COMMENT '购买商品用户id',
  `buy_price` decimal(32, 8) NULL DEFAULT NULL COMMENT '购买价格-成本价格',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '状态',
  `CREATED_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATED_BY` int(11) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`orders_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (0, 'SP20220303130200000001', 1, 1, 1, 11.00000000, 0, '2022-03-03 13:02:00', 1);
INSERT INTO `orders` VALUES (21, 'SP20220303130201000005', 5, 1, 1, 11.00000000, 0, '2022-03-03 13:02:01', 1);
INSERT INTO `orders` VALUES (27, 'SP20220303130201000015', 15, 1, 1, 1231.00000000, 0, '2022-03-03 13:02:01', 1);
INSERT INTO `orders` VALUES (31, 'SP20220304133245000031', 31, 30, 24, 31.00000000, 0, '2022-03-04 13:32:45', 1);
INSERT INTO `orders` VALUES (32, 'SP20220304133351000025', 25, 24, 24, 101.00000000, 0, '2022-03-04 13:33:51', 1);
INSERT INTO `orders` VALUES (33, 'SP20220304140519000026', 26, 24, 24, 51.00000000, 0, '2022-03-04 14:05:19', 1);

-- ----------------------------
-- Table structure for pawn
-- ----------------------------
DROP TABLE IF EXISTS `pawn`;
CREATE TABLE `pawn`  (
  `auctions_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '典当id',
  `goods_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `pawn_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '典当名称',
  `start` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `present_price` decimal(32, 8) NULL DEFAULT NULL COMMENT '目前价格',
  `present_person` int(11) NULL DEFAULT NULL COMMENT '目前标价人id',
  `fixed_price` decimal(32, 8) NULL DEFAULT NULL COMMENT '一口价',
  `starting_price` decimal(32, 8) NULL DEFAULT NULL COMMENT '起拍价',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '状态',
  `CREATED_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATED_BY` int(11) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`auctions_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '典当场次表 ' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pawn
-- ----------------------------
INSERT INTO `pawn` VALUES (4, 24, '数据结构c语言版', '2022-03-03 08:00:00', '2022-03-03 11:00:00', 10.00000000, 1, 80.00000000, 40.00000000, 1, '2022-03-02 23:20:19', 24);
INSERT INTO `pawn` VALUES (6, 15, '数据库原理及应用', '2022-02-28 17:38:56', '2022-02-28 17:38:59', 1231.00000000, 1, 231.00000000, 123.00000000, 1, '2022-02-28 17:39:12', 1);
INSERT INTO `pawn` VALUES (7, 25, '钢笔', '2022-03-03 08:00:00', '2022-03-03 11:00:00', 11.00000000, 1, 80.00000000, 40.00000000, 1, '2022-03-02 23:20:19', 24);
INSERT INTO `pawn` VALUES (8, 26, '英语考试耳机', '2022-03-03 08:00:00', '2022-03-03 11:00:00', 96.00000000, 1, 80.00000000, 40.00000000, 1, '2022-03-02 23:20:19', 24);
INSERT INTO `pawn` VALUES (9, 27, '记事贴', '2022-03-03 08:00:00', '2022-03-03 11:00:00', 20.00000000, 1, 80.00000000, 40.00000000, 1, '2022-03-02 23:20:19', 24);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_status` int(11) NULL DEFAULT NULL COMMENT '状态 0正常 1删除',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', 0, '2021-02-22 09:47:49', 1);
INSERT INTO `role` VALUES (2, '管理员', 0, '2021-08-13 14:51:19', 1);
INSERT INTO `role` VALUES (3, '成员', 0, '2021-08-13 14:51:34', 1);
INSERT INTO `role` VALUES (4, '演示账号', 0, '2021-08-13 14:54:26', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `openid` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'openid',
  `id_card` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `user_number` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账号学号',
  `user_pwd` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_avatarurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `user_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `user_gender` int(11) NULL DEFAULT 1 COMMENT '用户性别',
  `user_phone` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户手机',
  `user_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户地址',
  `user_credit` int(11) NULL DEFAULT 100 COMMENT '信用值',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '状态',
  `CREATED_TIME` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATED_BY` int(11) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'kongchulai', 'aaaa', 'aaaaaa', '00000000', 'https://thirdwx.qlogo.cn/mmopen/vi_32/UlI1nqhJYGcD4xib2mPdsX4pibqdOEcjrcYvwEXmI9gr9FqNyB7SrJhiaaicbSUicXicib7T2mkzaAgr36CsLTvYM41EQ/132', '1111', 1, '66666666666', '河北石家庄', 100, 0, '2022-01-16 10:48:38', 1);
INSERT INTO `user` VALUES (2, 'kongchulai', 'string', 'string', 'string', NULL, '11111111111111111', 2, '66666666666', 'string', 100, 1, '2022-01-16 10:48:38', 1);
INSERT INTO `user` VALUES (6, 'string', 'string', 'string', 'string', NULL, 'string', 1, 'string', 'string', 100, 0, '2022-02-28 20:45:34', 0);
INSERT INTO `user` VALUES (24, 'orgCu5UONbG70W002r_-379xmzkk', '101202200106060081', '999999999', 'https://thirdwx.qlogo.cn/mmopen/vi_32/P14jrWHgs7D5RBvC0oeXjtmnWcGjesxibIoNbicRvQ4vKq5r4UiaR8KIwh3PyqA4hCEwhHk6hKttkWu9GkffRib8CQ/132', 'https://thirdwx.qlogo.cn/mmopen/vi_32/P14jrWHgs7D5RBvC0oeXjtmnWcGjesxibIoNbicRvQ4vKq5r4UiaR8KIwh3PyqA4hCEwhHk6hKttkWu9GkffRib8CQ/132', '测试员', 1, '66666666666', '河北省邯郸市峰峰矿区', 100, 0, '2022-02-28 22:50:18', 0);
INSERT INTO `user` VALUES (30, 'orgCu5XQP84jftrmj_ofz8oPaiZs', '111111111111111111', '999999999', NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/UlI1nqhJYGcD4xib2mPdsX4pibqdOEcjrcYvwEXmI9gr9FqNyB7SrJhiaaicbSUicXicib7T2mkzaAgr36CsLTvYM41EQ/132', '测试员1', 1, '66666666666', '河北省石家庄市裕华区裕翔街河北科技大学', 100, 0, '2022-03-03 23:54:40', 0);
INSERT INTO `user` VALUES (35, 'orgCu5e4M0l6of-4wqQCxxN32ySk', NULL, NULL, NULL, 'https://thirdwx.qlogo.cn/mmopen/vi_32/KblOibLic757ylPnAsZicHqd4ACktdreW0OLWcUTibVxnxIeicRMXeCMyfJ6NYiatKxPcFokvRtWJwqAm5IUod8V6umA/132', '蜡笔没有心', 1, NULL, NULL, 100, 0, '2022-03-05 19:30:33', 0);

SET FOREIGN_KEY_CHECKS = 1;
