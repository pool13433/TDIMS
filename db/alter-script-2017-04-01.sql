ALTER TABLE `sim` CHANGE `serial_no` `serial_no` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL, CHANGE `imsi` `imsi` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL, CHANGE `region_code` `region_code` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL, CHANGE `site` `site` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL, CHANGE `owner` `owner` INT(11) NULL, CHANGE `update_date` `update_date` DATE NULL;