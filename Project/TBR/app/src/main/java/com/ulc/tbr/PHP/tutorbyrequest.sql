-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 14, 2021 at 03:32 PM
-- Server version: 5.7.31
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tutorbyrequest`
--

-- --------------------------------------------------------

--
-- Table structure for table `courses_table`
--

DROP TABLE IF EXISTS `courses_table`;
CREATE TABLE IF NOT EXISTS `courses_table` (
  `subject` varchar(50) NOT NULL,
  `course` varchar(50) NOT NULL,
  `course_num` int(3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `courses_table`
--

INSERT INTO `courses_table` (`subject`, `course`, `course_num`) VALUES
('Computer Science', 'Programming 1', 200),
('Computer Science', 'Programming 2', 300),
('Computer Science', 'Programming 3', 400),
('Mathematics', 'Linear Algebra', 341),
('Computer Science', 'Programming 1', 200),
('Computer Science', 'Programming 2', 300),
('Computer Science', 'Programming 3', 400),
('Mathematics', 'Linear Algebra', 341),
('Mathematics', 'Calculus and Analytic Geometry I', 221),
('Mathematics', 'Calculus and Analytic Geometry II', 222),
('Electrical and Computer Engineering', 'Signals, Information, and Computation', 203),
('Electrical and Computer Engineering', 'Data Science and Engineering', 204),
('Electrical and Computer Engineering', 'Intoductory Experience in Electrical Engineering', 210);

-- --------------------------------------------------------

--
-- Table structure for table `sessions_table`
--

DROP TABLE IF EXISTS `sessions_table`;
CREATE TABLE IF NOT EXISTS `sessions_table` (
  `student_id` varchar(30) NOT NULL,
  `tutor_id` varchar(30) NOT NULL,
  `date` varchar(10) NOT NULL,
  `time` varchar(10) NOT NULL,
  `subject` varchar(50) NOT NULL,
  `course_number` int(3) NOT NULL,
  `location` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `session_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`student_id`,`tutor_id`,`date`,`time`),
  UNIQUE KEY `session_id` (`session_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sessions_table`
--

INSERT INTO `sessions_table` (`student_id`, `tutor_id`, `date`, `time`, `subject`, `course_number`, `location`, `description`, `session_id`) VALUES
('0000000000', '1111111111', '03/28/2021', '09:00', 'Mathematics', 341, 'Liberry', 'Do.  The.  Math.', 1),
('0000000000', '1111111112', '03/29/2021', '09:30', 'Computer Science', 400, 'Memorial Union', 'Red black trees', 2),
('0000000000', '1111111114', '04/20/2021', '10:30', 'Data Science and Engineering', 204, 'Coffee Shop', 'Wo yao cafe.', 3);

-- --------------------------------------------------------

--
-- Table structure for table `tutor_availability_table`
--

DROP TABLE IF EXISTS `tutor_availability_table`;
CREATE TABLE IF NOT EXISTS `tutor_availability_table` (
  `tutor_id` varchar(10) NOT NULL,
  `date` varchar(10) NOT NULL,
  `time` varchar(10) NOT NULL,
  `booked` varchar(5) NOT NULL,
  PRIMARY KEY (`tutor_id`,`date`,`time`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tutor_availability_table`
--

INSERT INTO `tutor_availability_table` (`tutor_id`, `date`, `time`, `booked`) VALUES
('1111111112', '01/26/2021', '04:00', 'FALSE'),
('1111111111', '01/26/2021', '09:30', 'FALSE'),
('1111111111', '01/26/2021', '10:00', 'FALSE'),
('1111111111', '01/26/2021', '10:30', 'FALSE'),
('1111111111', '02/03/2021', '09:00', 'FALSE'),
('1111111111', '02/03/2021', '09:30', 'FALSE'),
('1111111111', '02/03/2021', '10:00', 'FALSE'),
('1111111111', '02/03/2021', '10:30', 'FALSE'),
('1111111111', '02/08/2021', '09:00', 'FALSE'),
('1111111111', '02/08/2021', '09:30', 'FALSE'),
('1111111111', '02/08/2021', '10:00', 'FALSE'),
('1111111111', '02/08/2021', '10:30', 'FALSE'),
('1111111111', '02/16/2021', '09:00', 'FALSE'),
('1111111111', '02/16/2021', '09:30', 'FALSE'),
('1111111111', '02/16/2021', '10:00', 'FALSE'),
('1111111111', '02/16/2021', '10:30', 'FALSE'),
('1111111111', '02/25/2021', '09:00', 'FALSE'),
('1111111111', '02/25/2021', '09:30', 'FALSE'),
('1111111111', '02/25/2021', '10:00', 'FALSE'),
('1111111111', '02/25/2021', '10:30', 'FALSE'),
('1111111111', '03/10/2021', '09:00', 'FALSE'),
('1111111111', '03/10/2021', '09:30', 'FALSE'),
('1111111111', '03/10/2021', '10:00', 'FALSE'),
('1111111111', '03/10/2021', '10:30', 'FALSE'),
('1111111111', '03/17/2021', '09:00', 'FALSE'),
('1111111111', '03/17/2021', '09:30', 'FALSE'),
('1111111111', '03/17/2021', '10:00', 'FALSE'),
('1111111111', '03/17/2021', '10:30', 'FALSE'),
('1111111111', '03/25/2021', '09:00', 'FALSE'),
('1111111111', '03/25/2021', '09:30', 'FALSE'),
('1111111111', '03/25/2021', '10:00', 'FALSE'),
('1111111111', '03/25/2021', '10:30', 'FALSE'),
('1111111111', '03/28/2021', '09:00', 'FALSE'),
('1111111111', '03/28/2021', '09:30', 'FALSE'),
('1111111111', '03/28/2021', '10:00', 'FALSE'),
('1111111111', '03/28/2021', '10:30', 'FALSE'),
('1111111111', '04/05/2021', '09:00', 'FALSE'),
('1111111111', '04/05/2021', '09:30', 'FALSE'),
('1111111111', '04/05/2021', '10:00', 'FALSE'),
('1111111111', '04/05/2021', '10:30', 'FALSE'),
('1111111111', '04/12/2021', '09:00', 'FALSE'),
('1111111111', '04/12/2021', '09:30', 'FALSE'),
('1111111111', '04/12/2021', '10:00', 'FALSE'),
('1111111111', '04/12/2021', '10:30', 'FALSE'),
('1111111111', '04/20/2021', '09:00', 'FALSE'),
('1111111111', '04/20/2021', '09:30', 'FALSE'),
('1111111111', '04/20/2021', '10:00', 'FALSE'),
('1111111111', '04/20/2021', '10:30', 'FALSE'),
('1111111111', '04/27/2021', '09:00', 'FALSE'),
('1111111111', '04/27/2021', '09:30', 'FALSE'),
('1111111111', '04/27/2021', '10:00', 'FALSE'),
('1111111111', '04/27/2021', '10:30', 'FALSE'),
('1111111111', '05/03/2021', '09:00', 'FALSE'),
('1111111111', '05/03/2021', '09:30', 'FALSE'),
('1111111111', '05/03/2021', '10:00', 'FALSE'),
('1111111111', '05/03/2021', '10:30', 'FALSE'),
('1111111112', '01/26/2021', '09:00', 'FALSE'),
('1111111112', '01/26/2021', '09:30', 'FALSE'),
('1111111112', '01/26/2021', '10:00', 'FALSE'),
('1111111112', '01/26/2021', '10:30', 'FALSE'),
('1111111112', '02/03/2021', '09:00', 'FALSE'),
('1111111112', '02/03/2021', '09:30', 'FALSE'),
('1111111112', '02/03/2021', '10:00', 'FALSE'),
('1111111112', '02/03/2021', '10:30', 'FALSE'),
('1111111112', '02/08/2021', '09:00', 'FALSE'),
('1111111112', '02/08/2021', '09:30', 'FALSE'),
('1111111112', '02/08/2021', '10:00', 'FALSE'),
('1111111112', '02/08/2021', '10:30', 'FALSE'),
('1111111112', '02/16/2021', '09:00', 'FALSE'),
('1111111112', '02/16/2021', '09:30', 'FALSE'),
('1111111112', '02/16/2021', '10:00', 'FALSE'),
('1111111112', '02/16/2021', '10:30', 'FALSE'),
('1111111112', '02/25/2021', '09:00', 'FALSE'),
('1111111112', '02/25/2021', '09:30', 'FALSE'),
('1111111112', '02/25/2021', '10:00', 'FALSE'),
('1111111112', '02/25/2021', '10:30', 'FALSE'),
('1111111112', '03/10/2021', '09:00', 'FALSE'),
('1111111112', '03/10/2021', '09:30', 'FALSE'),
('1111111112', '03/10/2021', '10:00', 'FALSE'),
('1111111112', '03/10/2021', '10:30', 'FALSE'),
('1111111112', '03/17/2021', '09:00', 'FALSE'),
('1111111112', '03/17/2021', '09:30', 'FALSE'),
('1111111112', '03/17/2021', '10:00', 'FALSE'),
('1111111112', '03/17/2021', '10:30', 'FALSE'),
('1111111112', '03/25/2021', '09:00', 'FALSE'),
('1111111112', '03/25/2021', '09:30', 'FALSE'),
('1111111112', '03/25/2021', '10:00', 'FALSE'),
('1111111112', '03/25/2021', '10:30', 'FALSE'),
('1111111112', '03/28/2021', '09:00', 'FALSE'),
('1111111112', '03/28/2021', '09:30', 'FALSE'),
('1111111112', '03/28/2021', '10:00', 'FALSE'),
('1111111112', '03/28/2021', '10:30', 'FALSE'),
('1111111112', '04/05/2021', '09:00', 'FALSE'),
('1111111112', '04/05/2021', '09:30', 'FALSE'),
('1111111112', '04/05/2021', '10:00', 'FALSE'),
('1111111112', '04/05/2021', '10:30', 'FALSE'),
('1111111112', '04/12/2021', '09:00', 'FALSE'),
('1111111112', '04/12/2021', '09:30', 'FALSE'),
('1111111112', '04/12/2021', '10:00', 'FALSE'),
('1111111112', '04/12/2021', '10:30', 'FALSE'),
('1111111112', '04/20/2021', '09:00', 'FALSE'),
('1111111112', '04/20/2021', '09:30', 'FALSE'),
('1111111112', '04/20/2021', '10:00', 'FALSE'),
('1111111112', '04/20/2021', '10:30', 'FALSE'),
('1111111112', '04/27/2021', '09:00', 'FALSE'),
('1111111112', '04/27/2021', '09:30', 'FALSE'),
('1111111112', '04/27/2021', '10:00', 'FALSE'),
('1111111112', '04/27/2021', '10:30', 'FALSE'),
('1111111112', '05/03/2021', '09:00', 'FALSE'),
('1111111112', '05/03/2021', '09:30', 'FALSE'),
('1111111112', '05/03/2021', '10:00', 'FALSE'),
('1111111112', '05/03/2021', '10:30', 'FALSE');

-- --------------------------------------------------------

--
-- Table structure for table `tutor_courses_table`
--

DROP TABLE IF EXISTS `tutor_courses_table`;
CREATE TABLE IF NOT EXISTS `tutor_courses_table` (
  `tutor_id` varchar(10) NOT NULL,
  `subject` varchar(50) NOT NULL,
  `course_num` int(3) NOT NULL,
  PRIMARY KEY (`tutor_id`,`subject`,`course_num`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tutor_courses_table`
--

INSERT INTO `tutor_courses_table` (`tutor_id`, `subject`, `course_num`) VALUES
('1111111111', 'Computer Science', 200),
('1111111111', 'Computer Science', 300),
('1111111111', 'Computer Science', 400),
('1111111111', 'Mathematics', 221),
('1111111112', 'Electrical and Computer Engineering', 203),
('1111111112', 'Electrical and Computer Engineering', 204),
('1111111112', 'Electrical and Computer Engineering', 210),
('1111111112', 'Mathematics', 222);

-- --------------------------------------------------------

--
-- Table structure for table `users_table`
--

DROP TABLE IF EXISTS `users_table`;
CREATE TABLE IF NOT EXISTS `users_table` (
  `student_id` varchar(10) NOT NULL,
  `net_id` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `email` varchar(40) NOT NULL,
  `tutor` varchar(5) NOT NULL,
  `tutee` varchar(5) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users_table`
--

INSERT INTO `users_table` (`student_id`, `net_id`, `password`, `name`, `email`, `tutor`, `tutee`) VALUES
('0000000000', 'student', 'student', 'Stu Dent', 'student@wisc.edu', 'false', 'true'),
('1111111111', 'tutor1', 'tutor1', 'Super Smart', 'tutor1@wisc.edu', 'true', 'false'),
('1111111112', 'tutor2', 'tutor2', 'Also Smart', 'tutor2@wisc.edu', 'true', 'false'),
('1111111113', 'tutor3', 'tutor3', 'Kindof Smart', 'tutor3@wisc.edu', 'true', 'false'),
('1111111114', 'tutor4', 'tutor4', 'Notso Smart', 'tutor4@wisc.edu', 'true', 'false'),
('0000011111', 'stutor', 'stutor', 'Student Tutor', 'stutor@wisc.edu', 'true', 'true');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
