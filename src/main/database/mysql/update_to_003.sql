ALTER TABLE `score_rules` ADD COLUMN `service_id` VARCHAR(32) NOT NULL AFTER `score`,
ADD COLUMN `shortcode` VARCHAR(5) NOT NULL AFTER `service_id`,
ADD COLUMN `currency` VARCHAR(3) NOT NULL AFTER `shortcode`;