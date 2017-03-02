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