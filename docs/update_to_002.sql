create index games_activation_time on games
(
   activation_time
);

create index games_end_time on games
(
   end_time
);

create index games_start_time on games
(
   start_time
);

create index games_country on games
(
   country
);

create index sms_user_plays_id_fk on sms
(
   user_plays_id
);

create index user_plays_games_id_fk on user_plays
(
   games_id
);

create index user_login on users
(
   login
);

create index user_email on users
(
   email
);

create index texts_presents_id_fk on texts
(
   presents_id
);

create index images_presents_id_fk on images
(
   presents_id
);

create index alias_reserve_games_id_fk on alias_reserve
(
   games_id
);
