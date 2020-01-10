-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 10, 2020 at 05:39 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `movies`
--

-- --------------------------------------------------------

--
-- Table structure for table `genres`
--

CREATE TABLE `genres` (
  `gen_id` int(11) NOT NULL,
  `gen_title` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `genres`
--

INSERT INTO `genres` (`gen_id`, `gen_title`) VALUES
(1, 'Action'),
(2, 'Sci-Fi'),
(3, 'Thriller'),
(4, 'Comedy'),
(5, 'Drama'),
(6, 'Crime'),
(7, 'Romance'),
(8, 'Biography'),
(9, 'Adventure'),
(10, 'Family');

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE `movie` (
  `mov_Id` int(11) NOT NULL,
  `mov_title` varchar(50) NOT NULL,
  `mov_year` int(11) NOT NULL,
  `mov_lang` varchar(30) NOT NULL,
  `mov_dura` int(11) NOT NULL,
  `mov_img` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`mov_Id`, `mov_title`, `mov_year`, `mov_lang`, `mov_dura`, `mov_img`) VALUES
(4, 'Avengers', 2018, 'English', 162, 'up/infinitywar.jpg'),
(5, 'Cats', 2019, 'English', 110, 'up/cats.jpeg'),
(6, 'Knives Out', 2019, 'English,Spanish', 130, 'up/knives-out.jpg'),
(7, 'Little Women', 2019, 'English,French', 135, 'up/littlewomen.jpg'),
(8, 'The Irishman', 2019, 'English,Italian,Latin,Spanish', 209, 'up/irishman.jpg'),
(9, 'Joker', 2019, 'English', 122, 'up/joker.jpg'),
(10, 'Jumanji: The Next Level', 2019, 'English', 123, 'up/jumanjinextlevel.jpg'),
(11, 'Avengers: Endgame', 2019, 'English', 181, 'up/avengers.jpg'),
(12, 'Jumanji', 2017, 'English', 119, 'up/jumanji.jpg'),
(13, 'Captain America: The First Avenger', 2011, 'English', 124, 'up/Captainamerica.jpg'),
(14, '6 Underground', 2019, 'English,Turkmen', 128, 'up/6Underground.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `movie_genres`
--

CREATE TABLE `movie_genres` (
  `mov_Id` int(11) NOT NULL,
  `gen_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `movie_genres`
--

INSERT INTO `movie_genres` (`mov_Id`, `gen_id`) VALUES
(4, 1),
(4, 2),
(5, 4),
(5, 5),
(5, 10),
(6, 4),
(6, 6),
(6, 5),
(7, 5),
(7, 7),
(8, 8),
(8, 6),
(8, 5),
(9, 6),
(9, 5),
(9, 3),
(10, 1),
(10, 9),
(10, 4),
(11, 1),
(11, 9),
(11, 5),
(12, 1),
(12, 9),
(12, 4),
(13, 1),
(13, 9),
(13, 2),
(14, 1),
(14, 9),
(14, 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `genres`
--
ALTER TABLE `genres`
  ADD PRIMARY KEY (`gen_id`);

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`mov_Id`);

--
-- Indexes for table `movie_genres`
--
ALTER TABLE `movie_genres`
  ADD KEY `fk_gen_id` (`gen_id`),
  ADD KEY `fk_mov_id` (`mov_Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `genres`
--
ALTER TABLE `genres`
  MODIFY `gen_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `movie`
--
ALTER TABLE `movie`
  MODIFY `mov_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `movie_genres`
--
ALTER TABLE `movie_genres`
  ADD CONSTRAINT `fk_gen_id` FOREIGN KEY (`gen_id`) REFERENCES `genres` (`gen_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_mov_id` FOREIGN KEY (`mov_Id`) REFERENCES `movie` (`mov_Id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
