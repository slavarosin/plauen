ALTER TABLE `games` ADD CONSTRAINT `games_presents_fk` FOREIGN KEY `games_presents_fk` (`presents_id`)
    REFERENCES `presents` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT;

ALTER TABLE user_plays ADD CONSTRAINT user_plays_games_fk FOREIGN KEY (games_id)
  REFERENCES games (id) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE `alias_reserve` ADD CONSTRAINT `ar_games_fk` FOREIGN KEY `alias_reserve_games_fk` (`games_id`)
    REFERENCES `games` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT;

ALTER TABLE sms ADD CONSTRAINT sms_user_plays_fk FOREIGN KEY (user_plays_id)
  REFERENCES user_plays (id) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE user_plays ADD CONSTRAINT user_plays_users_fk FOREIGN KEY (users_id)
  REFERENCES users (id) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE texts ADD CONSTRAINT presents_texts_fk FOREIGN KEY (presents_id)
  REFERENCES presents (id) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE images ADD CONSTRAINT presents_images_fk FOREIGN KEY (presents_id)
  REFERENCES presents (id) ON DELETE CASCADE ON UPDATE RESTRICT;