DROP TABLE IF EXISTS `winners`;
CREATE TABLE  `winners` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `users_id` int(10) unsigned NOT NULL,
  `games_id` int(10) unsigned NOT NULL,
  `timestamp` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `games` (`users_id`, `games_id`),
  KEY `users_has_games_FKIndex1` (`users_id`),
  KEY `users_has_games_FKIndex2` (`games_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
