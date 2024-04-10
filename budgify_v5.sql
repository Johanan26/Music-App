CREATE DATABASE  IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `test`;
-- MySQL dump 10.13  Distrib 8.0.36, for macos14 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `albums`
--

DROP TABLE IF EXISTS `albums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `albums` (
  `album_id` int NOT NULL,
  `album_name` varchar(45) NOT NULL,
  `artist_id` int DEFAULT NULL,
  PRIMARY KEY (`album_id`),
  KEY `artist_id` (`artist_id`),
  CONSTRAINT `albums_ibfk_1` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`artist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albums`
--

LOCK TABLES `albums` WRITE;
/*!40000 ALTER TABLE `albums` DISABLE KEYS */;
INSERT INTO `albums` VALUES (1,'Goodbye & Good Riddance',1),(2,'Don\'t Forget About Me Demos',22),(3,'Sunburn',22);
/*!40000 ALTER TABLE `albums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artists`
--

DROP TABLE IF EXISTS `artists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artists` (
  `artist_id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `Monthly_listeners` int DEFAULT NULL,
  `Top_Songs` varchar(255) DEFAULT NULL,
  `Artist_desc` varchar(255) DEFAULT NULL,
  `Merch` enum('yes','no') DEFAULT NULL,
  PRIMARY KEY (`artist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artists`
--

LOCK TABLES `artists` WRITE;
/*!40000 ALTER TABLE `artists` DISABLE KEYS */;
INSERT INTO `artists` VALUES (1,'Juice WRLD',31,'Lucid Dreams','As of 2024 Juice WRLD is number 137 in the world.','yes'),(2,'Ice Spice',23,'Think you the shit','As of 2024 Ice Spice is number 248 in the world.','no'),(3,'21 Savage',64,'Redrum','As of 2024 21 is number 23 in the world.','no'),(4,'Travis Scott',67,'Fe!n','As of 2024 Travis Scott  is number 19 in the world.','no'),(5,'Kanye West',78,'Carnival','As of 2024 Kanye West is number 6 in the world.','yes'),(6,'Central Cee',25,'Sprinter','As of 2024 Central Cee is number 214 in the world.','yes'),(7,'Taylor Swift',104,'Cruel Summer','As of 2024 Taylor Swift is number 2 in the world.','yes'),(8,'Drake',84,'Rich Baby Daddy','As of 2024 Drake is number 5 in the world.','yes'),(9,'Olivia Rodrigo',58,'Vampire','As of 2024 Olivia Rodrigo is number 31 in the world.','no'),(10,'The Weeknd',114,'One of the girls','As of 2024 The Weeknd is number 1 in the world.','yes'),(11,'The Kid Laroi',31,'Stay','As of 2024 The Kid Laroi is number 138 in the world.','yes'),(12,'Billie Eilish',67,'What Was I Made For','As of 2024 Billie Eilish is number 21 in the world.','no'),(13,'Laufey',14,'From The Start','As of 2024 Laufey is number 330 in the world.','no'),(14,'Lana Del Ray',58,'Summertime Sadness','As of 2024 Lana Del Rey is number 32 in the world.','no'),(15,'Djo',32,'End of beginning','As of 2024 Djo is number 130 in the world.','yes'),(16,'Dj Khalid',25,'Wild Thoughts','As of 2024 Dj Khaled is number 224 in the world.','yes'),(17,'Justin Bieber',78,'Ghost','As of 2024 Justin Bieber is number 7 in the world.','no'),(18,'Frank Sinatra',17,'Somethin\' Stupid','As of 2024 Frank Sinatra is number 464 in the world.','yes'),(19,'Micheal Bublé',17,'Spicy Margarita','As of 2024 Michael Bubléis number 454 in the world.','no'),(20,'Sean paul',33,'No Lie','As of 2024 Sean Paul is number 123 in the world.','no'),(21,'Queen',52,'Don\'t Stop Me Now','As of 2024 Queen is number 45 in the world.','no'),(22,'Dominic Fike',20,'Baby Doll','As of 2024 Dominic Fike is number 341 in the world.','yes'),(23,'Weezer',15,'Island In The Sun','As of 2024 Weezer is number 362 in the world.','yes');
/*!40000 ALTER TABLE `artists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feat_artist`
--

DROP TABLE IF EXISTS `feat_artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feat_artist` (
  `feat_id` int NOT NULL,
  `artist_id` int DEFAULT NULL,
  PRIMARY KEY (`feat_id`),
  KEY `artist_id_idx` (`artist_id`),
  CONSTRAINT `artist_id` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`artist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feat_artist`
--

LOCK TABLES `feat_artist` WRITE;
/*!40000 ALTER TABLE `feat_artist` DISABLE KEYS */;
INSERT INTO `feat_artist` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11),(12,12),(13,13),(14,14),(15,15),(16,16),(17,17),(18,18),(19,19),(20,20),(21,21),(22,22),(23,23);
/*!40000 ALTER TABLE `feat_artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preset_playlist`
--

DROP TABLE IF EXISTS `preset_playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preset_playlist` (
  `preset_playlist_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `playlist_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`preset_playlist_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`preset_playlist_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preset_playlist`
--

LOCK TABLES `preset_playlist` WRITE;
/*!40000 ALTER TABLE `preset_playlist` DISABLE KEYS */;
INSERT INTO `preset_playlist` VALUES (1,1,'Radio Los Santos'),(2,1,'West Coast Classics'),(3,1,'Los Santos Rock Radio'),(4,1,'Non Stop Pop FM'),(5,1,'Flylo FM');
/*!40000 ALTER TABLE `preset_playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preset_playlist_songs`
--

DROP TABLE IF EXISTS `preset_playlist_songs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preset_playlist_songs` (
  `preset_playlist_id` int NOT NULL,
  `song_id` int NOT NULL,
  PRIMARY KEY (`preset_playlist_id`,`song_id`),
  KEY `song_id` (`song_id`),
  CONSTRAINT `preset_playlist_songs_ibfk_1` FOREIGN KEY (`preset_playlist_id`) REFERENCES `preset_playlist` (`preset_playlist_id`),
  CONSTRAINT `preset_playlist_songs_ibfk_2` FOREIGN KEY (`song_id`) REFERENCES `songs` (`song_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preset_playlist_songs`
--

LOCK TABLES `preset_playlist_songs` WRITE;
/*!40000 ALTER TABLE `preset_playlist_songs` DISABLE KEYS */;
INSERT INTO `preset_playlist_songs` VALUES (4,5),(3,6),(5,6),(1,7),(2,9),(1,10),(2,10),(5,10),(3,11),(5,13),(4,15),(1,16),(3,17),(5,17),(3,19),(1,21),(1,23),(4,24),(5,25),(3,31),(2,37),(2,39),(4,39),(4,40),(2,50);
/*!40000 ALTER TABLE `preset_playlist_songs` ENABLE KEYS */;
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
  `artist_id` int NOT NULL,
  `song_streams` varchar(255) NOT NULL,
  `Song_length` varchar(45) NOT NULL,
  `explicit` enum('yes','no') DEFAULT NULL,
  `feat_id` int DEFAULT NULL,
  `album_id` int DEFAULT NULL,
  PRIMARY KEY (`song_id`),
  KEY `artist_id` (`artist_id`),
  KEY `feat_id_idx` (`feat_id`),
  KEY `album1_id_idx` (`album_id`),
  CONSTRAINT `album_id` FOREIGN KEY (`album_id`) REFERENCES `albums` (`album_id`),
  CONSTRAINT `feat_id` FOREIGN KEY (`feat_id`) REFERENCES `feat_artist` (`feat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `songs`
--

LOCK TABLES `songs` WRITE;
/*!40000 ALTER TABLE `songs` DISABLE KEYS */;
INSERT INTO `songs` VALUES (1,'End of Beginning',15,'216,665,065','2:38','no',NULL,NULL),(2,'Chateau',15,'94,501,259','4:32','no',NULL,NULL),(3,'Lucid Dreams',1,'2,480,370,970','3:59','yes',NULL,1),(4,'Robbery',1,'1,274,864,196','4:00','yes',NULL,1),(5,'Boys a Liar',2,'811,444,335','2:09','yes',NULL,NULL),(6,'Think U The Shit (Fart)',2,'53,695,614','2:21','yes',NULL,NULL),(7,'redrum',3,'253,674,708','4:30','yes',NULL,NULL),(8,'Fe!n',4,'423,500,343','3:11','yes',NULL,NULL),(9,'Touch The Sky',5,'325,983,520','3:59','yes',NULL,NULL),(10,'Gold Digger',5,'1,093,756,680','3:27','yes',NULL,NULL),(11,'Carnival',5,'220,034,644','4:24','yes',NULL,NULL),(12,'Sprinter',6,'623,302,067','3:29','yes',NULL,NULL),(13,'Doja',6,'635,374,717','1:37','yes',NULL,NULL),(14,'Paper Rings',7,'610,924,320','3:42','no',NULL,NULL),(15,'Cruel Summer',7,'1,888,829,670','2:58','no',NULL,NULL),(16,'Anti-Hero',7,'1,457,757,627','3:20','no',NULL,NULL),(17,'Rich Baby Daddy',8,'275,872,793','5:19','yes',NULL,NULL),(18,'Passion Fruit',8,'913,240,534','4:58','yes',NULL,NULL),(19,'vampire',9,'851,600,344','3:39','yes',NULL,NULL),(20,'drivers license',9,'2,099,263,425','4:02','yes',NULL,NULL),(21,'Blinding Lights',10,'4,131,140,404','3:20','no',NULL,NULL),(22,'Starboy',10,'3,117,353,254','3:50','yes',NULL,NULL),(23,'Popular',10,'671,062,906','3:35','yes',NULL,NULL),(24,'Stay (with Justin Bieber)',11,'2,994,266,610','2:21','yes',17,NULL),(25,'WITHOUT YOU',11,'908,255,194','2:41','yes',NULL,NULL),(26,'What Was I Made For?',12,'735,417,807','3:42','no',NULL,NULL),(27,'Happier Than Ever',12,'1,256,416,664','4:58','yes',NULL,NULL),(28,'ocean eyes',12,'1,324,772,821','3:20','no',NULL,NULL),(29,'From The Start',13,'281,168,221','2:49','no',NULL,NULL),(30,'Falling Behind',13,'129,933,719','2:53','no',NULL,NULL),(31,'Summertime Sadness',14,'1,322,525,805','4:25','no',NULL,NULL),(32,'Wild Thoughts',16,'1,104,678,716','3:24','yes',NULL,NULL),(33,'Ghost',17,'1,481,200,258','2:33','no',NULL,NULL),(34,'Peaches',17,'82,254,645','3:18','yes',NULL,NULL),(35,'Die For You (feat. Dominic Fike)',17,'92,293,382','3:18','no',22,NULL),(36,'Somethin\' Stupid',18,'280,896,983','2:42','no',NULL,NULL),(37,'Fly Me To The Moon',18,'619,010,806','2:27','no',NULL,NULL),(38,'My Way',18,'443,753,059','4:36','no',NULL,NULL),(39,'Baby',17,'890,883,699','3:34','no',NULL,NULL),(40,'BabyDoll',22,'344,005,750','1:37','no',NULL,2),(41,'3 Nights',22,'942,040,608','2:57','no',NULL,2),(42,'Mona Lisa',22,'131,643,903','3:06','no',NULL,3),(43,'She Wants My Money',22,'94,140,359','2:13','yes',NULL,2),(44,'Westcoast Collective',22,'59,621,436','1:46','no',NULL,2),(45,'Socks',22,'23,902,370','2:11','no',NULL,2),(46,'King of Everything',22,'42,451,895','3:13','no',NULL,NULL),(47,'Think Fast (feat. Weezer)',22,'22,879,738','3:41','no',23,3),(48,'All Girls Are The Same',1,'1,551,722,151','2:45','yes',NULL,1),(49,'Armed and Dangerous',1,'765,641,160','2:49','yes',NULL,1),(50,'Lean Wit Me',1,'849,070,363','2:55','yes',NULL,1);
/*!40000 ALTER TABLE `songs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscription`
--

DROP TABLE IF EXISTS `subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscription` (
  `subscription_id` int NOT NULL,
  `subscription_type` varchar(45) NOT NULL,
  `prices` varchar(45) NOT NULL,
  PRIMARY KEY (`subscription_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription`
--

LOCK TABLES `subscription` WRITE;
/*!40000 ALTER TABLE `subscription` DISABLE KEYS */;
INSERT INTO `subscription` VALUES (1,'Free','€0'),(2,'Student','€5.99'),(3,'Premium (Solo)','€10.99'),(4,'Premium (Duo)','€14.99'),(5,'Premium (Family)','€17.99');
/*!40000 ALTER TABLE `subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_playlist`
--

DROP TABLE IF EXISTS `user_playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_playlist` (
  `user_playlist_id` int NOT NULL AUTO_INCREMENT,
  `playlist_name` varchar(50) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`user_playlist_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_playlist_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_playlist`
--

LOCK TABLES `user_playlist` WRITE;
/*!40000 ALTER TABLE `user_playlist` DISABLE KEYS */;
INSERT INTO `user_playlist` VALUES (1,'D A V I D',20),(2,'Party Mix',4),(3,'Hot Boy Summer',3);
/*!40000 ALTER TABLE `user_playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_playlist_songs`
--

DROP TABLE IF EXISTS `user_playlist_songs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_playlist_songs` (
  `user_playlist_id` int NOT NULL,
  `song_id` int NOT NULL,
  PRIMARY KEY (`user_playlist_id`,`song_id`),
  KEY `song_id` (`song_id`),
  CONSTRAINT `user_playlist_songs_ibfk_1` FOREIGN KEY (`user_playlist_id`) REFERENCES `user_playlist` (`user_playlist_id`),
  CONSTRAINT `user_playlist_songs_ibfk_2` FOREIGN KEY (`song_id`) REFERENCES `songs` (`song_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_playlist_songs`
--

LOCK TABLES `user_playlist_songs` WRITE;
/*!40000 ALTER TABLE `user_playlist_songs` DISABLE KEYS */;
INSERT INTO `user_playlist_songs` VALUES (2,1),(3,1),(1,3),(1,14),(3,17),(3,38),(1,40),(3,40),(1,41),(1,42);
/*!40000 ALTER TABLE `user_playlist_songs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserLikes`
--

DROP TABLE IF EXISTS `UserLikes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UserLikes` (
  `user_id` int NOT NULL DEFAULT '0',
  `song_id` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`song_id`,`user_id`),
  KEY `fk_user_id` (`user_id`),
  CONSTRAINT `fk_song_id` FOREIGN KEY (`song_id`) REFERENCES `songs` (`song_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserLikes`
--

LOCK TABLES `UserLikes` WRITE;
/*!40000 ALTER TABLE `UserLikes` DISABLE KEYS */;
INSERT INTO `UserLikes` VALUES (3,1),(4,18),(11,21),(20,1),(20,23);
/*!40000 ALTER TABLE `UserLikes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL DEFAULT '0',
  `name` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(8) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `subscription_id` int DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `Date_of_birth` date DEFAULT NULL,
  `Parental_controls` enum('yes','no') DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `marketing` enum('yes','no') DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`),
  KEY `subscription_id_idx` (`subscription_id`),
  CONSTRAINT `subscription_id` FOREIGN KEY (`subscription_id`) REFERENCES `subscription` (`subscription_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin','1234','admin',1,'male','Ireland','2005-07-07','no','087-9549150','no','2019-12-13'),(2,'Darragh O\'keefe','chief_keefe',NULL,'keefe@atu.ie',1,'male','Ireland','2004-01-14','no','413-635-5769','yes','2020-11-20'),(3,'Seán Maloney','ikingsean',NULL,'sean.maloney@atu.ie',3,'male','Ireland','2003-03-27','no','087123','no','2018-09-08'),(4,'Johanan Seeruthern','johanan2607',NULL,'johanan@atu.ie',4,'male','Mauritius','2005-04-26','yes','086432','no','2017-08-21'),(5,'Evelyn Concannon','e_concans123',NULL,'evelyn.conca@pres.ie',2,'female','Ireland','1964-04-29','no','0346597','yes','2024-01-31'),(6,'Ana de Armas','ana123',NULL,'ana_armas@gmail.com',1,'female','Cuba','1988-04-30','no','06731','no','2023-05-15'),(7,'Kim Kardashian','kim_k',NULL,'kim_kardashian@temu.com',3,'female','United States','1980-10-21','no','15717','yes','2022-02-28'),(8,'Tyrese Mumia','visixn',NULL,'G00421432@atu.ie',4,'Male','Kenya','2004-02-17','yes','085-413243','yes','2009-02-16'),(9,'Emily Johnson','Emily_j',NULL,'johnson_e@gmail.com',2,'Female','England','2002-11-14','no','085-5356','yes','2020-03-13'),(10,'Sophia Martinez','Sophia_m',NULL,'Martinez_sophia@yahoo.ie',1,'Female','Maldives','2007-05-30','yes','089-32465','yes','2018-05-25'),(11,'Bryant Moreland','EDP445',NULL,'Bryant_loves_cupcakes@yahoo.la',3,'male','United States ','1990-12-15','yes','18960','no','2024-03-19'),(12,'Lebron James','King_james',NULL,'King_james@bing.ie',4,'Male','United States','1984-12-30','yes','393467','yes','2021-09-06'),(13,'Thomas Brady','tom_brady',NULL,'tom_brady@tombrady.com',3,'Male','United States','1977-08-03','no','73459','no','2020-03-20'),(14,'Des O\'Reilly','Des123',NULL,'Des@atu.ie',1,'Male','Ireland','1969-05-30','no','3483452','yes','2023-04-13'),(15,'Delilah Carter','deli_carti',NULL,'delicarti@gmail.com',1,'Female','Scotland','1999-09-16','no','555-121-444','yes','2018-01-11'),(16,'Alex Turner','alex_turner',NULL,'alex_turner@gmail.com',1,'Male','England','1986-01-06','no','435-646-6533','yes','2020-10-12'),(17,'Dalton Watson','Big_Dalto',NULL,'Watson@gmail.com',2,'Male','Scotland','2005-11-08','no','654-846-2146','no','2019-06-23'),(18,'Felonius Gru','Gru',NULL,'felonius_gru@gmail.com',3,'Male','Russian','1965-05-25','no','253-635-1534','no','2021-06-28'),(19,'Lord Farquad','Farquad',NULL,'Lordy_quads@yahoo.ie',2,'Male','Duloc','1994-04-15','yes','264-234-7563','yes','2018-02-12'),(20,'David Jayakumar','davidjacuzzi','7705','david@gmail.com',2,'Male','Ireland',NULL,NULL,NULL,NULL,NULL);
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

-- Dump completed on 2024-04-02 16:48:44
