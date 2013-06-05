INSERT INTO `users` (`id`,`login`,`passwd`,`first_name`,`last_name`,`email`,`phone`,`role`) VALUES
 (1,'administrator','9e87b917259bc9781b99af870891e002','Admin','Root','admin@gobro.net','+372','ADMIN');

insert into `score_rules` (`id`, `country`, `price`, `score`) values (1, 'EE', '5', 5);
insert into `score_rules` (`id`, `country`, `price`, `score`) values (2, 'EE', '10', 12);
insert into `score_rules` (`id`, `country`, `price`, `score`) values (3, 'EE', '15', 21);
insert into `score_rules` (`id`, `country`, `keyword`, `score`) values (5, 'LV', 'GIVE5', 5);
insert into `score_rules` (`id`, `country`, `keyword`, `score`) values (6, 'LV', 'GIVE12', 12);
insert into `score_rules` (`id`, `country`, `keyword`, `score`) values (7, 'LV', 'GIVE21', 20);
insert into `score_rules` (`id`, `country`, `keyword`, `score`) values (8, 'LT', 'GIVE5', 5);
insert into `score_rules` (`id`, `country`, `keyword`, `score`) values (9, 'LT', 'GIVE12', 12);
insert into `score_rules` (`id`, `country`, `keyword`, `score`) values (10, 'LT', 'GIVE21', 20);
