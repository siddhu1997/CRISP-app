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
-- Table structure for table `testing_history`
--

DROP TABLE IF EXISTS `testing_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `testing_history` (
  `testing_id` int NOT NULL AUTO_INCREMENT,
  `person_id` int DEFAULT NULL,
  `hospital` varchar(10) DEFAULT NULL,
  `testing_date` date DEFAULT NULL,
  `result` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`testing_id`),
  KEY `person_id` (`person_id`),
  CONSTRAINT `testing_history_ibfk_1` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testing_history`
--

LOCK TABLES `testing_history` WRITE;
/*!40000 ALTER TABLE `testing_history` DISABLE KEYS */;
INSERT INTO `testing_history` VALUES (1,6,'VMC','2020-10-03','Positive'),(2,6,'VMC','2020-10-05','Negative'),(3,6,'VMC','2020-10-07','Negative'),(4,6,'VMC','2020-10-09','Negative'),(5,7,'VMC','2020-12-01','Positive'),(6,7,'VMC','2020-12-03','Negative'),(7,16,'VMC','2020-10-03','Positive'),(8,16,'VMC','2020-10-05','Negative'),(9,17,'VMC','2020-12-01','Positive'),(10,17,'VMC','2020-12-03','Negative'),(11,16,'VMC','2020-10-09','Negative'),(12,16,'VMC','2020-10-11','Negative'),(13,16,'VMC','2020-10-13','Negative'),(14,26,'VMC','2020-10-03','Positive'),(15,26,'VMC','2020-10-05','Negative'),(16,26,'VMC','2020-10-07','Negative'),(17,26,'VMC','2020-10-09','Negative'),(18,26,'VMC','2020-10-13','Negative'),(19,27,'VMC','2020-12-01','Positive'),(20,27,'VMC','2020-12-03','Negative'),(21,26,'VMC','2020-10-03','Positive'),(22,30,'VMC','2020-10-05','Negative'),(23,30,'VMC','2020-10-07','Negative'),(24,30,'VMC','2020-10-09','Negative'),(25,30,'VMC','2020-10-13','Negative'),(26,31,'VMC','2020-12-01','Positive'),(27,31,'VMC','2020-12-03','Negative'),(28,49,'VMC','2020-10-07','Negative'),(29,49,'VMC','2020-10-09','Negative'),(30,49,'VMC','2020-10-13','Negative'),(31,50,'VMC','2020-12-01','Positive'),(32,50,'VMC','2020-12-03','Negative'),(33,6,'L','2020-12-12','Negative'),(34,6,'L','2020-12-24','Negative'),(35,49,'VMC','2020-09-13','Positive');
/*!40000 ALTER TABLE `testing_history` ENABLE KEYS */;
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
