INSERT INTO `presents` (`id`,`name`,`price`,`comments`) VALUES
 (1,'SONY ERICSSON 710C','999.00',NULL);

INSERT INTO `presents` (`id`,`name`,`price`,`comments`) VALUES
 (2,'SONY ERICSSON 711C','999.10',NULL);

INSERT INTO `presents` (`id`,`name`,`price`,`comments`) VALUES
 (3,'SONY ERICSSON 712C','999.20',NULL);

INSERT INTO `presents` (`id`,`name`,`price`,`comments`) VALUES
 (4,'SONY ERICSSON 713C','999.30',NULL);

INSERT INTO `presents` (`id`,`name`,`price`,`comments`) VALUES
 (5,'SONY ERICSSON 714C','999.40',NULL);

INSERT INTO `presents` (`id`,`name`,`price`,`comments`) VALUES
 (6,'SONY ERICSSON 715C','999.50',NULL);

INSERT INTO `presents` (`id`,`name`,`price`,`comments`) VALUES
 (7,'SONY ERICSSON 716C','999.60',NULL);

INSERT INTO `presents` (`id`,`name`,`price`,`comments`) VALUES
 (8,'SONY ERICSSON 710C','999.00',NULL);

INSERT INTO `presents` (`id`,`name`,`price`,`comments`) VALUES
 (9,'SONY ERICSSON 711C','999.10',NULL);

INSERT INTO `presents` (`id`,`name`,`price`,`comments`) VALUES
 (10,'SONY ERICSSON 712C','999.20',NULL);

INSERT INTO `presents` (`id`,`name`,`price`,`comments`) VALUES
 (11,'SONY ERICSSON 713C','999.30',NULL);

INSERT INTO `presents` (`id`,`name`,`price`,`comments`) VALUES
 (12,'SONY ERICSSON 714C','999.40',NULL);

INSERT INTO `presents` (`id`,`name`,`price`,`comments`) VALUES
 (13,'SONY ERICSSON 715C','999.50',NULL);

INSERT INTO `presents` (`id`,`name`,`price`,`comments`) VALUES
 (14,'SONY ERICSSON 716C','999.60',NULL);

INSERT INTO `games` (`id`,`presents_id`,`start_time`,`end_time`,`country`) VALUES
 (1,1,'2008-11-25 00:00:00','2008-11-26 00:00:00','EE');

INSERT INTO `games` (`id`,`presents_id`,`start_time`,`end_time`,`country`) VALUES
 (2,2,'2008-11-26 00:00:00','2008-11-27 00:00:00','EE');

INSERT INTO `games` (`id`,`presents_id`,`start_time`,`end_time`,`country`) VALUES
 (3,3,'2008-11-27 00:00:00','2008-11-28 00:00:00','EE');

INSERT INTO `games` (`id`,`presents_id`,`activation_time`,`start_time`,`end_time`,`country`) VALUES
 (4,4,'2008-11-01 00:00:00','2008-11-28 00:00:00','2008-11-29 00:00:00','EE');

INSERT INTO `games` (`id`,`presents_id`,`activation_time`,`start_time`,`end_time`,`country`) VALUES
 (5,5,'2008-11-01 00:00:00','2008-11-29 00:00:00','2008-11-30 00:00:00','EE');

INSERT INTO `games` (`id`,`presents_id`,`activation_time`,`start_time`,`end_time`,`country`) VALUES
 (6,6,'2008-11-01 00:00:00','2008-11-30 00:00:00','2008-12-01 00:00:00','EE');

INSERT INTO `games` (`id`,`presents_id`,`activation_time`,`start_time`,`end_time`,`country`) VALUES
 (7,7,'2008-11-01 00:00:00','2008-12-01 00:00:00','2008-12-02 00:00:00','EE');

INSERT INTO `games` (`id`,`presents_id`,`activation_time`,`start_time`,`end_time`,`country`) VALUES
 (8,8,'2008-11-01 00:00:00','2008-12-02 00:00:00','2008-12-03 00:00:00','EE');

INSERT INTO `games` (`id`,`presents_id`,`activation_time`,`start_time`,`end_time`,`country`) VALUES
 (9,9,'2008-11-01 00:00:00','2008-12-03 00:00:00','2008-12-04 00:00:00','EE');

INSERT INTO `games` (`id`,`presents_id`,`activation_time`,`start_time`,`end_time`,`country`) VALUES
 (10,10,'2008-11-01 00:00:00','2008-12-04 00:00:00','2008-12-05 00:00:00','EE');

INSERT INTO `games` (`id`,`presents_id`,`activation_time`,`start_time`,`end_time`,`country`) VALUES
 (11,11,'2008-11-01 00:00:00','2008-12-05 00:00:00','2008-12-06 00:00:00','EE');

INSERT INTO `games` (`id`,`presents_id`,`activation_time`,`start_time`,`end_time`,`country`) VALUES
 (12,12,'2008-11-01 00:00:00','2008-12-06 00:00:00','2008-12-07 00:00:00','EE');

INSERT INTO `games` (`id`,`presents_id`,`activation_time`,`start_time`,`end_time`,`country`) VALUES
 (13,13,'2008-11-01 00:00:00','2008-12-07 00:00:00','2008-12-08 00:00:00','EE');

INSERT INTO `games` (`id`,`presents_id`,`activation_time`,`start_time`,`end_time`,`country`) VALUES
 (14,14,'2008-11-01 00:00:00','2008-12-08 00:00:00','2008-12-09 00:00:00','EE');

INSERT INTO `users` (`id`,`login`,`passwd`,`first_name`,`last_name`,`email`,`phone`,`role`) VALUES
 (1,'igor.bljahhin','099b3b060154898840f0ebdfb46ec78f','Igor','Bljahhin','igor.bljahhin@tallink.ee','+3725131010','ADMIN');

INSERT INTO `user_plays` (`id`,`users_id`,`games_id`,`alias`) VALUES
 (1,1,1,'123451');

INSERT INTO `user_plays` (`id`,`users_id`,`games_id`,`alias`) VALUES
 (2,1,2,'123452');

INSERT INTO `user_plays` (`id`,`users_id`,`games_id`,`alias`) VALUES
 (3,1,3,'123453');

INSERT INTO `user_plays` (`id`,`users_id`,`games_id`,`alias`) VALUES
 (4,1,4,'123454');

INSERT INTO `user_plays` (`id`,`users_id`,`games_id`,`alias`) VALUES
 (5,1,5,'123455');

INSERT INTO `user_plays` (`id`,`users_id`,`games_id`,`alias`) VALUES
 (6,1,6,'123456');

INSERT INTO `user_plays` (`id`,`users_id`,`games_id`,`alias`) VALUES
 (7,1,7,'123457');

insert into `score_rules` (`id`, `country`, `price`, `score`) values (1, 'EE', '5', 5);
insert into `score_rules` (`id`, `country`, `price`, `score`) values (2, 'EE', '10', 12);
insert into `score_rules` (`id`, `country`, `price`, `score`) values (3, 'EE', '15', 20);
insert into `score_rules` (`id`, `country`, `price`, `score`) values (4, 'EE', '35', 50);
insert into `score_rules` (`id`, `country`, `keyword`, `score`) values (5, 'LV', 'FOR PLAYER', 5);
insert into `score_rules` (`id`, `country`, `keyword`, `score`) values (6, 'LV', 'FOR PLAYER10', 12);
insert into `score_rules` (`id`, `country`, `keyword`, `score`) values (7, 'LV', 'FOR PLAYER15', 20);
insert into `score_rules` (`id`, `country`, `keyword`, `score`) values (8, 'LV', 'FOR PLAYER35', 50);
