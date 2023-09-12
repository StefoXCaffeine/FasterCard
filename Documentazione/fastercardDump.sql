-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: schemacard
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `carte`
--

DROP TABLE IF EXISTS `carte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carte` (
  `NumeroCarta` char(16) NOT NULL,
  `DataCreazione` date NOT NULL,
  `DataScadenza` date NOT NULL,
  `FlagBlock` tinyint NOT NULL,
  `Cvv` char(3) NOT NULL,
  `Credito` float NOT NULL,
  `IDTitolare` int NOT NULL,
  PRIMARY KEY (`NumeroCarta`),
  UNIQUE KEY `NumeroCarta_UNIQUE` (`NumeroCarta`),
  KEY `IDTitolare_idx` (`IDTitolare`),
  CONSTRAINT `IDTitolare` FOREIGN KEY (`IDTitolare`) REFERENCES `utenti` (`IDUtente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carte`
--

LOCK TABLES `carte` WRITE;
/*!40000 ALTER TABLE `carte` DISABLE KEYS */;
INSERT INTO `carte` VALUES ('5132172802606425','2023-08-02','2028-08-02',0,'796',524.87,2),('5187901864014702','2023-08-24','2028-08-24',1,'046',100,2),('5267060749663106','2023-08-23','2028-08-23',1,'184',24,3),('5299573911418168','2023-08-10','2028-08-10',1,'905',0,3),('5417150339237009','2023-08-01','2024-08-01',0,'321',2.82,3),('5423671232298038','2023-09-12','2028-09-12',1,'963',73,50);
/*!40000 ALTER TABLE `carte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimenti`
--

DROP TABLE IF EXISTS `movimenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimenti` (
  `idMovimento` int NOT NULL AUTO_INCREMENT,
  `dataMovimento` date NOT NULL,
  `importo` float NOT NULL,
  `cartaRicevente` char(16) NOT NULL,
  PRIMARY KEY (`idMovimento`),
  UNIQUE KEY `idMovimento_UNIQUE` (`idMovimento`),
  KEY `cartaRicevente_idx` (`cartaRicevente`),
  CONSTRAINT `cartaRicevente` FOREIGN KEY (`cartaRicevente`) REFERENCES `carte` (`NumeroCarta`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimenti`
--

LOCK TABLES `movimenti` WRITE;
/*!40000 ALTER TABLE `movimenti` DISABLE KEYS */;
INSERT INTO `movimenti` VALUES (1,'2023-08-10',100,'5132172802606425'),(2,'2023-08-08',67,'5132172802606425'),(3,'2023-08-11',-298,'5132172802606425'),(4,'2023-08-11',-10,'5132172802606425'),(5,'2023-08-11',20,'5132172802606425'),(6,'2023-08-11',-20,'5132172802606425'),(12,'2023-08-11',10,'5132172802606425'),(13,'2023-08-11',-25,'5132172802606425'),(14,'2023-08-11',-100,'5299573911418168'),(15,'2023-08-11',200,'5299573911418168'),(16,'2023-08-11',250,'5299573911418168'),(17,'2023-08-11',100,'5132172802606425'),(18,'2023-08-11',280,'5132172802606425'),(19,'2023-08-11',-88,'5132172802606425'),(20,'2023-08-11',-3,'5299573911418168'),(21,'2023-08-11',20,'5299573911418168'),(22,'2023-08-17',20,'5132172802606425'),(23,'2023-08-20',30,'5132172802606425'),(24,'2023-08-23',20,'5132172802606425'),(25,'2023-08-23',-15,'5132172802606425'),(26,'2023-09-08',44,'5267060749663106'),(27,'2023-09-08',0,'5267060749663106'),(28,'2023-09-08',0,'5299573911418168'),(29,'2023-09-12',-44,'5299573911418168'),(30,'2023-09-12',100.45,'5299573911418168'),(31,'2023-09-12',8000,'5299573911418168'),(32,'2023-09-12',-8500,'5299573911418168'),(33,'2023-09-12',-256.45,'5299573911418168'),(34,'2023-09-12',0,'5299573911418168'),(35,'2023-09-12',-50,'5423671232298038'),(36,'2023-09-12',23,'5423671232298038'),(37,'2023-09-12',-50,'5267060749663106'),(38,'2023-09-12',10,'5267060749663106');
/*!40000 ALTER TABLE `movimenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utenti`
--

DROP TABLE IF EXISTS `utenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utenti` (
  `IDUtente` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(45) NOT NULL,
  `Cognome` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Password` char(64) NOT NULL,
  `UserType` int NOT NULL,
  `NegBlock` tinyint DEFAULT NULL,
  PRIMARY KEY (`IDUtente`),
  UNIQUE KEY `IDUtente_UNIQUE` (`IDUtente`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utenti`
--

LOCK TABLES `utenti` WRITE;
/*!40000 ALTER TABLE `utenti` DISABLE KEYS */;
INSERT INTO `utenti` VALUES (1,'Mario','Rossi','mariorossi123@gmail.com','c7b978a979266f0896c00d4822f9d08c31bab92ce7a85126e97db5bd1979adaf',0,NULL),(2,'Giuseppe','Verdi','giuseppeverdi21@libero.it','c7b978a979266f0896c00d4822f9d08c31bab92ce7a85126e97db5bd1979adaf',1,1),(3,'Stefano','Parrini','stefano.parrini22@gmail.com','c7b978a979266f0896c00d4822f9d08c31bab92ce7a85126e97db5bd1979adaf',2,NULL),(49,'Mario','Mario','mario@gmail.com','da7c32c2649c05f41b4bf10291b0aa16956daba8c5bad0b7787207c3059d4830',1,1),(50,'Mario','Rossi','mario2@gmail.com','da7c32c2649c05f41b4bf10291b0aa16956daba8c5bad0b7787207c3059d4830',2,NULL);
/*!40000 ALTER TABLE `utenti` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-12 14:59:12
