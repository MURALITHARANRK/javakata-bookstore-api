CREATE TABLE IF NOT EXISTS `book` (
  `book_id` INT AUTO_INCREMENT  PRIMARY KEY,
  `title` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `price` INT NOT NULL
);