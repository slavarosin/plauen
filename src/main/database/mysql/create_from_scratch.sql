DROP TABLE IF EXISTS `games`;
CREATE TABLE  `games` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `presents_id` int(10) unsigned NOT NULL,
  `activation_time` timestamp NOT NULL,
  `start_time` timestamp NOT NULL,
  `end_time` timestamp NOT NULL,
  `country` varchar(2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `games_FKIndex1` (`presents_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `images`;
CREATE TABLE  `images` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `presents_id` int(10) unsigned NOT NULL,
  `image` blob NOT NULL,
  PRIMARY KEY (`id`),
  KEY `images_FKIndex1` (`presents_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `presents`;
CREATE TABLE  `presents` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `comments` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `sms`;
CREATE TABLE  `sms` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_plays_id` int(10) unsigned NOT NULL,
  `message` text NOT NULL,
  `sender` varchar(32) NOT NULL,
  `country` varchar(2) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `currency` varchar(3) NOT NULL,
  `service_id` varchar(32) NOT NULL,
  `message_id` varchar(128) NOT NULL,
  `keyword` varchar(12) NOT NULL,
  `shortcode` varchar(5) NOT NULL,
  `msg_status` varchar(7) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sms_FKIndex1` (`user_plays_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `texts`;
CREATE TABLE  `texts` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `presents_id` int(10) unsigned NOT NULL,
  `language` varchar(2) DEFAULT NULL,
  `code` varchar(32) NOT NULL,
  `text` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `texts` (`presents_id`, `language`, `code`),
  KEY `texts_FKIndex1` (`presents_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `users`;
CREATE TABLE  `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(32) NOT NULL,
  `passwd` varchar(32) NOT NULL,
  `first_name` varchar(32) NOT NULL,
  `last_name` varchar(32) NOT NULL,
  `email` varchar(64) NOT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `role` varchar(5) NOT NULL,
  `city` varchar(32) NOT NULL,
  `address` varchar(32) NOT NULL,
  `zip` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_login` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `user_plays`;
CREATE TABLE  `user_plays` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `users_id` int(10) unsigned NOT NULL,
  `games_id` int(10) unsigned NOT NULL,
  `alias` varchar(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `games` (`users_id`, `games_id`),
  KEY `users_has_games_FKIndex1` (`users_id`),
  KEY `users_has_games_FKIndex2` (`games_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `alias_reserve`;
CREATE TABLE  `alias_reserve` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `games_id` int(10) unsigned NOT NULL,
  `start_number` int(6) unsigned NOT NULL default 0,
  `end_number` int(6) unsigned NOT NULL default 100,
  `current_number` int(6) unsigned NOT NULL default 0,
  `date_created` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `games` (`games_id`,`start_number`,`end_number`),
  KEY `alias_to_games_FKIndex2` (`games_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS `score_rules`;
CREATE TABLE  `score_rules` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `country` varchar(2) NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `keyword` varchar(12) NOT NULL,
  `score` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `country_rule` (`country`,`price`,`keyword`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

#First meaningless reserve
INSERT INTO `alias_reserve`(`games_id`, `start_number`,`end_number`,`current_number`, `date_created`) VALUES(0,0,0,0,CURRENT_TIMESTAMP);