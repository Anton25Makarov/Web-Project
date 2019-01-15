CREATE DATABASE  IF NOT EXISTS `library`;
USE `library`;

SET NAMES utf8 ;


CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `year` int(11) DEFAULT NULL,
  `count` int(11) NOT NULL,
  `book_author_id` int(11) NOT NULL,
  `genre_catalog_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_book_book_author1_idx` (`book_author_id`),
  KEY `fk_book_genre_catalog1_idx` (`genre_catalog_id`),
  CONSTRAINT `fk_book_book_author1` FOREIGN KEY (`book_author_id`) REFERENCES `book_author` (`id`),
  CONSTRAINT `fk_book_genre_catalog1` FOREIGN KEY (`genre_catalog_id`) REFERENCES `genre_catalog` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `book_author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `is_admin` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `genre_catalog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `genre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_in_reading_room` bit(1) DEFAULT NULL,
  `taking_date` datetime DEFAULT NULL,
  `return_date` datetime DEFAULT NULL,
  `book_id` int(11) NOT NULL,
  `reader_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_book1_idx1` (`book_id`),
  KEY `fk_order_reader1_idx` (`reader_id`),
  CONSTRAINT `fk_order_book1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `fk_order_reader1` FOREIGN KEY (`reader_id`) REFERENCES `reader` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `reader` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `telephone` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
