create table oauth_client_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

create table oauth_access_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BLOB,
  refresh_token VARCHAR(255)
);

create table oauth_refresh_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication BLOB
);

create table oauth_code (
  code VARCHAR(255), authentication BLOB
);



insert into `cms_menu_unit` (`id`,`unit`) values (1,'Small');
insert into `cms_menu_unit` (`id`,`unit`) values (2,'Medium');
insert into `cms_menu_unit` (`id`,`unit`) values (3,'Large');
insert into `cms_menu_unit` (`id`,`unit`) values (4,'Half');
insert into `cms_menu_unit` (`id`,`unit`) values (5,'Full');
insert into `cms_menu_unit` (`id`,`unit`) values (6,'One');




insert into `cast_info` (`id`,`cast`) values (1,'Marathi');
insert into `cast_info` (`id`,`cast`) values (2,'Gujrati');
insert into `cast_info` (`id`,`cast`) values (3,'Jain');
insert into `cast_info` (`id`,`cast`) values (4,'Brahmin');
insert into `cast_info` (`id`,`cast`) values (5,'Muslim');


insert into `cms_cooks_speciality` (`id`,`speciality`) values (1,'Pre-Orders');
insert into `cms_cooks_speciality` (`id`,`speciality`) values (2,'Top Dishes');
insert into `cms_cooks_speciality` (`id`,`speciality`) values (3,'Weekend Special Dishes');
