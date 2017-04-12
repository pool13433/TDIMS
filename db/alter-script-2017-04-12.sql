/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  POOL_LAPTOP
 * Created: Apr 12, 2017
 */
ALTER TABLE `profile` CHANGE `status` `status` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL;


INSERT INTO `config` (`con_id`, `con_code`, `con_name`, `con_value`) VALUES 
(NULL, 'ROLE', 'TESTER', 'TESTER'), 
(NULL, 'ROLE', 'TESTLEAD', 'TESTLEAD'),
(NULL, 'ROLE', 'ADMIN', 'ADMIN');


INSERT INTO `profile` (`profile_id`, `username`, `password`, `fname`, `lname`, `gender`, `mobile`, `email`, `position`, `create_date`, `create_by`, `update_date`, `update_by`, `status`) VALUES 
(NULL, 'tester', '81dc9bdb52d04dc20036dbd8313ed055', 'tester', 'tester', 'female', 'md5', 'md5@gmail.com', '3', '2017-02-26', '1', '2017-03-27', '1', 'TESTER'),
(NULL, 'lead', '81dc9bdb52d04dc20036dbd8313ed055', 'lead', 'lead', 'female', 'lead', 'lead@gmail.com', '3', '2017-02-26', '1', '2017-03-27', '1', 'TESTLEAD'),
(NULL, 'admin', '81dc9bdb52d04dc20036dbd8313ed055', 'admin', 'admin', 'female', 'admin', 'admin@gmail.com', '3', '2017-02-26', '1', '2017-03-27', '1',  'ADMIN');


INSERT INTO `config` (`con_code`, `con_name`, `con_value`) VALUES
( 'TC_TYPE', 'Project', 'Project'),
( 'TC_TYPE', 'CR', 'CR'),
( 'TC_TYPE', 'SR', 'SR'),
( 'TC_TYPE', 'Patch', 'Patch'),
( 'TC_TYPE', 'Support', 'Support');


CREATE TABLE `module` (
  `id` int(11) NOT NULL,
  `team_id` int(11) DEFAULT NULL,
  `module_name` varchar(100) DEFAULT NULL,
  `module_desc` varchar(100) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `module`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `module`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;


INSERT INTO `module` (`id`, `team_id`, `module_name`, `module_desc`, `create_date`, `create_by`) VALUES
(null, 1, 'Billing', 'Billing', '2017-04-11', 1),
(null, 2, 'eService', 'eService', '2017-04-11', 1);