CREATE DATABASE  IF NOT EXISTS `tiendalibrosoihana` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tiendalibrosoihana`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: tiendalibrosoihana
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `autores`
--

DROP TABLE IF EXISTS `autores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autores` (
  `idAutor` int NOT NULL AUTO_INCREMENT,
  `nombreAutor` varchar(45) DEFAULT NULL,
  `fechaAutor` date DEFAULT NULL,
  `apellidosAutor` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idAutor`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autores`
--

LOCK TABLES `autores` WRITE;
/*!40000 ALTER TABLE `autores` DISABLE KEYS */;
INSERT INTO `autores` VALUES (1,'Gustave Flaubert','1986-11-11','Federico'),(2,'Federico García Lorca	','1928-11-11','Gomez'),(3,'Gabriel García Márquez	','1967-11-11','Leor');
/*!40000 ALTER TABLE `autores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `editoriales`
--

DROP TABLE IF EXISTS `editoriales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `editoriales` (
  `ideditorial` int NOT NULL AUTO_INCREMENT,
  `nombreEditorial` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ideditorial`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `editoriales`
--

LOCK TABLES `editoriales` WRITE;
/*!40000 ALTER TABLE `editoriales` DISABLE KEYS */;
INSERT INTO `editoriales` VALUES (1,'Syas'),(2,'Mual'),(3,'huyst');
/*!40000 ALTER TABLE `editoriales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libros`
--

DROP TABLE IF EXISTS `libros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libros` (
  `idLibro` int NOT NULL AUTO_INCREMENT,
  `cantidadLibro` int DEFAULT NULL,
  `precioLibro` decimal(6,2) DEFAULT NULL,
  `nombreLibro` varchar(45) DEFAULT NULL,
  `idEditorialFK` int DEFAULT NULL,
  `idAutorFK` int DEFAULT NULL,
  PRIMARY KEY (`idLibro`),
  KEY `idEditorialFK3_idx` (`idEditorialFK`),
  KEY `idAutorFK4_idx` (`idAutorFK`),
  CONSTRAINT `idAutorFK4` FOREIGN KEY (`idAutorFK`) REFERENCES `autores` (`idAutor`),
  CONSTRAINT `idEditorialFK3` FOREIGN KEY (`idEditorialFK`) REFERENCES `editoriales` (`ideditorial`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libros`
--

LOCK TABLES `libros` WRITE;
/*!40000 ALTER TABLE `libros` DISABLE KEYS */;
INSERT INTO `libros` VALUES (1,3,2.00,'Libro Tal de cual',1,1),(2,8,5.00,'Romancero gitano	',2,3),(3,44,33.00,'YA no es feo',2,3),(4,5,3.00,'Patito Feo',3,2),(5,10,14.00,'El rey leon',2,2),(6,34,12.00,'El senyor de los anillos',1,2),(7,22,22.00,'YA no es feo2',2,2),(8,8,20.00,'El libro negro de las horas',1,2),(9,23,22.00,'Libro9.1',1,1),(10,25,7.00,'El mágico animal',1,1),(11,4,19.00,'De sangre y cenizas',1,3),(12,8,22.00,'El mentalista',3,3),(13,33,33.00,'aaa13',1,1);
/*!40000 ALTER TABLE `libros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `librosenviados`
--

DROP TABLE IF EXISTS `librosenviados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `librosenviados` (
  `idlibrosEnviados` int NOT NULL AUTO_INCREMENT,
  `cantidad` int DEFAULT NULL,
  `idLibroFK` int DEFAULT NULL,
  `idPedidoFK` int DEFAULT NULL,
  PRIMARY KEY (`idlibrosEnviados`),
  KEY `idLibroFK1_idx` (`idLibroFK`),
  KEY `idPedidoFK2_idx` (`idPedidoFK`),
  CONSTRAINT `idLibroFK1` FOREIGN KEY (`idLibroFK`) REFERENCES `libros` (`idLibro`),
  CONSTRAINT `idPedidoFK2` FOREIGN KEY (`idPedidoFK`) REFERENCES `pedidos` (`idpedidos`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `librosenviados`
--

LOCK TABLES `librosenviados` WRITE;
/*!40000 ALTER TABLE `librosenviados` DISABLE KEYS */;
INSERT INTO `librosenviados` VALUES (5,2,2,2),(6,3,1,3);
/*!40000 ALTER TABLE `librosenviados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `idpedidos` int NOT NULL AUTO_INCREMENT,
  `fechaPedido` date DEFAULT NULL,
  `haLlegadoPedido` varchar(45) DEFAULT NULL,
  `idUsuarioFK` int DEFAULT NULL,
  PRIMARY KEY (`idpedidos`),
  KEY `idUsuarioFK5_idx` (`idUsuarioFK`),
  CONSTRAINT `idUsuarioFK5` FOREIGN KEY (`idUsuarioFK`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (2,'1111-11-11','1',1),(3,'1111-11-11','1',1);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(45) DEFAULT NULL,
  `passwdUsuario` varchar(255) DEFAULT NULL,
  `tipoUsuario` tinyint DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'usuario2','2fb6c8d2f3842a5ceaa9bf320e649ff0',0),(2,'usuario1','122b738600a0f74f7c331c0ef59bc34c',1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-20 13:02:01
