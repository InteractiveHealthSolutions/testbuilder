CREATE DATABASE  IF NOT EXISTS `test_builder` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `test_builder`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: test_builder
-- ------------------------------------------------------
-- Server version	5.6.23-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `test_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `creator_id` int(11) NOT NULL,
  `marks_obtained` int(11) DEFAULT '0',
  `last_editor_id` int(11) DEFAULT NULL,
  `checked` bit(1) DEFAULT b'0',
  `creation_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_edited_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`test_id`,`question_id`,`creator_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `FKABCA3FBEDAFDA1EF` (`test_id`),
  KEY `FKABCA3FBE576CAEEF` (`question_id`),
  CONSTRAINT `FKABCA3FBE576CAEEF` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `FKABCA3FBEDAFDA1EF` FOREIGN KEY (`test_id`) REFERENCES `test` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer_data`
--

DROP TABLE IF EXISTS `answer_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer_id` int(11) NOT NULL,
  `data` longtext,
  `_index` int(11) DEFAULT '0',
  `creation_ts` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `last_edited_ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKED678F8B9D7D392F` (`answer_id`),
  CONSTRAINT `FKED678F8B9D7D392F` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer_data`
--

LOCK TABLES `answer_data` WRITE;
/*!40000 ALTER TABLE `answer_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer_type`
--

DROP TABLE IF EXISTS `answer_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `creation_ts` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer_type`
--

LOCK TABLES `answer_type` WRITE;
/*!40000 ALTER TABLE `answer_type` DISABLE KEYS */;
INSERT INTO `answer_type` VALUES (1,'Text','text answer is required','2014-09-18 09:59:35'),(2,'Image','Image is required','2014-09-18 09:59:35'),(3,'Text with image','Accepts both type of answer','2014-09-18 09:59:35');
/*!40000 ALTER TABLE `answer_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_type`
--

DROP TABLE IF EXISTS `category_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) NOT NULL,
  `type_description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_type`
--

LOCK TABLES `category_type` WRITE;
/*!40000 ALTER TABLE `category_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formsaved`
--

DROP TABLE IF EXISTS `formsaved`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formsaved` (
  `id` int(11) NOT NULL,
  `form_id` int(11) DEFAULT NULL,
  `savedby` varchar(45) DEFAULT NULL,
  `ts` varchar(45) DEFAULT 'CURRENT_TIMESTAMP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formsaved`
--

LOCK TABLES `formsaved` WRITE;
/*!40000 ALTER TABLE `formsaved` DISABLE KEYS */;
/*!40000 ALTER TABLE `formsaved` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilege`
--

DROP TABLE IF EXISTS `privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `right` varchar(255) DEFAULT NULL,
  `description` mediumtext,
  `creation_ts` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege`
--

LOCK TABLES `privilege` WRITE;
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;
INSERT INTO `privilege` VALUES (1,'Account Management','Can make account','2014-08-29 10:30:00'),(2,'Test Maker','Can make test','2014-08-29 10:30:00'),(3,'Test Checker','Can check test','2014-08-29 10:30:00'),(4,'Test Taker','Can take test and see his result','2014-08-29 10:30:00');
/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_type_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` longtext NOT NULL,
  `max_marks` int(10) NOT NULL,
  `comments_for_test_maker` longtext,
  `creation_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_edited_ts` timestamp NULL DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `last_editor_id` int(11) DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBA823BE66CFD659C` (`question_type_id`),
  KEY `FKBA823BE67F06C9C9` (`category_id`),
  CONSTRAINT `FKBA823BE66CFD659C` FOREIGN KEY (`question_type_id`) REFERENCES `question_type` (`id`),
  CONSTRAINT `FKBA823BE67F06C9C9` FOREIGN KEY (`category_id`) REFERENCES `category_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_data`
--

DROP TABLE IF EXISTS `question_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `data` longtext NOT NULL,
  `image_data` longblob,
  `_index` int(11) DEFAULT '0',
  `creation_ts` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `last_edited_ts` datetime DEFAULT NULL,
  `iscorrect` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC28EEC63576CAEEF` (`question_id`),
  CONSTRAINT `FKC28EEC63576CAEEF` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_data`
--

LOCK TABLES `question_data` WRITE;
/*!40000 ALTER TABLE `question_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `question_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_type`
--

DROP TABLE IF EXISTS `question_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) DEFAULT NULL,
  `creation_ts` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_type`
--

LOCK TABLES `question_type` WRITE;
/*!40000 ALTER TABLE `question_type` DISABLE KEYS */;
INSERT INTO `question_type` VALUES (1,'Paragraph','2016-08-09 11:25:39'),(2,'Multiple Choice','2014-09-12 07:40:03'),(3,'Single Choice','2014-09-12 07:41:05');
/*!40000 ALTER TABLE `question_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) DEFAULT NULL,
  `description` mediumtext,
  `creation_ts` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin','full grants','2014-08-29 10:36:58'),(2,'Test maker','can make test','2014-09-09 09:09:02'),(3,'Quiz taker','can take test and view his/her result','2015-01-21 06:41:09');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_privilege`
--

DROP TABLE IF EXISTS `role_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_privilege` (
  `id` int(11) DEFAULT NULL,
  `role_id` int(11) NOT NULL,
  `privilege_id` int(11) NOT NULL,
  `creation_ts` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`,`privilege_id`),
  KEY `fk_roles_with_privilges_privileges1_idx` (`privilege_id`),
  KEY `fk_roles_with_privilges_role1_idx` (`role_id`),
  KEY `FK45FBD62881D7676F` (`role_id`),
  KEY `FK45FBD628808777C5` (`privilege_id`),
  CONSTRAINT `FK45FBD628808777C5` FOREIGN KEY (`privilege_id`) REFERENCES `privilege` (`id`),
  CONSTRAINT `FK45FBD62881D7676F` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_privilege`
--

LOCK TABLES `role_privilege` WRITE;
/*!40000 ALTER TABLE `role_privilege` DISABLE KEYS */;
INSERT INTO `role_privilege` VALUES (1,1,1,'2014-08-29 10:42:25'),(2,1,2,'2014-08-29 10:42:25'),(3,1,3,'2014-08-29 10:42:25'),(4,2,2,'2014-09-09 11:42:47'),(5,3,4,'2015-01-21 07:26:59');
/*!40000 ALTER TABLE `role_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scheme`
--

DROP TABLE IF EXISTS `scheme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scheme` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `total_questions` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scheme`
--

LOCK TABLES `scheme` WRITE;
/*!40000 ALTER TABLE `scheme` DISABLE KEYS */;
/*!40000 ALTER TABLE `scheme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scheme_category`
--

DROP TABLE IF EXISTS `scheme_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scheme_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `scheme_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `weightage` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `scheme_id_idx` (`scheme_id`),
  KEY `category_id_idx` (`category_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `category_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `scheme_id` FOREIGN KEY (`scheme_id`) REFERENCES `scheme` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scheme_category`
--

LOCK TABLES `scheme_category` WRITE;
/*!40000 ALTER TABLE `scheme_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `scheme_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `comments` longtext,
  `creator_id` int(11) unsigned NOT NULL,
  `creation_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_editor_id` int(11) DEFAULT NULL,
  `last_edited_ts` timestamp NULL DEFAULT NULL,
  `scheme_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `scheme_id` (`scheme_id`),
  CONSTRAINT `test_ibfk_1` FOREIGN KEY (`scheme_id`) REFERENCES `scheme` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_question`
--

DROP TABLE IF EXISTS `test_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `test_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `creation_ts` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_test_with_questions_question_idx` (`question_id`),
  KEY `fk_test_with_questions_test1_idx` (`test_id`),
  CONSTRAINT `fk_test_with_questions_question` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_test_with_questions_test1` FOREIGN KEY (`test_id`) REFERENCES `test` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_question`
--

LOCK TABLES `test_question` WRITE;
/*!40000 ALTER TABLE `test_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` varchar(100) NOT NULL,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role_id` int(11) NOT NULL,
  `creation_ts` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `last_edited_ts` timestamp NULL DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `last_editor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_id_UNIQUE` (`login_id`),
  KEY `FK36EBCB81D7676F` (`role_id`),
  CONSTRAINT `FK36EBCB81D7676F` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'sa123','Haris','sa',1,'2016-07-22 11:44:50','2016-07-22 11:44:50',2,2),(4,'sa333','Mr Saad','sa',2,'2014-10-09 04:25:36',NULL,2,NULL),(5,'za123','new user','sa',1,'2014-09-12 05:33:59',NULL,2,NULL),(6,'sas','Full new user','12',1,'2014-10-29 04:46:28','2014-10-29 04:47:13',2,2),(7,'sazxc','Mr Anonymous','sa',1,'2014-09-25 04:41:25',NULL,2,NULL),(9,'ali','ali bhai','sa',1,'2014-10-09 05:16:18',NULL,2,NULL),(13,'df','testing','sa',2,'2014-10-09 05:50:36',NULL,2,NULL),(14,'user1','bootstrap User','sa',2,'2014-10-28 07:09:18',NULL,2,NULL),(15,'user2','bootstrap User 2','sa',2,'2014-10-28 07:16:10',NULL,2,NULL),(16,'hgjhgj','fgjhgj','sa',1,'2014-10-29 06:32:24',NULL,2,NULL),(17,'maven','maven Testing','sa',1,'2015-01-21 06:06:23',NULL,2,NULL),(18,'student','student','sa',3,'2015-01-21 07:11:06',NULL,2,NULL),(19,'student2','stduent34','sa',3,'2015-02-19 04:48:19',NULL,2,NULL),(21,'android','User from android','sa',1,'2015-02-19 06:25:58',NULL,2,NULL),(22,'student5','stdent5','sa',3,'2015-09-10 05:03:57',NULL,2,NULL),(23,'haris1','Haris','12',3,'2016-07-11 06:21:26',NULL,2,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-29 14:46:06
