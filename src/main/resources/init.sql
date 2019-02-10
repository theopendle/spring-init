CREATE DATABASE  IF NOT EXISTS `spring_demo`;

USE `spring_demo`;

DROP TABLE IF EXISTS `clients`;

CREATE TABLE `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

LOCK TABLES `clients` WRITE;

INSERT INTO `clients` VALUES (4,'Theo','Pendle'),(5,'Bruce','Wayne');

UNLOCK TABLES;