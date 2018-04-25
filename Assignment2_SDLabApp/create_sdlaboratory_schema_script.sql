CREATE DATABASE `sdlaboratory` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `assignment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `description` text,
  `lab_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `assignment_laboratory_id_fk` (`lab_id`),
  CONSTRAINT `assignment_laboratory_id_fk` FOREIGN KEY (`lab_id`) REFERENCES `laboratory` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

CREATE TABLE `attendance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lab_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `present` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `lab_idx` (`lab_id`),
  KEY `student_idx` (`student_id`),
  KEY `lab_att_idx` (`lab_id`),
  KEY `student_att_idx` (`student_id`),
  CONSTRAINT `FK26jv8kfowsr7rsuyn5bd8gdcc` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`),
  CONSTRAINT `attendance_student_user_id_fk` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `lab_att` FOREIGN KEY (`lab_id`) REFERENCES `laboratory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `laboratory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(10) unsigned NOT NULL,
  `date` date NOT NULL,
  `title` varchar(128) DEFAULT NULL,
  `curricula` varchar(128) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number_UNIQUE` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

CREATE TABLE `student` (
  `id` int(11) DEFAULT NULL,
  `group_name` varchar(45) NOT NULL,
  `hobby` varchar(45) DEFAULT NULL,
  UNIQUE KEY `student_user_id_pk_3` (`id`),
  CONSTRAINT `student_user_id_fk` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `submission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `assignment_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `grade` int(11) NOT NULL,
  `date` date NOT NULL,
  `description` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_idx` (`student_id`),
  KEY `assignment_idx` (`assignment_id`),
  CONSTRAINT `FKmfr0iogbtbssiknheahbjq322` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`),
  CONSTRAINT `assignment` FOREIGN KEY (`assignment_id`) REFERENCES `assignment` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `assignment_student_student_id_fk` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(128) NOT NULL,
  `full_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `is_teacher` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

