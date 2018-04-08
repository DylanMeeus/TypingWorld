create table users(
	username varchar(40) not null primary key,
	email varchar(100),
	firstname varchar(30),
	lastname varchar(30),
	keyboard varchar(50),
	password varchar(128) -- for use with SHA256 -> 512
);