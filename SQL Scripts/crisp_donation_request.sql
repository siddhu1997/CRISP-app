CREATE DATABASE  IF NOT EXISTS `crisp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `crisp`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: crisp
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `donation_request`
--

DROP TABLE IF EXISTS `donation_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donation_request` (
  `req_id` int NOT NULL AUTO_INCREMENT,
  `person_id` int DEFAULT NULL,
  `donor_remarks` varchar(500) DEFAULT NULL,
  `admin_remarks` varchar(500) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`req_id`),
  KEY `person_id` (`person_id`),
  CONSTRAINT `donation_request_ibfk_1` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donation_request`
--

LOCK TABLES `donation_request` WRITE;
/*!40000 ALTER TABLE `donation_request` DISABLE KEYS */;
INSERT INTO `donation_request` VALUES (1,1,'','Not Eligible','Rejected'),(2,2,'','Not Eligible','Rejected'),(3,3,'','Not Eligible','Rejected'),(4,4,'','Not Eligible','Rejected'),(5,5,'','Not Eligible','Rejected'),(6,6,'','Pending','Pending'),(7,7,'','Not Eligible','Rejected'),(8,7,'','Not Eligible','Rejected'),(9,8,'','Not Eligible','Rejected'),(10,9,'','Not Eligible','Rejected'),(11,10,'','Not Eligible','Rejected'),(12,15,'','Not Eligible','Rejected'),(13,16,'','Pending','Pending'),(14,26,'','Pending','Pending'),(15,30,'','Not Eligible','Rejected'),(16,31,'','Not Eligible','Rejected'),(17,28,'','Not Eligible','Rejected'),(18,49,'','Not Eligible','Rejected'),(19,49,'','Not Eligible','Rejected'),(20,49,'','Not Eligible','Rejected'),(21,49,'','Not Eligible','Rejected');
/*!40000 ALTER TABLE `donation_request` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-03 13:52:01
