-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 10, 2017 at 06:52 AM
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
(5, 'SIM_STATUS', 'available', 'ว่าง'),
(6, 'SIM_STATUS', 'inUsed', 'ใช้งานอยู่'),
(7, 'SIM_STATUS', 'lost', 'เสีย'),
(8, 'SIM_STATUS', 'unavailable', 'ไม่ว่าง'),
(11, 'PROJECT_STATUS', 'on', 'เปิด'),
(12, 'PROJECT_STATUS', 'off', 'ปิด'),
(13, 'PROFILE_GENDER', 'male', 'ชาย'),
(14, 'PROFILE_GENDER', 'female', 'หญิง'),
(15, 'PROFILE_STATUS', 'MEMBER', 'MEMBER'),
(16, 'PROFILE_STATUS', 'ADMIN', 'ADMIN'),
(17, 'MAIL', 'MAIL_USER', 'tdimsnoreply@gmail.com'),
(18, 'MAIL', 'MAIL_PASSWORD', 'test#2017'),
(19, 'MAIL', 'MAIL_SUBJECT', 'Automatic email for warning to return expired number'),
(20, 'MAIL', 'MAIL_CONTENT', 'Dear All.\n\nThis is automatic email for warning to return expired number.\nPlease return all ‘Number& SIM card’ to BOS-tester Team ASAP.\nLocation : ESV floor.4th.\nThank you.'),
(21, 'MAIL', 'MAIL_PORT', '587'),
(22, 'SYSTEM', 'BOS', 'BOS'),
(23, 'SYSTEM', 'IRB', 'IRB'),
(24, 'SYSTEM', 'RTBS', 'RTBS'),
(25, 'SYSTEM', 'INS', 'INS'),
(26, 'ENV', 'SIT', 'SIT'),
(27, 'ENV', 'PVT/UAT', 'PVT/UAT'),
(28, 'ENV', 'PRD', 'PRD'),
(29, 'ENV', 'PP', 'PP'),
(30, 'ENV', 'POC', 'POC');


-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `dep_id` int(11) NOT NULL,
  `dep_name` varchar(100) NOT NULL,
  `dep_desc` text NOT NULL,
  `create_date` date NOT NULL,
  `create_by` int(11) NOT NULL,
  `update_date` date NOT NULL,
  `update_by` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`dep_id`, `dep_name`, `dep_desc`, `create_date`, `create_by`, `update_date`, `update_by`) VALUES
(1, 'Development', 'Development', '0000-00-00', 0, '0000-00-00', 0);

-- --------------------------------------------------------

--
-- Table structure for table `enviroment`
--

CREATE TABLE `enviroment` (
  `env_id` int(11) NOT NULL,
  `env_code` varchar(10) NOT NULL,
  `env_desc` text NOT NULL,
  `env_site` varchar(10) NOT NULL,
  `create_date` date NOT NULL,
  `create_by` int(11) NOT NULL,
  `update_date` date NOT NULL,
  `update_by` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `enviroment`
--

INSERT INTO `enviroment` (`env_id`, `env_code`, `env_desc`, `env_site`, `create_date`, `create_by`, `update_date`, `update_by`) VALUES
(1, 'PP', 'PPPPPP', '1,2,3', '2017-03-07', 1, '2017-03-07', 1),
(2, 'SIT', 'SITSIT', '2,3', '2017-03-07', 1, '2017-03-07', 1);

-- --------------------------------------------------------

--
-- Table structure for table `knowledge`
--

CREATE TABLE `knowledge` (
  `id` int(11) DEFAULT NULL,
  `project_id` varchar(20) DEFAULT NULL,
  `server_name` varchar(50) NOT NULL,
  `path_folder` varchar(250) NOT NULL,
  `create_by` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `knowledge`
--

INSERT INTO `knowledge` (`id`, `project_id`, `server_name`, `path_folder`, `create_by`) VALUES
(1, '1', 'CDR', 'D:\\Knowledge', 1991),
(2, '2', 'CQS', 'D:\\Knowledge\\Text', 1991),
(3, '1', 'CDR', 'D:\\Knowledge', 1991),
(4, '2', 'CQS', 'D:\\Knowledge\\Text', 1991),
(5, '3', 'X-MEN', '\\\\D:\\Knowledge', 1991),
(6, '1', 'CDES', '\\\\\\\\D:\\\\Knowledge', 1992);

-- --------------------------------------------------------

--
-- Table structure for table `position`
--

CREATE TABLE `position` (
  `pos_id` int(11) NOT NULL,
  `pos_name` varchar(100) NOT NULL,
  `pos_desc` text NOT NULL,
  `dep_id` int(11) NOT NULL,
  `create_date` date NOT NULL,
  `create_by` int(1) NOT NULL,
  `update_date` date NOT NULL,
  `update_by` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `position`
--

INSERT INTO `position` (`pos_id`, `pos_name`, `pos_desc`, `dep_id`, `create_date`, `create_by`, `update_date`, `update_by`) VALUES
(1, 'PA', 'Programmer Analytics', 1, '0000-00-00', 0, '0000-00-00', 0),
(2, 'BA', 'Business Analytics', 1, '0000-00-00', 0, '0000-00-00', 0),
(3, 'SA', 'System Analysis', 1, '0000-00-00', 0, '0000-00-00', 0);

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
(6, 'CDR Web Internal', 'CDR Web Internal', 'on');

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
  `system` varchar(30) NOT NULL,
  `env` varchar(20) NOT NULL,
  `site` varchar(20) DEFAULT NULL,
  `usage_type` varchar(20) NOT NULL,
  `owner` int(11) NOT NULL,
  `team_id` int(10) DEFAULT NULL,
  `email_contact` varchar(100) DEFAULT NULL,
  `project_id` int(10) DEFAULT NULL,
  `valid_date` date DEFAULT NULL,
  `expire_date` date DEFAULT NULL,
  `remark` text,
  `create_date` date NOT NULL,
  `create_by` int(11) NOT NULL,
  `update_date` date NOT NULL,
  `update_by` int(11) DEFAULT NULL,
  `sim_status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sim`
--

INSERT INTO `sim` (`sim_id`, `mobile_no`, `serial_no`, `imsi`, `charge_type`, `region_code`, `system`, `env`, `site`, `usage_type`, `owner`, `team_id`, `email_contact`, `project_id`, `valid_date`, `expire_date`, `remark`, `create_date`, `create_by`, `update_date`, `update_by`, `sim_status`) VALUES
(1, '08000000001', '0800000000', '0800000000', 'prepaid', '0800000000', 'BOS', 'BOS', 'Site1', 'physical', 1, 0, '', 0, NULL, NULL, '', '2017-02-26', 1, '2017-03-24', NULL, 'Available'),
(11, '021111111', '021111111', '021111111', 'prepaid', '021111111', 'IRB', 'INS', 'Site2', 'physical', 1, 3, 'IRB_Test@ais.postbox.in.th; ', 1, '2017-03-23', '2017-03-23', '', '2017-02-26', 1, '2017-03-23', 1, 'Avaliable'),
(12, '0988809878', 'W100000988809878', 'IME1000000988809878', 'prepaid', 'md5', 'INS', 'AVATAR', 'Site1', 'physical', 1, 3, 'IRB_Test@ais.postbox.in.th; ', 1, '2017-03-23', '2017-03-23', '', '2017-02-27', 1, '2017-03-23', 1, 'Avaliable'),
(13, '0901166611', '0901166611', '0901166611', 'postpaid', 'Interstellar', 'RTBS', 'BOS', 'Site1', 'physical', 1, 1, 'test@gmail.com,emaple@gmail.com ', 5, '2017-03-24', '2017-03-24', 'ยืมนะ', '2017-02-27', 1, '2017-03-24', 1, 'Inused');

-- --------------------------------------------------------

--
-- Table structure for table `sim_reserve`
--

CREATE TABLE `sim_reserve` (
  `resv_id` int(11) NOT NULL,
  `mobile_no` varchar(15) NOT NULL,
  `team_id` varchar(10) NOT NULL,
  `email_contact` text NOT NULL,
  `proj_id` int(11) DEFAULT NULL,
  `valid_date` date DEFAULT NULL,
  `expire_date` date DEFAULT NULL,
  `remark` text,
  `reserve_date` date NOT NULL,
  `create_by` int(11) NOT NULL,
  `return_date` date NOT NULL,
  `update_by` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sim_reserve`
--

INSERT INTO `sim_reserve` (`resv_id`, `mobile_no`, `team_id`, `email_contact`, `proj_id`, `valid_date`, `expire_date`, `remark`, `reserve_date`, `create_by`, `return_date`, `update_by`) VALUES
(1, '0800000000', '1', 'test@gmail.com,test@gmail.com,test@gmail.com,test@gmail.com', 1, '2017-03-07', '2017-03-07', NULL, '2017-03-07', 1, '2017-03-22', 1),
(2, '0801111111', '1', '0801111111@gmail.com', 1, '2017-03-07', '2017-03-07', 'ยืม', '2017-03-14', 1, '2017-03-07', 1);

-- --------------------------------------------------------

--
-- Table structure for table `team`
--

CREATE TABLE `team` (
  `team_id` int(11) NOT NULL,
  `team_name` varchar(150) NOT NULL,
  `team_email` text NOT NULL,
  `create_date` date NOT NULL,
  `create_by` int(11) NOT NULL,
  `update_date` date NOT NULL,
  `update_by` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `team`
--

INSERT INTO `team` (`team_id`, `team_name`, `team_email`, `create_date`, `create_by`, `update_date`, `update_by`) VALUES
(1, 'CQS', 'test@gmail.com,emaple@gmail.com', '2017-03-07', 1, '0000-00-00', 0),
(2, 'BOSD', 'BOSD_Dev@ais.postbox.in.th;', '2017-03-09', 1, '2017-03-09', 0),
(3, 'IRB', 'IRB_Test@ais.postbox.in.th;', '2017-03-09', 1, '2017-03-09', 0);

-- --------------------------------------------------------

--
-- Table structure for table `testcase`
--

CREATE TABLE `sim_history` (
  `log_id` int(11) NOT NULL,
  `mobile_no` varchar(20) DEFAULT NULL,
  `system` varchar(20) DEFAULT NULL,
  `env` varchar(20) DEFAULT NULL,
  `site` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `team_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `remark` text,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `testcase` (
  `testcase_id` int(10) NOT NULL,
  `testcase_title` varchar(250) NOT NULL,
  `testcase_details` varchar(250) NOT NULL,
  `systems` varchar(100) NOT NULL,
  `enviroment` varchar(100) NOT NULL,
  `defect_no` varchar(100) NOT NULL,
  `issue_no` varchar(100) NOT NULL,
  `project_id` varchar(50) NOT NULL,
  `path_dir` varchar(250) NOT NULL,
  `step` int(11) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `testcase`
--

INSERT INTO `testcase` (`testcase_id`, `testcase_title`, `testcase_details`, `systems`, `enviroment`, `defect_no`, `issue_no`, `project_id`, `path_dir`, `step`, `create_by`, `create_date`) VALUES
(1, 'Test Call BOS Store Procedure Error', 'Test Call BOS Store Procedure Error. getPaymentMoneyNullError not success.', 'BOS', 'BOS', 'T170303001', '1', '1', 'D://Knowledge/1', 30, '1', '2017-03-27 10:06:21'),
(2, 'Test RTBS + Support Report Ccare', 'Test RTBS + Support Report Ccare. issue case hybrid, change charge type by account.', 'RTBS', 'IRB', 'T170303002', '2', '2', 'D://Knowledge/2', 1, '15', '2017-03-27 10:06:21');

--
ALTER TABLE `sim_history`
  ADD PRIMARY KEY (`log_id`);


ALTER TABLE `testcase`
  ADD PRIMARY KEY (`testcase_id`);


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
-- Indexes for table `enviroment`
--
ALTER TABLE `enviroment`
  ADD PRIMARY KEY (`env_id`);

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
-- Indexes for table `sim_reserve`
--
ALTER TABLE `sim_reserve`
  ADD PRIMARY KEY (`resv_id`),
  ADD KEY `team_id` (`team_id`),
  ADD KEY `proj_id` (`proj_id`);

--
-- Indexes for table `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`team_id`);

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
-- AUTO_INCREMENT for table `enviroment`
--
ALTER TABLE `enviroment`
  MODIFY `env_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
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
  MODIFY `proj_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `sim`
--
ALTER TABLE `sim`
  MODIFY `sim_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `sim_reserve`
--
ALTER TABLE `sim_reserve`
  MODIFY `resv_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `team`
--
ALTER TABLE `team`
  MODIFY `team_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

ALTER TABLE `testcase`
  MODIFY `testcase_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
  
ALTER TABLE `sim_history`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
