ALTER TABLE `users` ADD COLUMN `signed_up` timestamp NOT NULL;
UPDATE `users` SET `signed_up` = '2008-12-31 23:59' WHERE `signed_up` = '0000-00-00 00:00:00';