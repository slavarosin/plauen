ALTER TABLE `sms` ADD COLUMN `disabled` BOOLEAN NOT NULL DEFAULT 0 AFTER `user_plays_id`;