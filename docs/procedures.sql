DELIMITER $$

DROP PROCEDURE IF EXISTS `getAlias` $$
CREATE PROCEDURE `getAlias` (
  IN gameId int,
  IN targetDate timestamp,
  OUT aliasGenerated int
)
getAliasProc: BEGIN
	DECLARE reserveVar INT DEFAULT 100 ;
	DECLARE	resultVar INT DEFAULT 0 ;
	DECLARE	exVar INT DEFAULT 0 ;
	DECLARE	newReserveStartVar INT DEFAULT 0 ;
	SELECT `current_number` INTO exVar FROM `alias_reserve` WHERE gameId =  `games_id` AND `current_number` < `end_number` ;
	IF( exVar is NULL or exVar = 0) THEN
		SELECT MIN(`end_number`) INTO newReserveStartVar FROM `alias_reserve` a WHERE NOT EXISTS( SELECT `current_number` FROM `alias_reserve` WHERE `start_number` = a.`end_number` + 1) ;
		INSERT INTO `alias_reserve`(`games_id`, `start_number`,`end_number`,`current_number`, `date_created`)
			VALUES (gameId, newReserveStartVar + 1, newReserveStartVar + reserveVar, newReserveStartVar + 1, targetDate) ;
		SET aliasGenerated = newReserveStartVar + 1 ;
	ELSE
		SET aliasGenerated = exVar + 1 ;
		UPDATE `alias_reserve` SET `current_number` = aliasGenerated WHERE `games_id` = gameId AND aliasGenerated <= `end_number` AND aliasGenerated >= `start_number` ;
	END IF ;
END getAliasProc $$

DROP PROCEDURE IF EXISTS `clearOldReserves` $$
CREATE PROCEDURE `clearOldReserves` (
	in targetDate timestamp
)
BEGIN
	DELETE FROM `alias_reserve` WHERE `date_created` < targetDate ;
END $$

DELIMITER ;