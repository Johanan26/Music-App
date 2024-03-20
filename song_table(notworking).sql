CREATE DATABASE  IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `test`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `artists`
--

DROP TABLE IF EXISTS `artists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artists` (
  `artist_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `Monthly_listeners` int DEFAULT NULL,
  `Top_Songs` varchar(255) DEFAULT NULL,
  `Artist_desc` varchar(255) DEFAULT NULL,
  `Merch` enum('yes','no') DEFAULT NULL,
  PRIMARY KEY (`artist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artists`
--

LOCK TABLES `artists` WRITE;
/*!40000 ALTER TABLE `artists` DISABLE KEYS */;
INSERT INTO `artists` VALUES (1,'Juice WRLD',31,'Lucid Dreams','As of 2024 Juice WRLD is number 137 in the world.','yes'),(2,'Ice Spice',23,'Think you the shit','As of 2024 Ice Spice is number 248 in the world.','no'),(3,'21',64,'Redrum','As of 2024 21 is number 23 in the world.','no'),(4,'Travis Scott',67,'Fe!n','As of 2024 Travis Scott  is number 19 in the world.','no'),(5,'Kanye West',78,'Carnival','As of 2024 Kanye West is number 6 in the world.','yes'),(6,'Central Cee',25,'Sprinter','As of 2024 Central Cee is number 214 in the world.','yes'),(7,'Taylor Swift',104,'Cruel Summer','As of 2024 Taylor Swift is number 2 in the world.','yes'),(8,'Drake',84,'Rich Baby Daddy','As of 2024 Drake is number 5 in the world.','yes'),(9,'Olivia Rodrigo',58,'Vampire','As of 2024 Olivia Rodrigo is number 31 in the world.','no'),(10,'The Weeknd',114,'One of the girls','As of 2024 The Weeknd is number 1 in the world.','yes'),(11,'The Kid Laroi',31,'Stay','As of 2024 The Kid Laroi is number 138 in the world.','yes'),(12,'Billie Eilish',67,'What Was I Made For','As of 2024 Billie Eilish is number 21 in the world.','no'),(13,'Laufey',14,'From The Start','As of 2024 Laufey is number 330 in the world.','no'),(14,'Lana Del Ray',58,'Summertime Sadness','As of 2024 Lana Del Rey is number 32 in the world.','no'),(15,'Djo',32,'End of beginning','As of 2024 Djo is number 130 in the world.','yes'),(16,'Dj Khalid',25,'Wild Thoughts','As of 2024 Dj Khaled is number 224 in the world.','yes'),(17,'Justin Bieber',78,'Ghost','As of 2024 Justin Bieber is number 7 in the world.','no'),(18,'Frank Sinatra',17,'Somethin\' Stupid','As of 2024 Frank Sinatra is number 464 in the world.','yes'),(19,'Micheal Bublé',17,'Spicy Margarita','As of 2024 Michael Bubléis number 454 in the world.','no'),(20,'Sean paul',33,'No Lie','As of 2024 Sean Paul is number 123 in the world.','no'),(21,'Queen',52,'Don\'t Stop Me Now','As of 2024 Queen is number 45 in the world.','no');
/*!40000 ALTER TABLE `artists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `songs`
--

DROP TABLE IF EXISTS `songs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `songs` (
  `song_id` int NOT NULL AUTO_INCREMENT,
  `song_name` varchar(255) DEFAULT NULL,
  `song_length` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `song_streams` int NOT NULL,
  `explicit` enum('yes','no') DEFAULT NULL,
  `release_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `artist_id` int NOT NULL,
  PRIMARY KEY (`song_id`),
  KEY `artist_id` (`artist_id`),
  CONSTRAINT `songs_ibfk_1` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`artist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `songs`
--

LOCK TABLES `songs` WRITE;
/*!40000 ALTER TABLE `songs` DISABLE KEYS */;
/*!40000 ALTER TABLE `songs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `Date_of_birth` date DEFAULT NULL,
  `Parental_controls` enum('yes','no') DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `subscription_plan` int DEFAULT NULL,
  `followed_artists` varchar(255) DEFAULT NULL,
  `marketing` enum('yes','no') DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `Liked_playlists` varchar(255) DEFAULT NULL,
  `liked_songs` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'David Jayakumar','davidj7705','davidj@atu.ie','Ireland','male','2005-07-07','no','087-9549150',2,'1','no','2019-12-13','1','1,2,3,4'),(2,'Darragh O\'keefe','chief_keefe','keefe@atu.ie','Ireland','male','2004-01-14','no','413-635-5769',3,'3','yes','2020-11-20','2','2,5,6,7'),(3,'Seán Maloney','ikingsean','sean.maloney@atu.ie','Ireland','male','2003-03-27','no','087123',4,'2','no','2018-09-08','4','2,1,4'),(4,'Johanan Seeruthern','johanan2607','johanan@atu.ie','Mauritius','male','2005-04-26','yes','086432',1,'2,5','no','2017-08-21','3,2','3,2,6,7'),(5,'Evelyn Concannon','e_concans123','evelyn.conca@pres.ie','Ireland','female','1964-04-29','no','0346597',1,'4','yes','2024-01-31','5','1,4,7,9'),(6,'Ana de Armas','ana123','ana_armas@gmail.com','Cuba','female','1988-04-30','no','06731',2,'1,6','no','2023-05-15','6','6,9,2.1'),(7,'Kim Kardashian','kim_k','kim_kardashian@temu.com','United States','female','1980-10-21','no','15717',4,'5','yes','2022-02-28','1,6','1,6,8'),(8,'Tyrese Mumia','visixn','G00421432@atu.ie','Kenya','Male','2004-02-17','yes','085-413243',2,'1,5','yes','2009-02-16','1,5','10,4,6'),(9,'Emily Johnson','Emily_j','johnson_e@gmail.com','England','Female','2002-11-14','no','085-5356',1,'2,6','yes','2020-03-13','0','4,9,1'),(10,'Sophia Martinez','Sophia_m','Martinez_sophia@yahoo.ie','Maldives','Female','2007-05-30','yes','089-32465',4,'1,3','yes','2018-05-25','2,4','3,5,7'),(11,'Bryant Moreland','EDP445','Bryant_loves_cupcakes@yahoo.la','United States ','male','1990-12-15','yes','18960',1,'4,7','no','2024-03-19','3','1,2,3,7,8'),(12,'Lebron James','King_james','King_james@bing.ie','United States','Male','1984-12-30','yes','393467',3,'2,6','yes','2021-09-06','2','6,9,12,2'),(13,'Thomas Brady','tom_brady','tom_brady@tombrady.com','United States','Male','1977-08-03','no','73459',2,'8,9','no','2020-03-20','6,2','3,7,8,1'),(14,'Des O\'Reilly','Des123','Des@atu.ie','Ireland','Male','1969-05-30','no','3483452',1,'3,5,8,9','yes','2023-04-13','5,3','2,6'),(15,'Delilah Carter','deli_carti','delicarti@gmail.com','Scotland','Female','1999-09-16','no','555-121-444',3,'2,6','yes','2018-01-11','4,1','1,7'),(16,'Alex Turner','alex_turner','alex_turner@gmail.com','England','Male','1986-01-06','no','435-646-6533',3,'1,4,7,8','yes','2020-10-12','3,6','7'),(17,'Dalton Watson','Big_Dalto','Watson@gmail.com','Scotland','Male','2005-11-08','no','654-846-2146',4,'3,8,1','no','2019-06-23','5','1'),(18,'Felonius Gru','Gru','felonius_gru@gmail.com','Russian','Male','1965-05-25','no','253-635-1534',4,'0','no','2021-06-28','1','5,2'),(19,'Lord Farquad','Farquad','Lordy_quads@yahoo.ie','Duloc','Male','1994-04-15','yes','264-234-7563',2,'2,6','yes','2018-02-12','6','8,4,7');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-20 15:21:40
