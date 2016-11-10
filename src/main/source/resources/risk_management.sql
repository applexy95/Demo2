/*
Navicat MySQL Data Transfer

Source Server         : ReggieYang
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : risk_management

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-11-07 19:46:44
*/

CREATE DATABASE IF NOT EXISTS risk_management CHARACTER SET utf8;

USE risk_management;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for followup
-- ----------------------------
DROP TABLE IF EXISTS `followup`;
CREATE TABLE `followup` (
  `followup_id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `followup_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `risk_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`followup_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of followup
-- ----------------------------
INSERT INTO `followup` VALUES ('1', 'a', '2016-06-06 00:00:00', '123');


-- ----------------------------
-- Table structure for project_developer
-- ----------------------------
DROP TABLE IF EXISTS `project_developer`;
CREATE TABLE `project_developer` (
  `project_id` int(11) DEFAULT NULL,
  `developer_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of project_developer
-- ----------------------------
INSERT INTO `project_developer` VALUES ('123', 'kai');
-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `project_id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(255) DEFAULT NULL,
  `creator_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=latin1;

CREATE TRIGGER `create_project` 
AFTER INSERT ON `project` 
    FOR EACH ROW BEGIN
        INSERT INTO `project_developer` 
        SET project_id = NEW.project_id, developer_name=NEW.creator_name;
    END;


CREATE TRIGGER `delete_project` 
AFTER DELETE ON `project` 
    FOR EACH ROW BEGIN
        DELETE FROM `project_developer` 
        WHERE project_id = OLD.project_id;
    END;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('123', 'nasame2', 'zzz');
INSERT INTO `project` VALUES ('124', 'name', 'kaimyang');
INSERT INTO `project` VALUES ('125', 'xx', 'kaimyang');
INSERT INTO `project` VALUES ('126', 'NBA project', 'kaimyang');

-- ----------------------------
-- Table structure for risk
-- ----------------------------
DROP TABLE IF EXISTS `risk`;
CREATE TABLE `risk` (
  `risk_id` int(11) NOT NULL AUTO_INCREMENT,
  `possibility` varchar(255) DEFAULT NULL,
  `impact` varchar(255) DEFAULT NULL,
  `threshold` varchar(255) DEFAULT NULL,
  `creator_name` varchar(255) DEFAULT NULL,
  `follower_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `risk_type` varchar(255) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`risk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of risk
-- ----------------------------
INSERT INTO `risk` VALUES ('2', 'asd', 'x', 'x', 'x', 'x', 'x', 'x', '23');
INSERT INTO `risk` VALUES ('3', 'nasame2', 'x', 'x', 'x', 'x', 'x', 'x', '123');
INSERT INTO `risk` VALUES ('4', '5', 'x', 'x', 'x', 'x', 'x', 'x', '123');
INSERT INTO `risk` VALUES ('5', 'nasame2', 'x', 'x', 'x', 'x', 'x', 'x', '123');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_name` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('kaimyang', 'elder Wang', '123456', 'eBay');
INSERT INTO `user` VALUES ('sasa', 'sa', 'asa', 'eBay');
INSERT INTO `user` VALUES ('ss', 's', 's', 's');


