insert into `score_rules` (`id`, `country`, `price`, `score`) values (11, 'EE', '25', 40);
insert into `score_rules` (`id`, `country`, `price`, `score`) values (12, 'EE', '35', 63);

update score_rules set
service_id = "38aeddb7bc69763a865ada28abc6cbb5",
keyword = "FOR PLAYER",
shortcode = "13013",
currency = "EEK"
where price = "25.00" and country = "EE";

update score_rules set
service_id = "31ea3866bc2a5a2788c91d9d9ed87d6c",
keyword = "FOR PLAYER",
shortcode = "13015",
currency = "EEK"
where price = "35.00" and country = "EE";