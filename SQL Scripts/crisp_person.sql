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
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `person_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `blood_group` varchar(5) DEFAULT NULL,
  `location` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Aby','1996-10-30','9966558877','A+ve','Bangalore','password'),(2,'Adnan','1998-11-03','8866995522','B+ve','Chennai','password'),(3,'Sooraj','1997-09-30','9988445522','O+ve','Bangalore','password'),(4,'Farhan','1999-07-20','9744112255','AB+ve','Chennai','password'),(5,'Sana','2000-06-10','8778899665','A-ve','Kochi','password'),(6,'Jiya','2001-05-30','7755663322','B-ve','Delhi','password'),(7,'Zara','1996-11-30','9988663322','O-ve','Bangalore','password'),(8,'Lekha','1986-01-10','9988332200','A+ve','Chennai','password'),(9,'Jim','1976-05-01','9966336633','AB+ve','Delhi','password'),(10,'John','1996-07-02','9977446655','B+ve','Kochi','password'),(11,'Ashwini','2016-03-03','9986523145','B-ve','Chennai','password'),(12,'Aswin','1999-10-04','9874563210','AB-ve','Bangalore','password'),(13,'Ram','2003-08-05','9630258741','A+ve','Bangalore','password'),(14,'Deva','2006-03-06','9876543210','B+ve','Chennai','password'),(15,'Sam','2008-04-07','8956321470','O+ve','Kochi','password'),(16,'Abel','1990-05-08','9658741230','A-ve','Delhi','password'),(17,'Aparna','1991-06-09','8796541230','B-ve','Kochi','password'),(18,'Dennis','1992-09-11','8855220011','O-ve','Bangalore','password'),(19,'Daya','1993-03-13','8754210369','AB+ve','Kochi','password'),(20,'Eby','1994-05-12','8899556633','AB-ve','Bangalore','password'),(21,'Irfan','1995-01-14','8899663320','O-ve','Bangalore','password'),(22,'Hari','1980-05-15','8956230147','O+ve','Chennai','password'),(23,'Sankar','1986-06-16','9996663332','A+ve','Kochi','password'),(24,'Rubin','1989-10-17','9998887774','A+ve','Bangalore','password'),(25,'Mahesh','1988-11-30','9907678785','B+ve','Kochi','password'),(26,'Shwetha','1956-12-30','9995551110','O+ve','Delhi','crisp@app'),(27,'Azmi','1930-12-14','9977442213','B-ve','Chennai','crisp@app'),(28,'Sriraj','2006-10-15','9966440253','A+ve','Kochi','crisp@app'),(29,'Nandhu','1940-10-24','9944221036','A+ve','Chennai','crisp@app'),(30,'Jeenia','1950-10-25','9966458712','A+ve','Bangalore','crisp@app'),(31,'Anjali','1987-10-14','9875123604','B+ve','Bangalore','crisp@app'),(32,'Sneha','2013-01-27','9012365478','O-ve','Kochi','crisp@app'),(33,'Salim','2015-02-17','9123605478','AB+ve','Kochi','crisp@app'),(34,'vipin','1997-10-05','8740125636','AB-ve','Delhi','crisp@app'),(35,'Ajeesh','1996-10-01','8654791203','A+ve','Bangalore','crisp@app'),(36,'Ramanujan','1993-06-30','9632014784','B+ve','Chennai','crisp@app'),(37,'Vasudev','2016-10-29','9514786320','A+ve','Delhi','crisp@app'),(38,'AKhi','1990-10-28','9360147852','A+ve','Chennai','crisp@app'),(39,'Joe','1996-10-27','8745963210','O-ve','Delhi','crisp@app'),(40,'Nimi','1996-10-16','8745320169','A+ve','Kochi','crisp@app'),(41,'Abisha','1996-10-25','9301288745','A+ve','Bangalore','crisp@app'),(42,'Anand','1985-10-24','7458963021','A+ve','Kochi','crisp@app'),(43,'Arun','1978-10-23','7899877899','O+ve','Kochi','crisp@app'),(44,'Arjun','1966-10-22','7788443300','A+ve','Bangalore','crisp@app'),(45,'Miya','2013-07-21','9012044442','B+ve','Chennai','crisp@app'),(46,'Laya','2018-04-21','9666852222','B-ve','Bangalore','crisp@app'),(47,'Ivana','1978-04-20','9922000336','AB+ve','Chennai','crisp@app'),(48,'Surya','2000-05-19','7854120369','A+ve','Chennai','crisp@app'),(49,'Vijay','1966-10-18','7012365489','AB-ve','Delhi','password'),(50,'Salini','2020-11-01','7963201458','A+ve','Kochi','crisp@app');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
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
