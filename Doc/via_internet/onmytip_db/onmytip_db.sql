/*
SQLyog Enterprise - MySQL GUI v7.02 
MySQL - 5.5.5-10.1.10-MariaDB : Database - onmytip_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `adminlogin` */

CREATE TABLE `adminlogin` (
  `adminId` int(255) NOT NULL AUTO_INCREMENT,
  `userId` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `adminlogin` */

insert  into `adminlogin`(`adminId`,`userId`,`firstName`,`lastName`,`email`,`password`) values (1,'admin1401','anshul',NULL,'anshulv1401@gmail.com','password');

/*Table structure for table `device_list` */

CREATE TABLE `device_list` (
  `deviceId` int(255) NOT NULL AUTO_INCREMENT,
  `userId` varchar(255) DEFAULT NULL,
  `deviceName` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `onTime` varchar(255) DEFAULT NULL,
  `powerConsumption` varchar(255) DEFAULT NULL,
  `roomNo` varchar(255) DEFAULT NULL,
  UNIQUE KEY `id_2` (`deviceId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `device_list` */

insert  into `device_list`(`deviceId`,`userId`,`deviceName`,`status`,`onTime`,`powerConsumption`,`roomNo`) values (1,'ansh1401','Bulb','on','0','40W','1'),(2,'ansh1401','Phone charge','off','0','5W','1'),(3,'ansh1401','Fan','on','0','60W','2'),(4,'ansh1401','light1','off','0','40w','2'),(5,'ansh1401','light2','on','0','40W','2'),(6,'ansh1401','light3','off','0 ','40W','3'),(7,'ansh1401','light4','on','0','40W','3'),(8,'ansh1401','light5','off','0','40W','3');

/*Table structure for table `registration_table` */

CREATE TABLE `registration_table` (
  `registrationId` int(255) NOT NULL AUTO_INCREMENT,
  `userId` varchar(255) NOT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phoneNo` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `id` (`registrationId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `registration_table` */

insert  into `registration_table`(`registrationId`,`userId`,`firstName`,`lastName`,`email`,`phoneNo`,`address`,`password`) values (1,'ansh1401','anshul','vanawat','anshulv1401@gmail.com','7790806941','1 kh 8, sector 11, udaipur','password');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;