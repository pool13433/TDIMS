ALTER TABLE `profile` ADD `department` INT(11) NOT NULL AFTER `position`;ALTER TABLE `testcase` ADD `update_date` TIMESTAMP on update CURRENT_TIMESTAMP() NOT NULL DEFAULT CURRENT_TIMESTAMP() AFTER `update_by`;ALTER TABLE `testcase` ADD `update_by` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `create_date`;ALTER TABLE `testcase` ADD `automate` INT(100) NOT NULL AFTER `step`;