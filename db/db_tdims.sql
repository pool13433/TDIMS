-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 26, 2017 at 07:23 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_tdims`
--

-- --------------------------------------------------------

--
-- Table structure for table `config`
--

CREATE TABLE `config` (
  `con_id` int(11) NOT NULL,
  `con_code` varchar(100) NOT NULL,
  `con_name` varchar(100) NOT NULL,
  `con_value` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `config`
--

INSERT INTO `config` (`con_id`, `con_code`, `con_name`, `con_value`) VALUES
(1, 'CHARGE_TYPE', 'prepaid', 'เติมเงิน'),
(2, 'CHARGE_TYPE', 'postpaid', 'รายเดือน'),
(3, 'USAGE_TYPE', 'dummy', 'เบอร์ที่ไม่มี SIM card'),
(4, 'USAGE_TYPE', 'physical', 'เบอร์ที่มี SIM card'),
(5, 'SIM_STATUS', 'available', 'Available'),
(6, 'SIM_STATUS', 'inUsed', 'InUsed'),
(7, 'SIM_STATUS', 'lost', 'Lost'),
(8, 'SIM_STATUS', 'unavailable', 'Unavailable'),
(9, 'ENV', 'Test', 'Environment Test'),
(10, 'ENV', 'Production', 'Environment Production'),
(11, 'PROJECT_STATUS', 'on', 'เปิด'),
(12, 'PROJECT_STATUS', 'off', 'ปิด'),
(13, 'PROFILE_GENDER', 'male', 'ชาย'),
(14, 'PROFILE_GENDER', 'female', 'หญิง'),
(15, 'PROFILE_STATUS', 'MEMBER', 'MEMBER'),
(16, 'PROFILE_STATUS', 'ADMIN', 'ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `dep_id` int(11) NOT NULL,
  `dep_name` varchar(100) NOT NULL,
  `dep_desc` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`dep_id`, `dep_name`, `dep_desc`) VALUES
(1, 'Development', 'Development');

-- --------------------------------------------------------

--
-- Table structure for table `position`
--

CREATE TABLE `position` (
  `pos_id` int(11) NOT NULL,
  `pos_name` varchar(100) NOT NULL,
  `pos_desc` text NOT NULL,
  `dep_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `position`
--

INSERT INTO `position` (`pos_id`, `pos_name`, `pos_desc`, `dep_id`) VALUES
(1, 'PA', 'Programmer Analytics', 1),
(2, 'BA', 'Business Analytics', 1),
(3, 'SA', 'System Analysis', 1);

-- --------------------------------------------------------

--
-- Table structure for table `profile`
--

CREATE TABLE `profile` (
  `profile_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `position` int(11) NOT NULL,
  `create_date` date NOT NULL,
  `create_by` int(11) NOT NULL,
  `update_date` date NOT NULL,
  `update_by` int(11) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `profile`
--

INSERT INTO `profile` (`profile_id`, `username`, `password`, `fname`, `lname`, `gender`, `mobile`, `email`, `position`, `create_date`, `create_by`, `update_date`, `update_by`, `status`) VALUES
(1, 'demo', 'demo', '11111', '0', '', '', '', 0, '0000-00-00', 0, '0000-00-00', 0, 'MEM'),
(2, 'ADMIN', 'ADMIN', 'ADMIN', 'ADMIN', 'female', 'ADMIN', 'ADMIN', 3, '2017-02-26', 1, '0000-00-00', 0, 'MEMBER'),
(3, 'md5', '1bc29b36f623ba82aaf6724fd3b16718', 'md511111111', 'md5111111', 'female', 'md5', 'md5@gmail.com', 3, '2017-02-26', 1, '2017-02-27', 1, 'MEMBER');

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `proj_id` int(11) NOT NULL,
  `proj_name` varchar(100) NOT NULL,
  `proj_desc` text NOT NULL,
  `proj_status` enum('on','off') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`proj_id`, `proj_name`, `proj_desc`, `proj_status`) VALUES
(1, 'Matadore', 'Matadore', 'on'),
(2, 'Smart Confidential Call Detail', 'Smart Confidential Call Detail', 'on'),
(5, 'CDQ Web', 'CDQ Web', 'on'),
(6, 'CDR Web Internal', 'CDR Web Internal', 'on'),
(7, 'Interstellar', 'Interstellar', 'on');

-- --------------------------------------------------------

--
-- Table structure for table `sim`
--

CREATE TABLE `sim` (
  `sim_id` int(11) NOT NULL,
  `mobile_no` varchar(15) NOT NULL,
  `serial_no` varchar(50) NOT NULL,
  `imsi` varchar(100) NOT NULL,
  `charge_type` varchar(20) NOT NULL,
  `region_code` varchar(20) NOT NULL,
  `env` varchar(20) NOT NULL,
  `site` varchar(50) NOT NULL,
  `usage_type` varchar(20) NOT NULL,
  `assign_team` varchar(30) NOT NULL,
  `email_contact` varchar(50) NOT NULL,
  `project` int(11) NOT NULL,
  `valid_date` date NOT NULL,
  `expire_date` date NOT NULL,
  `remark` text NOT NULL,
  `create_date` date NOT NULL,
  `create_by` int(11) NOT NULL,
  `update_date` date NOT NULL,
  `update_by` int(11) NOT NULL,
  `sim_status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sim`
--

INSERT INTO `sim` (`sim_id`, `mobile_no`, `serial_no`, `imsi`, `charge_type`, `region_code`, `env`, `site`, `usage_type`, `assign_team`, `email_contact`, `project`, `valid_date`, `expire_date`, `remark`, `create_date`, `create_by`, `update_date`, `update_by`, `sim_status`) VALUES
(1, '08000000001', '0800000000', '0800000000', 'prepaid', '0800000000', 'Production', 'production', 'physical', 'test', 'test@ais.co.th', 1, '2017-02-26', '2017-02-27', '111', '2017-02-26', 1, '2017-02-26', 1, 'available'),
(11, '021111111', '021111111', '021111111', 'prepaid', '021111111', 'Production', '021111111', 'physical', '021111111', '021111111@ais.co.th', 5, '2017-02-22', '2017-02-28', '021111111', '2017-02-26', 1, '2017-02-26', 1, 'inUsed'),
(12, 'md5', 'md5', 'md5', 'prepaid', 'md5', 'Production', 'md5', 'physical', 'md5', 'md5@ais.co.th', 5, '2017-02-21', '2017-02-28', 'md5', '2017-02-27', 1, '2017-02-27', 1, 'available'),
(13, 'Interstellar', 'Interstellar', 'Interstellar', 'postpaid', 'Interstellar', 'Production', 'Interstellar', 'physical', 'Interstellar', 'Interstellar@gmail.com', 6, '2017-02-01', '2017-02-28', 'Interstellar', '2017-02-27', 1, '2017-02-27', 1, 'available');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `config`
--
ALTER TABLE `config`
  ADD PRIMARY KEY (`con_id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`dep_id`);

--
-- Indexes for table `position`
--
ALTER TABLE `position`
  ADD PRIMARY KEY (`pos_id`);

--
-- Indexes for table `profile`
--
ALTER TABLE `profile`
  ADD PRIMARY KEY (`profile_id`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`proj_id`);

--
-- Indexes for table `sim`
--
ALTER TABLE `sim`
  ADD PRIMARY KEY (`sim_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `config`
--
ALTER TABLE `config`
  MODIFY `con_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `dep_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `position`
--
ALTER TABLE `position`
  MODIFY `pos_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `profile`
--
ALTER TABLE `profile`
  MODIFY `profile_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `proj_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `sim`
--
ALTER TABLE `sim`
  MODIFY `sim_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
