CREATE DATABASE brewDB;

use brewDB;

CREATE TABLE `brewlogs` (
  `logtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `brewid` int(11) NOT NULL DEFAULT '0',
  `brewstate` varchar(256) DEFAULT NULL,
  `temperature` float DEFAULT NULL,
  PRIMARY KEY (`brewid`,`logtime`),
  CONSTRAINT `brewlogs_ibfk_1` FOREIGN KEY (`brewid`) REFERENCES `brewtable` (`brewid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `brewrecipe` (
  `brewrecipe_id` int(11) NOT NULL AUTO_INCREMENT,
  `boil_temp` double DEFAULT NULL,
  `recipe_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`brewrecipe_id`),
  UNIQUE KEY `recipe_name` (`recipe_name`,`boil_temp`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `brewtable` (
  `brewid` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`brewid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE temperatureSensorData (
	'id' int(11) NOT NULL, 
	'temperature' double NOT NULL, 
	'time_stamp' datetime NOT NULL DEFAULT NOW()
);