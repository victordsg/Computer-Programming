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
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` varchar(5) NOT NULL,
  `firstName` varchar(20) DEFAULT NULL,
  `surname` varchar(20) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `address` text,
  `email` varchar(40) DEFAULT NULL,
  `totalAttendance` double DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('ch723','Christian','Henderson',209155468,'105,Parnell Street','christian@dorsetcollege.ie',NULL,'digimon'),('gg660','Grace','Hello',209155468,'105,Parnell Street','greene@dorsetcollege.ie',NULL,'Greene'),('mw700','Megan','White',209155468,'105,Parnell Street','white@dorsetcollege.ie',NULL,'agoramudane'),('pf880','Peter','Fraser',209155468,'105,Parnell Street','peter@dorsetcollege.ie',NULL,'peter'),('rh645','Ruth','Hart',209155468,'105,Parnell Street','ruth@dorsetcollege.ie',NULL,'ruth'),('rq541','Ryan','Quinn',209155468,'105,Parnell Street','quinn@dorsetcollege.ie',NULL,'quinn'),('va047','Victor','Amorim',838801130,'103, Beacon South Quarter','victordsgamorim@hotmail.com',48.786,'88145913'),('vr462','Victor','Ross',209155468,'105,Parnell Street','ross@dorsetcollege.ie',NULL,'ross');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
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
