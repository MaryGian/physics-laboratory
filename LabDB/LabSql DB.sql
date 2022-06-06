-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: laboratory
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `announcements`
--

DROP TABLE IF EXISTS `announcements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `announcements` (
  `an_id` int NOT NULL AUTO_INCREMENT,
  `member_id` int DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `detail` varchar(300) DEFAULT NULL,
  `date_of_announcement` date DEFAULT NULL,
  PRIMARY KEY (`an_id`),
  KEY `cources_announc` (`course_id`),
  KEY `members_announc` (`member_id`),
  CONSTRAINT `cources_announc` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `members_announc` FOREIGN KEY (`member_id`) REFERENCES `lab_members` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcements`
--

LOCK TABLES `announcements` WRITE;
/*!40000 ALTER TABLE `announcements` DISABLE KEYS */;
INSERT INTO `announcements` (`an_id`, `member_id`, `course_id`, `title`, `detail`, `date_of_announcement`) VALUES (1,1,NULL,'Συνταξιοδότηση του καθηγητή κύριου Μαστιχιάδη','Θα πραγματοποιηθεί το Σάββατο στις 6 η ώρα το απόγευμα δεξίωση αποχαιρετισμού του καθηγητη κύριου Μαστιχιάδη στην αίθουσα δεξιώσεων του εργαστηρίου','2022-02-23'),(2,NULL,1,'Αλλαγή διάλεξης','Το μάθημα της παρασκευής αναβάλλεται για την επόμενη εβδομάδα όπου θα πραγματοποιηθεί Τρίτη στις 4 η ωρα','2022-02-20'),(3,2,3,'Αλλαγή υπεύθυνου μαθήματος','Την διδασκαλία του μαθήματος αναλαμβάνει ο καθηγητής κύριος Αποστολάτος και οι ώρες θα ανακοινωθούν εντός των επόμενων ημερών','2022-02-19'),(4,NULL,NULL,'Δήλωση μαθημάτων','Ξεκίνησαν οι δηλώσεις μαθημάτων. Η προθεσμία δηλώσεων λήγει στις 15 φλεβάρη','2022-02-15'),(5,1,4,'Ακύρωση μαθήματος','Το μάθημα της δευτέρας δεν θα πραγματοποιηθεί','2022-02-06'),(6,NULL,NULL,'just testing','','2022-02-03'),(7,NULL,NULL,'Σεμινάριο Κβαντομηχανικής','Θα πραγματοποιηθεί Τρίτη στις 3.','2022-01-03');
/*!40000 ALTER TABLE `announcements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `co_authors`
--

DROP TABLE IF EXISTS `co_authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `co_authors` (
  `cauthor_id` int NOT NULL AUTO_INCREMENT,
  `cauthor_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cauthor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `co_authors`
--

LOCK TABLES `co_authors` WRITE;
/*!40000 ALTER TABLE `co_authors` DISABLE KEYS */;
INSERT INTO `co_authors` (`cauthor_id`, `cauthor_name`) VALUES (1,'Mckee'),(2,'Einstein'),(3,'Maxwell'),(7,'Newton');
/*!40000 ALTER TABLE `co_authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `co_write`
--

DROP TABLE IF EXISTS `co_write`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `co_write` (
  `pub_id` int DEFAULT NULL,
  `cauthor_id` int DEFAULT NULL,
  UNIQUE KEY `unique_pub_cauth` (`pub_id`,`cauthor_id`),
  KEY `cauthor_cwrite` (`cauthor_id`),
  CONSTRAINT `cauthor_cwrite` FOREIGN KEY (`cauthor_id`) REFERENCES `co_authors` (`cauthor_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pub_cwrite` FOREIGN KEY (`pub_id`) REFERENCES `publications` (`pub_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `co_write`
--

LOCK TABLES `co_write` WRITE;
/*!40000 ALTER TABLE `co_write` DISABLE KEYS */;
INSERT INTO `co_write` (`pub_id`, `cauthor_id`) VALUES (2,1),(2,7),(5,1),(6,2),(6,3);
/*!40000 ALTER TABLE `co_write` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(60) DEFAULT NULL,
  `ects` int DEFAULT NULL,
  `undergraduate` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`course_id`),
  UNIQUE KEY `unique_course` (`course_name`,`undergraduate`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` (`course_id`, `course_name`, `ects`, `undergraduate`) VALUES (1,'Αστροφυσική Πλάσματος',3,1),(2,'Παρατηρησιακή Αστροφυσική',2,0),(3,'Γενική θεωρία Σχετικότητας',3,1),(4,'Δυναμική ρευστών',1,0),(5,'Παρατηρησιακή Αστροφυσική',3,1),(6,'Στοιχειώδη Σωμάτια',3,1),(7,'Ηλεκτρονικά Κυκλώματα',3,1),(8,'Σήματα και Συστήματα',2,1),(9,'Αλληλεπίδραση Φωτονίων με την ύλη',3,0),(10,'Κβαντομηχανική',1,1),(11,'Ατομική Φυσική',3,1),(12,'Μικροηλεκτρονική',2,0),(13,'Ηλεκτρονική 2',3,1);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institutes`
--

DROP TABLE IF EXISTS `institutes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `institutes` (
  `institute_id` int NOT NULL AUTO_INCREMENT,
  `institute_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`institute_id`),
  UNIQUE KEY `uniqueInstitute` (`institute_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institutes`
--

LOCK TABLES `institutes` WRITE;
/*!40000 ALTER TABLE `institutes` DISABLE KEYS */;
INSERT INTO `institutes` (`institute_id`, `institute_name`) VALUES (4,'CERN'),(1,'MIT'),(3,'NASA'),(2,'Stanford'),(9,'ΕΚΠΑ');
/*!40000 ALTER TABLE `institutes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lab_members`
--

DROP TABLE IF EXISTS `lab_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lab_members` (
  `member_id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT '7',
  `member_name` varchar(30) DEFAULT NULL,
  `sir_name` varchar(30) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `short_cv` varchar(500) DEFAULT NULL,
  `web_page` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `email` (`email`),
  KEY `roles_members` (`role_id`),
  CONSTRAINT `roles_members` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lab_members`
--

LOCK TABLES `lab_members` WRITE;
/*!40000 ALTER TABLE `lab_members` DISABLE KEYS */;
INSERT INTO `lab_members` (`member_id`, `role_id`, `member_name`, `sir_name`, `email`, `short_cv`, `web_page`) VALUES (1,1,'Απόστολος','Μαστιχιάδης','mastich@gmail.com','Ο Απόστολος Μαστιχιάδης είναι καθηγητής του τμήματος φυσικής και υπεύθυνος του Μεταπτυχιακού προγράμματος σπουδών Αστροφυσικής.','www.mastich.com'),(2,2,'Θεοχάρης','Αποστολάτος','apostol@phys.uoa.gr','Αναπληρωτής καθηγητής του φυσικού αθήνας. Διδακτορική διατριβή στο μετανευτώνιο όριο στον τομέα της γενικής σχετικότητας','www.apostol.com'),(3,8,'Ορέστης','Κατσαρός','katsar@phys.uoa.gr',NULL,NULL),(4,7,'Μαρκέλλα','Παππαιωάνου','mark@phys.uoa.gr','Aπόφοιτος του τμήματος Φυσικής της Κρήτης και μεταπτυχιακός φοιτητής του ΠΜΣ Πυρινικής φυσικής του τμήματος Φυσικής Αθήνας. Έχει συμμετάσχει το καλοκαίρι του 2015 στο πρόγραμμα Erasmus+ στο ερευνητικό εργαστήριο του cern ','www.mark.com'),(5,6,'Δημήτρης','Τσαγκέτας','tsagketsa@phys.uoa.com',NULL,NULL);
/*!40000 ALTER TABLE `lab_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pub_category`
--

DROP TABLE IF EXISTS `pub_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pub_category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `category_name` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pub_category`
--

LOCK TABLES `pub_category` WRITE;
/*!40000 ALTER TABLE `pub_category` DISABLE KEYS */;
INSERT INTO `pub_category` (`category_id`, `category_name`) VALUES (2,'Αστροφυσική'),(1,'Ηλεκτρονική φυσική'),(3,'Πυρινική Φυσική');
/*!40000 ALTER TABLE `pub_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publications`
--

DROP TABLE IF EXISTS `publications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publications` (
  `pub_id` int NOT NULL AUTO_INCREMENT,
  `category_id` int DEFAULT NULL,
  `pub_title` varchar(150) DEFAULT NULL,
  `date_of_pub` date DEFAULT NULL,
  `publisher` varchar(150) DEFAULT NULL,
  `publish_at_conference` tinyint(1) DEFAULT '0',
  `co_author_foreign` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`pub_id`),
  KEY `category_pub` (`category_id`),
  CONSTRAINT `category_pub` FOREIGN KEY (`category_id`) REFERENCES `pub_category` (`category_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publications`
--

LOCK TABLES `publications` WRITE;
/*!40000 ALTER TABLE `publications` DISABLE KEYS */;
INSERT INTO `publications` (`pub_id`, `category_id`, `pub_title`, `date_of_pub`, `publisher`, `publish_at_conference`, `co_author_foreign`) VALUES (1,1,'“Average capacity of optical wireless communication systems over atmospheric turbulence channels','2009-05-08','Journal of Lightwave Technology',0,0),(2,2,'Electron-Positron Pair Production by Ultrarelativistic Electrons in a Soft Photon Field','2020-03-06','Astrophysical Journal',0,1),(3,1,'On the use of Wavelength and Time Diversity in Optical Wireless Communication Systems over Gamma-Gamma Turbulence Channels','2018-12-05','Journal of Optics & Laser Technology',0,0),(4,2,'Gravitational waves','2019-06-08','Big Ideas and Thoughts of Astronomy, Astrophysics and Space Science',1,1),(5,3,'Tevatron constraints on models of the Higgs boson with exotic spin and parity','2018-01-02','Physics Today',0,0),(6,3,'First search for exotic Z boson decays into photons and neutral pions in hadron collisions','2021-05-08','Physics Today',0,1),(7,2,'Electromagnetic Cascades in the Magnetosphere of a Very Young Pulsar','2021-12-12','Astrophysical Journal',0,0),(10,3,'The explosive mechanism of supernova','2017-06-02','Astrophysics Journal',1,0),(11,2,'Electrons-Pozitrons Captrure','2015-12-02','Nature',1,0);
/*!40000 ALTER TABLE `publications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publish`
--

DROP TABLE IF EXISTS `publish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publish` (
  `pub_id` int NOT NULL,
  `member_id` int NOT NULL,
  UNIQUE KEY `unique_publish` (`pub_id`,`member_id`),
  KEY `members_publish` (`member_id`),
  CONSTRAINT `members_publish` FOREIGN KEY (`member_id`) REFERENCES `lab_members` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pub_publish` FOREIGN KEY (`pub_id`) REFERENCES `publications` (`pub_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publish`
--

LOCK TABLES `publish` WRITE;
/*!40000 ALTER TABLE `publish` DISABLE KEYS */;
INSERT INTO `publish` (`pub_id`, `member_id`) VALUES (2,1),(3,1),(6,1),(7,1),(2,2),(4,2),(10,2),(5,3),(6,4),(1,5),(3,5),(11,5);
/*!40000 ALTER TABLE `publish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `research`
--

DROP TABLE IF EXISTS `research`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `research` (
  `member_id` int NOT NULL,
  `research_id` int NOT NULL,
  UNIQUE KEY `unique_research` (`member_id`,`research_id`),
  KEY `research_details_research` (`research_id`),
  CONSTRAINT `members_research` FOREIGN KEY (`member_id`) REFERENCES `lab_members` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `research_details_research` FOREIGN KEY (`research_id`) REFERENCES `research_details` (`research_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `research`
--

LOCK TABLES `research` WRITE;
/*!40000 ALTER TABLE `research` DISABLE KEYS */;
INSERT INTO `research` (`member_id`, `research_id`) VALUES (4,1),(1,2),(2,3),(3,4),(4,5),(5,6),(1,11),(3,12),(2,13),(4,13);
/*!40000 ALTER TABLE `research` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `research_details`
--

DROP TABLE IF EXISTS `research_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `research_details` (
  `research_id` int NOT NULL AUTO_INCREMENT,
  `program_name` varchar(100) DEFAULT NULL,
  `ongoing` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`research_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `research_details`
--

LOCK TABLES `research_details` WRITE;
/*!40000 ALTER TABLE `research_details` DISABLE KEYS */;
INSERT INTO `research_details` (`research_id`, `program_name`, `ongoing`) VALUES (1,'Laser Interferometer Gravitational-wave Observatory',1),(2,'Event Horizon Telescope',1),(3,'Photon Interactions with High Energy Particles',0),(4,'Fiber Optic Communication Systems',0),(5,'Optical Wireless Communication Systems',1),(6,'Direct and indirect detection of dark matter',0),(11,'Radio observation of M37',1),(12,'X-Ray observation of Milky Way Galaxy',1),(13,'Exotic Astrophysical Objects',1);
/*!40000 ALTER TABLE `research_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`role_id`, `role_name`) VALUES (2,'Αναπληρωτής Καθηγητής'),(3,'Επίκουρος Καθηγητής'),(5,'Ερευνητής'),(1,'Καθηγητής'),(4,'Λέκτορας'),(7,'Μεταπτυχιακός Φοιτητής'),(8,'Προπτυχιακός Φοιτητής'),(6,'Υποψήφιος Διδάκτορας');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach`
--

DROP TABLE IF EXISTS `teach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teach` (
  `member_id` int NOT NULL,
  `course_id` int NOT NULL,
  `hours_for_each` int DEFAULT NULL,
  PRIMARY KEY (`course_id`,`member_id`),
  KEY `members_teach` (`member_id`),
  CONSTRAINT `courses_teach` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `members_teach` FOREIGN KEY (`member_id`) REFERENCES `lab_members` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach`
--

LOCK TABLES `teach` WRITE;
/*!40000 ALTER TABLE `teach` DISABLE KEYS */;
INSERT INTO `teach` (`member_id`, `course_id`, `hours_for_each`) VALUES (1,1,2),(2,1,2),(1,2,2),(3,2,2),(2,3,3),(3,3,3),(4,4,2),(5,4,2),(3,5,3),(4,6,2),(5,6,2),(3,7,2),(4,8,2),(2,9,3);
/*!40000 ALTER TABLE `teach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `took_place`
--

DROP TABLE IF EXISTS `took_place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `took_place` (
  `research_id` int DEFAULT NULL,
  `institute_id` int DEFAULT NULL,
  UNIQUE KEY `unique_tplace` (`research_id`,`institute_id`),
  KEY `institutes_tplace` (`institute_id`),
  CONSTRAINT `institutes_tplace` FOREIGN KEY (`institute_id`) REFERENCES `institutes` (`institute_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rdetails_tplace` FOREIGN KEY (`research_id`) REFERENCES `research_details` (`research_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `took_place`
--

LOCK TABLES `took_place` WRITE;
/*!40000 ALTER TABLE `took_place` DISABLE KEYS */;
INSERT INTO `took_place` (`research_id`, `institute_id`) VALUES (1,1),(1,2),(1,3),(2,4),(3,1),(5,2),(6,1),(11,1),(12,9),(13,1);
/*!40000 ALTER TABLE `took_place` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-23 23:38:16
