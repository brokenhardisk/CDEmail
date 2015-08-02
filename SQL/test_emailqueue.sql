CREATE DATABASE  IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `test`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win32 (x86)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.6.24-log

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
-- Table structure for table `emailqueue`
--

DROP TABLE IF EXISTS `emailqueue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emailqueue` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `From` varchar(45) DEFAULT NULL,
  `To` varchar(45) DEFAULT NULL,
  `Subject` varchar(45) DEFAULT NULL,
  `Body` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emailqueue`
--

LOCK TABLES `emailqueue` WRITE;
/*!40000 ALTER TABLE `emailqueue` DISABLE KEYS */;
INSERT INTO `emailqueue` VALUES (1,'abc@d.com','b@f.com','Test 1','Test 1'),(2,'ab@c.com','asd@b.com','Test 2','Test 2'),(3,'aa@c.com','bb@c.com','Test 3','Test 3'),(4,'asd@bb.com','fgh@cc.com','Test 4','Test 4'),(5,'ert@brt.com','brt@ert.com','Test 5','Test 5'),(6,'crt@monitor.com','monitor@crt.com','Test 6','Test 6'),(7,'led@tv.com','tv@led.com','Test 7','Test 7'),(8,'xbox@ms.com','ms@xbox.com','Test 8','Test 8'),(9,'ps@sony.com','sony@ps.com','Test 9','Test 9'),(10,'ps@sony.com','sony@ps.com','Test 10','Test 10'),(11,'ps@sony.com','sony@ps.com','11','11'),(12,'ps@sony.com','sony@ps.com','12','12'),(13,'ps@sony.com','sony@ps.com','13','13');
/*!40000 ALTER TABLE `emailqueue` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-02 14:34:49
