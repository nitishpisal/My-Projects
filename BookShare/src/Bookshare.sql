-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 04, 2015 at 12:27 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `Bookshare`
--

-- --------------------------------------------------------

--
-- Table structure for table `Address`
--

CREATE TABLE IF NOT EXISTS `Address` (
`addressid` int(11) NOT NULL,
  `street` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `zip` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Address`
--

INSERT INTO `Address` (`addressid`, `street`, `city`, `state`, `zip`) VALUES
(1, 'off of DY DIRECTOR', 'Bhopal', 'MP', '462041'),
(2, 'off of DY DIRECTOR', 'Bhopal', 'MP', '462041'),
(3, '101 E San Fernando', 'San jose', 'CALIFORNIA', '95112'),
(4, '101 E San Fernando', 'San Jose', 'California', '95112');

-- --------------------------------------------------------

--
-- Table structure for table `Bids`
--

CREATE TABLE IF NOT EXISTS `Bids` (
`bidid` int(11) NOT NULL,
  `bookid` int(11) NOT NULL,
  `bidder` int(11) NOT NULL,
  `bidprice` int(11) NOT NULL,
  `accepted` char(2) NOT NULL DEFAULT 'N',
  `active` varchar(5) DEFAULT 'yes'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Bids`
--

INSERT INTO `Bids` (`bidid`, `bookid`, `bidder`, `bidprice`, `accepted`, `active`) VALUES
(1, 6, 1, 0, '\0', 'no'),
(2, 6, 1, 0, 'N', 'no'),
(3, 6, 2, 0, 'N', 'yes'),
(4, 6, 2, 14, 'Y', 'no');

-- --------------------------------------------------------

--
-- Table structure for table `Books`
--

CREATE TABLE IF NOT EXISTS `Books` (
`bookid` int(11) NOT NULL,
  `isbn` varchar(15) NOT NULL,
  `title` varchar(50) NOT NULL,
  `author` varchar(50) DEFAULT NULL,
  `publisher` varchar(50) DEFAULT NULL,
  `year` int(11) DEFAULT '2014',
  `quantity` int(11) NOT NULL DEFAULT '1',
  `price` int(11) NOT NULL,
  `bid` char(2) NOT NULL DEFAULT 'N',
  `owner` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `available` char(2) NOT NULL DEFAULT 'Y'
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Books`
--

INSERT INTO `Books` (`bookid`, `isbn`, `title`, `author`, `publisher`, `year`, `quantity`, `price`, `bid`, `owner`, `time`, `available`) VALUES
(1, '123456789', 'Headfirst Java', 'Orielly', 'Orielly', 2014, 1, 30, 'N', 1, '2015-04-24 10:41:13', 'N'),
(2, '741852963', 'Headfirst SQL', 'Orielly', 'Orielly', 2014, 1, 32, 'N', 1, '2015-04-24 10:41:56', 'Y'),
(3, '123456', 'Java Black book', 'TATA', 'TATA', 2011, 1, 32, 'Y', 1, '2015-04-26 12:08:57', '\0'),
(4, '123456', 'Java Black book', 'TATA', 'TATA', 2011, 1, 32, 'Y', 1, '2015-04-26 12:10:56', 'Y'),
(5, '123456', 'Java Black book', 'TATA', 'TATA', 2011, 3, 32, 'Y', 1, '2015-04-26 12:11:04', 'Y'),
(6, '123456', 'Java Black book', 'TATA', 'TATA', 2011, 3, 32, 'Y', 1, '2015-04-26 12:41:02', 'Y'),
(7, '123456', 'Java Black book', 'TATA', 'TATA', 2011, 3, 32, 'Y', 1, '2015-04-26 12:42:10', 'Y'),
(8, '123456', 'Java Black book', 'TATA', 'TATA', 2011, 3, 32, 'Y', 1, '2015-04-26 12:51:58', 'Y'),
(10, '753951', 'Cracking the coding interview', 'TC Mcgraw', 'BJ publications', 2014, 5, 32, 'N', 2, '2015-04-30 06:44:11', 'Y'),
(11, '7456', 'Blue book', 'BJ', 'BJ', 2014, 1, 58, 'Y', 2, '2015-05-02 02:18:13', 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `Login`
--

CREATE TABLE IF NOT EXISTS `Login` (
`userid` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Login`
--

INSERT INTO `Login` (`userid`, `username`, `password`) VALUES
(1, 'badal.jain77@gmail.com', '123456'),
(2, 'badal.jain@sjsu.edu', '123456'),
(3, 'rishikhurana@sjsu.edu', '123456'),
(4, 'rishikhurana@sjsu.edu', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `Orders`
--

CREATE TABLE IF NOT EXISTS `Orders` (
`orderid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `bookid` int(11) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT '1',
  `amount` float NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Orders`
--

INSERT INTO `Orders` (`orderid`, `userid`, `bookid`, `quantity`, `amount`, `time`) VALUES
(1, 2, 1, 2, 60, '2015-04-27 11:32:46'),
(2, 2, 1, 2, 60, '2015-04-27 11:47:45'),
(3, 2, 1, 2, 60, '2015-04-27 11:51:04'),
(4, 2, 1, 2, 60, '2015-04-27 11:52:47'),
(5, 2, 1, 2, 60, '2015-04-28 09:39:56'),
(6, 1, 10, 2, 64, '2015-05-04 09:49:10'),
(7, 1, 10, 2, 64, '2015-05-04 09:51:44'),
(8, 1, 10, 2, 64, '2015-05-04 09:52:03');

-- --------------------------------------------------------

--
-- Table structure for table `Proposals`
--

CREATE TABLE IF NOT EXISTS `Proposals` (
`proposalid` int(11) NOT NULL,
  `proposerid` int(11) NOT NULL,
  `proposalforpostid` int(11) NOT NULL,
  `proposal` varchar(150) DEFAULT NULL,
  `accepted` char(2) NOT NULL DEFAULT 'N',
  `active` varchar(5) DEFAULT 'yes'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Proposals`
--

INSERT INTO `Proposals` (`proposalid`, `proposerid`, `proposalforpostid`, `proposal`, `accepted`, `active`) VALUES
(1, 2, 1, 'hello', 'N', 'yes'),
(2, 3, 1, 'Hello new proposal', 'N', 'yes'),
(3, 3, 2, 'Hello, I can fulfill your request of two books in 48$', 'N', 'yes'),
(4, 3, 2, 'Hello, I can fulfill your request of two books in 48$', 'N', 'yes');

-- --------------------------------------------------------

--
-- Table structure for table `Requiredbooks`
--

CREATE TABLE IF NOT EXISTS `Requiredbooks` (
`postid` int(11) NOT NULL,
  `isbn` varchar(15) DEFAULT NULL,
  `title` varchar(50) NOT NULL,
  `author` varchar(50) DEFAULT NULL,
  `publisher` varchar(50) DEFAULT NULL,
  `year` int(11) DEFAULT '2014',
  `quantity` int(11) NOT NULL DEFAULT '1',
  `postuserid` int(11) NOT NULL,
  `fulfilled` char(2) NOT NULL DEFAULT 'N'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Requiredbooks`
--

INSERT INTO `Requiredbooks` (`postid`, `isbn`, `title`, `author`, `publisher`, `year`, `quantity`, `postuserid`, `fulfilled`) VALUES
(1, '123456', 'Headfirst Design Patterns', 'Orielly', 'Orielly', 2014, 2, 1, 'Y'),
(2, '741852', 'Headfirst Python', 'Orielly', 'Orielly', 2014, 2, 1, 'N'),
(3, '789654', 'Mongo DB Internals', 'Kevin Petersen', 'Jack in the Box', 2014, 1, 2, 'N');

-- --------------------------------------------------------

--
-- Table structure for table `Userdetail`
--

CREATE TABLE IF NOT EXISTS `Userdetail` (
`userid` int(11) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `phoneno` varchar(20) DEFAULT NULL,
  `dealerorindividual` varchar(20) DEFAULT NULL,
  `addressid` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Userdetail`
--

INSERT INTO `Userdetail` (`userid`, `email`, `firstname`, `lastname`, `description`, `phoneno`, `dealerorindividual`, `addressid`) VALUES
(1, 'badal.jain77@gmail.com', 'Badal', 'Jain', 'SJSU', '6693007299', 'dealer', 1),
(2, 'vikapagey@gmail.com', 'Vikas', 'Pagey', 'SJSU', '7896541236', 'dealer', 2),
(3, 'rishikhurana@sjsu.edu', 'Rishi', 'Khurana', 'I am Individual', NULL, 'Individual', 3),
(4, 'rishikhurana@sjsu.edu', 'Rishi', 'Khurana', 'I am Individual', NULL, 'Individual', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Address`
--
ALTER TABLE `Address`
 ADD PRIMARY KEY (`addressid`);

--
-- Indexes for table `Bids`
--
ALTER TABLE `Bids`
 ADD PRIMARY KEY (`bidid`), ADD KEY `bookid` (`bookid`), ADD KEY `bidder` (`bidder`);

--
-- Indexes for table `Books`
--
ALTER TABLE `Books`
 ADD PRIMARY KEY (`bookid`), ADD KEY `owner` (`owner`);

--
-- Indexes for table `Login`
--
ALTER TABLE `Login`
 ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `Orders`
--
ALTER TABLE `Orders`
 ADD PRIMARY KEY (`orderid`), ADD KEY `bookid` (`bookid`), ADD KEY `Orders_ibfk_2` (`userid`);

--
-- Indexes for table `Proposals`
--
ALTER TABLE `Proposals`
 ADD PRIMARY KEY (`proposalid`), ADD KEY `proposerid` (`proposerid`), ADD KEY `proposalforpostid` (`proposalforpostid`);

--
-- Indexes for table `Requiredbooks`
--
ALTER TABLE `Requiredbooks`
 ADD PRIMARY KEY (`postid`), ADD KEY `postuserid` (`postuserid`);

--
-- Indexes for table `Userdetail`
--
ALTER TABLE `Userdetail`
 ADD PRIMARY KEY (`userid`), ADD KEY `addressid` (`addressid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Address`
--
ALTER TABLE `Address`
MODIFY `addressid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `Bids`
--
ALTER TABLE `Bids`
MODIFY `bidid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `Books`
--
ALTER TABLE `Books`
MODIFY `bookid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `Login`
--
ALTER TABLE `Login`
MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `Orders`
--
ALTER TABLE `Orders`
MODIFY `orderid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `Proposals`
--
ALTER TABLE `Proposals`
MODIFY `proposalid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `Requiredbooks`
--
ALTER TABLE `Requiredbooks`
MODIFY `postid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `Userdetail`
--
ALTER TABLE `Userdetail`
MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `Bids`
--
ALTER TABLE `Bids`
ADD CONSTRAINT `Bids_ibfk_1` FOREIGN KEY (`bookid`) REFERENCES `Books` (`bookid`),
ADD CONSTRAINT `Bids_ibfk_2` FOREIGN KEY (`bidder`) REFERENCES `Login` (`userid`);

--
-- Constraints for table `Books`
--
ALTER TABLE `Books`
ADD CONSTRAINT `Books_ibfk_1` FOREIGN KEY (`owner`) REFERENCES `Login` (`userid`);

--
-- Constraints for table `Orders`
--
ALTER TABLE `Orders`
ADD CONSTRAINT `Orders_ibfk_1` FOREIGN KEY (`bookid`) REFERENCES `Books` (`bookid`),
ADD CONSTRAINT `Orders_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `Userdetail` (`userid`);

--
-- Constraints for table `Proposals`
--
ALTER TABLE `Proposals`
ADD CONSTRAINT `Proposals_ibfk_1` FOREIGN KEY (`proposerid`) REFERENCES `Login` (`userid`),
ADD CONSTRAINT `Proposals_ibfk_2` FOREIGN KEY (`proposalforpostid`) REFERENCES `Requiredbooks` (`postid`);

--
-- Constraints for table `Requiredbooks`
--
ALTER TABLE `Requiredbooks`
ADD CONSTRAINT `Requiredbooks_ibfk_1` FOREIGN KEY (`postuserid`) REFERENCES `Login` (`userid`);

--
-- Constraints for table `Userdetail`
--
ALTER TABLE `Userdetail`
ADD CONSTRAINT `Userdetail_ibfk_3` FOREIGN KEY (`userid`) REFERENCES `Login` (`userid`),
ADD CONSTRAINT `Userdetail_ibfk_4` FOREIGN KEY (`addressid`) REFERENCES `Address` (`addressid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
