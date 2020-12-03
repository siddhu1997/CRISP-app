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
-- Table structure for table `treatment_history`
--

DROP TABLE IF EXISTS `treatment_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treatment_history` (
  `person_id` int DEFAULT NULL,
  `treatment_details` varchar(200) DEFAULT NULL,
  `admission_date` date DEFAULT NULL,
  `recovered_date` date DEFAULT NULL,
  `death_date` date DEFAULT NULL,
  KEY `person_id` (`person_id`),
  CONSTRAINT `treatment_history_ibfk_1` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatment_history`
--

LOCK TABLES `treatment_history` WRITE;
/*!40000 ALTER TABLE `treatment_history` DISABLE KEYS */;
INSERT INTO `treatment_history` VALUES (1,'lorem ipsum','2020-10-03',NULL,NULL),(2,'lorem ipsum','2020-12-01',NULL,NULL),(3,'lorem ipsum','2020-10-03',NULL,NULL),(4,'lorem ipsum','2020-12-01',NULL,NULL),(5,'lorem ipsum','2020-10-03',NULL,NULL),(11,'lorem ipsum','2020-12-01',NULL,NULL),(12,'lorem ipsum','2020-10-03',NULL,NULL),(13,'lorem ipsum','2020-12-01',NULL,NULL),(14,'lorem ipsum','2020-10-03',NULL,NULL),(15,'lorem ipsum','2020-12-01',NULL,NULL),(21,'lorem ipsum','2020-10-03',NULL,NULL),(22,'lorem ipsum','2020-12-01',NULL,NULL),(23,'lorem ipsum','2020-10-03',NULL,NULL),(24,'lorem ipsum','2020-12-01',NULL,NULL),(25,'lorem ipsum','2020-10-03',NULL,NULL),(36,'lorem ipsum','2020-12-01',NULL,NULL),(37,'lorem ipsum','2020-10-03',NULL,NULL),(38,'lorem ipsum','2020-12-01',NULL,NULL),(39,'lorem ipsum','2020-10-03',NULL,NULL),(35,'lorem ipsum','2020-12-01',NULL,NULL),(41,'lorem ipsum','2020-10-03',NULL,NULL),(42,'lorem ipsum','2020-12-01',NULL,NULL),(43,'lorem ipsum','2020-10-03',NULL,NULL),(44,'lorem ipsum','2020-12-01',NULL,NULL),(45,'lorem ipsum','2020-10-03',NULL,NULL),(46,'lorem ipsum','2020-12-01',NULL,NULL),(6,'lorem ipsum','2020-10-03','2020-10-05',NULL),(7,'lorem ipsum','2020-12-01','2020-12-03',NULL),(16,'lorem ipsum','2020-10-03','2020-10-05',NULL),(17,'lorem ipsum','2020-12-01','2020-12-03',NULL),(26,'lorem ipsum','2020-10-03','2020-10-05',NULL),(27,'lorem ipsum','2020-12-01','2020-12-03',NULL),(30,'lorem ipsum','2020-10-03','2020-10-05',NULL),(31,'lorem ipsum','2020-12-01','2020-12-03',NULL),(47,'lorem ipsum','2020-10-03','2020-10-05',NULL),(48,'lorem ipsum','2020-12-01','2020-12-03',NULL),(8,'died from covid-19','2020-10-03',NULL,'2020-10-05'),(9,'died from covid-19','2020-12-01',NULL,'2020-12-03'),(10,'died from covid-19','2020-12-01',NULL,'2020-12-03'),(18,'died from covid-19','2020-10-03',NULL,'2020-10-05'),(19,'died from covid-19','2020-10-03',NULL,'2020-10-05'),(20,'died from covid-19','2020-12-01',NULL,'2020-12-03'),(28,'died from covid-19','2020-10-03',NULL,'2020-10-05'),(29,'died from covid-19','2020-12-01',NULL,'2020-12-03'),(32,'died from covid-19','2020-10-03',NULL,'2020-10-05'),(33,'died from covid-19','2020-12-01',NULL,'2020-12-03'),(34,'died from covid-19','2020-10-03',NULL,'2020-10-05'),(40,'died from covid-19','2020-12-01',NULL,'2020-12-03'),(49,'died from covid-19','2020-10-03',NULL,'2020-10-05'),(50,'died from covid-19','2020-12-01',NULL,'2020-12-03');
/*!40000 ALTER TABLE `treatment_history` ENABLE KEYS */;
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
