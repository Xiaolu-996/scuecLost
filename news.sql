/*
 Navicat Premium Data Transfer

 Source Server         : MySQL 8
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : news

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 04/03/2022 15:03:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (63);
INSERT INTO `hibernate_sequence` VALUES (63);
INSERT INTO `hibernate_sequence` VALUES (63);
INSERT INTO `hibernate_sequence` VALUES (63);
INSERT INTO `hibernate_sequence` VALUES (63);

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` bigint NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `news_id` bigint NULL DEFAULT NULL,
  `parent_comment_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK6a7k00t6fuf9xfoiqukbvhq8f`(`news_id`) USING BTREE,
  INDEX `FK4jj284r3pb7japogvo6h72q95`(`parent_comment_id`) USING BTREE,
  CONSTRAINT `FK4jj284r3pb7japogvo6h72q95` FOREIGN KEY (`parent_comment_id`) REFERENCES `t_comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK6a7k00t6fuf9xfoiqukbvhq8f` FOREIGN KEY (`news_id`) REFERENCES `t_news` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------

-- ----------------------------
-- Table structure for t_news
-- ----------------------------
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE `t_news`  (
  `id` bigint NOT NULL,
  `appreciation` bit(1) NOT NULL,
  `comment` bit(1) NOT NULL,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `first_picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `published` bit(1) NOT NULL,
  `recommend` bit(1) NOT NULL,
  `share` bit(1) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `views` int NULL DEFAULT NULL,
  `type_id` bigint NULL DEFAULT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK2tste192abqqu7pxnklbhjw8q`(`user_id`) USING BTREE,
  INDEX `FKgphr3jsxifvwnft80exmuwpt3`(`type_id`) USING BTREE,
  CONSTRAINT `FK2tste192abqqu7pxnklbhjw8q` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKgphr3jsxifvwnft80exmuwpt3` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_news
-- ----------------------------
INSERT INTO `t_news` VALUES (49, b'0', b'1', '今天中午12点放学吃饭的时候在9栋教学楼丢失了一张校园卡，名字是赵伦祺，学号是201821091006，希望有捡到的同学可以联系我的qq：12345678，或者在评论区留言，我去找你(*╹▽╹*)', '2021-07-11 01:59:55.165000', '中午12点在9栋教学楼丢失了一张校园卡，名字是赵伦祺，学号是201821091006', 'https://tse2-mm.cn.bing.net/th/id/OIP-C.y55gRqCYHtBnFhlqNkNdhgHaEq?w=250&h=180&c=7&o=5&pid=1.7', NULL, b'1', b'0', b'0', '今天中午12点丢失了一张校园卡', '2021-07-11 02:04:24.805000', 1, 2, 1);
INSERT INTO `t_news` VALUES (50, b'0', b'0', '今天中午在1食堂靠西门的位置吃放，水杯放在桌子上忘记拿了，希望捡到的同学可以联系我，必有重谢！我的联系方式是qq：987654321，姓名：路建彬', '2021-07-11 02:04:11.529000', '中午1食堂丢失水杯', 'https://tse1-mm.cn.bing.net/th/id/OIP-C.awQAeo6bKcQM6o-O5oLYjAHaFj?w=232&h=180&c=7&o=5&pid=1.7', NULL, b'1', b'0', b'0', '在1食堂丢失本人的水杯，希望好心人帮我找回', '2021-07-11 02:04:19.485000', 1, 48, 1);
INSERT INTO `t_news` VALUES (51, b'0', b'1', '今天下午在南书院的2楼复印身份证时，不小心将身份证落在了2楼的打印店里。身份证姓名是杨宇林，身份证号是314124xxxx0921，希望捡到的同学能尽快联系我，我的电话是15010319283', '2021-07-11 02:09:30.243000', '身份证姓名是杨宇林，身份证号是314124xxxx0921，希望捡到的同学能尽快联系我，我的电话是15010319283', 'https://tse3-mm.cn.bing.net/th/id/OIP-C.yAYRlPXWG3w5ZWlg0ptgVwAAAA?w=229&h=171&c=7&o=5&pid=1.7', NULL, b'1', b'1', b'0', '下午在南书院丢失身份证，姓名是杨宇林', '2021-07-11 02:09:30.243000', 3, 47, 1);
INSERT INTO `t_news` VALUES (52, b'0', b'1', '今天晚上7点左右在15栋的203教室丢失了一本高数辅导讲义，放在第2排的桌柜里，吃完饭回来的时候发现找不到了，希望捡到这本书的同学能将它还给我，它对我意义重大，我的联系方式是qq：124123123，姓名：连宇豪', '2021-07-11 02:13:25.639000', '15栋的203教室丢失了一本高数辅导讲义，联系方式是qq：124123123，姓名：连宇豪', 'https://tse4-mm.cn.bing.net/th/id/OIP-C.9NmcfTjfwEt29bY6o0ShQQAAAA?w=214&h=214&c=7&o=5&pid=1.7', NULL, b'1', b'0', b'0', '今天晚上在15栋丢失学习资料', '2021-07-11 02:13:25.639000', 1, 36, 1);
INSERT INTO `t_news` VALUES (53, b'0', b'0', '昨天晚上在宿舍楼楼下丢失了斯伯丁篮球一颗，丢失前它被放在宿舍楼前的健身器材上面，一晚上忘记拿回宿舍就在那放了一晚上，今天早上发现找不到了，希望见过它的同学可以联系我，我的qq是1923181313，姓名是张三', '2021-07-11 02:17:32.969000', '昨天晚上在宿舍楼楼下丢失了斯伯丁篮球一颗，我的qq是1923181313，姓名是张三', 'https://tse3-mm.cn.bing.net/th/id/OIP-C.1_74h6s3iuRmOpFDW1S9FwHaHa?w=197&h=198&c=7&o=5&pid=1.7', NULL, b'1', b'0', b'0', '昨天晚上在宿舍楼附近丢失了一颗篮球', '2021-07-11 02:17:32.969000', 2, 37, 1);
INSERT INTO `t_news` VALUES (54, b'0', b'1', '今天早上8点左右在南区操场跑完步休息的时候，不慎将手机丢失在操场中间的草坪上，手机型号是黑色小米10，希望捡到同学可以去南区宿舍6楼612联系我，我的姓名是李四', '2021-07-11 02:23:52.771000', '今天早上8点左右在南区操场丢失黑色小米10一部', 'https://tse1-mm.cn.bing.net/th/id/OIP-C.pnU8qemhg9cucnJBx2_dwQHaHa?w=185&h=185&c=7&o=5&pid=1.7', NULL, b'1', b'1', b'0', '今天早上在南区操场丢失手机一部', '2021-07-11 02:23:52.771000', 0, 38, 1);
INSERT INTO `t_news` VALUES (55, b'0', b'1', '昨天晚上在3楼洗完澡将热水卡遗失在了刷卡机上忘记拿走了，今天早上去发现不见了，希望捡到的同学可以尽快联系我，我的联系方式是qq：1345455125', '2021-07-11 02:26:54.032000', '昨天晚上在南区宿舍3楼丢失热水卡一张，联系方式是qq：1345455125', 'https://tse2-mm.cn.bing.net/th/id/OIP-C.y55gRqCYHtBnFhlqNkNdhgHaEq?w=250&h=180&c=7&o=5&pid=1.7', NULL, b'1', b'0', b'0', '昨天晚上在南区宿舍3楼丢失热水卡一张', '2021-07-11 02:26:54.032000', 5, 2, 1);

-- ----------------------------
-- Table structure for t_news_tags
-- ----------------------------
DROP TABLE IF EXISTS `t_news_tags`;
CREATE TABLE `t_news_tags`  (
  `news_id` bigint NOT NULL,
  `tags_id` bigint NOT NULL,
  INDEX `FKg8oxfb5s0a3p7sjpho5bra0kb`(`tags_id`) USING BTREE,
  INDEX `FKqqmgs2ktefbghfengv59wxjbe`(`news_id`) USING BTREE,
  CONSTRAINT `FKg8oxfb5s0a3p7sjpho5bra0kb` FOREIGN KEY (`tags_id`) REFERENCES `t_tag` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKqqmgs2ktefbghfengv59wxjbe` FOREIGN KEY (`news_id`) REFERENCES `t_news` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_news_tags
-- ----------------------------
INSERT INTO `t_news_tags` VALUES (50, 44);
INSERT INTO `t_news_tags` VALUES (49, 41);
INSERT INTO `t_news_tags` VALUES (51, 4);
INSERT INTO `t_news_tags` VALUES (52, 42);
INSERT INTO `t_news_tags` VALUES (53, 46);
INSERT INTO `t_news_tags` VALUES (54, 43);
INSERT INTO `t_news_tags` VALUES (55, 46);

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
  `id` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES (4, '南书院');
INSERT INTO `t_tag` VALUES (39, '北书院');
INSERT INTO `t_tag` VALUES (40, '图书馆');
INSERT INTO `t_tag` VALUES (41, '9栋');
INSERT INTO `t_tag` VALUES (42, '15栋');
INSERT INTO `t_tag` VALUES (43, '南湖操场');
INSERT INTO `t_tag` VALUES (44, '一食堂');
INSERT INTO `t_tag` VALUES (45, '中心食堂');
INSERT INTO `t_tag` VALUES (46, '宿舍楼');

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
  `id` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES (2, '校园卡');
INSERT INTO `t_type` VALUES (36, '书籍');
INSERT INTO `t_type` VALUES (37, '生活用品');
INSERT INTO `t_type` VALUES (38, '手机');
INSERT INTO `t_type` VALUES (47, '证件');
INSERT INTO `t_type` VALUES (48, '水杯');
INSERT INTO `t_type` VALUES (59, '电脑');
INSERT INTO `t_type` VALUES (60, '耳机');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` int NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '/images/avatar.jpg', '2021-07-10 18:18:46.000000', NULL, NULL, 'f067bec7fc746948c8e3ff4abb7a63ab', NULL, NULL, 'admin');

SET FOREIGN_KEY_CHECKS = 1;
