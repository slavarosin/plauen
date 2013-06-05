ALTER TABLE `users` ADD COLUMN `logged_in` timestamp;
UPDATE `users` SET `logged_in` = '2008-12-31 23:59' WHERE `logged_in` = '0000-00-00 00:00:00';