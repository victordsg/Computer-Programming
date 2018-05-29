-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: vgcdatabase
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `studentyear`
--

DROP TABLE IF EXISTS `studentyear`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studentyear` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attend` int(11) DEFAULT NULL,
  `totalAttendance` decimal(5,2) DEFAULT NULL,
  `examResult` decimal(5,2) DEFAULT NULL,
  `studentName` varchar(5) DEFAULT NULL,
  `courseName` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `studentName` (`studentName`),
  KEY `courseName` (`courseName`),
  CONSTRAINT `studentyear_ibfk_1` FOREIGN KEY (`studentName`) REFERENCES `student` (`id`),
  CONSTRAINT `studentyear_ibfk_2` FOREIGN KEY (`courseName`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentyear`
--

LOCK TABLES `studentyear` WRITE;
/*!40000 ALTER TABLE `studentyear` DISABLE KEYS */;
INSERT INTO `studentyear` VALUES (6,10,22.50,32.00,'va047',2),(10,8,13.33,25.00,'pf880',7),(11,6,12.50,5.00,'pf880',8),(12,10,33.33,23.00,'pf880',9),(13,0,0.00,8.00,'pf880',10),(14,0,0.00,0.00,'rq541',3),(15,20,37.50,69.00,'va047',1),(16,30,62.50,80.00,'va047',2),(17,30,71.43,73.00,'va047',3),(18,35,50.00,65.00,'va047',4);
/*!40000 ALTER TABLE `studentyear` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-29 14:00:49
