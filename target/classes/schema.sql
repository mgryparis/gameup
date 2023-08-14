DROP TABLE IF EXISTS event_location;
DROP TABLE IF EXISTS event_game;
DROP TABLE IF EXISTS gamer_location;
DROP TABLE IF EXISTS gamer_event;
DROP TABLE IF EXISTS gamer_game;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS gamer;
DROP TABLE IF EXISTS human;

CREATE TABLE human (
	human_id int NOT NULL AUTO_INCREMENT,
	human_firstname varchar(128) NOT NULL,
	human_lastname  varchar(128) NOT NULL,
	human_email varchar(256), 
	human_phone varchar(30),
	human_note varchar(1024),
	PRIMARY KEY (human_id)
);

CREATE TABLE gamer (
	gamer_id int NOT NULL AUTO_INCREMENT,
	human_id int NOT NULL,
	gamer_handle varchar(128) NOT NULL,
	gamer_email varchar(256),
	gamer_discord varchar(128), 
	gamer_url varchar(256),
	gamer_note varchar(1024),
	PRIMARY KEY (gamer_id),
	FOREIGN KEY (human_id) REFERENCES human (human_id)
);

CREATE TABLE game (
	game_id int NOT NULL AUTO_INCREMENT,
	game_name varchar(128) NOT NULL,
	game_url varchar(256), 
	game_note varchar(1024),
	PRIMARY KEY (game_id)
);

CREATE TABLE event (
	event_id int NOT NULL AUTO_INCREMENT,
	event_name varchar(128) NOT NULL,
	event_level varchar(30) NOT NULL,
	event_date varchar(40), 
	event_starttime varchar(20),
	event_endtime varchar(20),
	PRIMARY KEY (event_id)
);

CREATE TABLE location (
	location_id int NOT NULL AUTO_INCREMENT,
	human_id int NOT NULL,
	location_name varchar(128) NOT NULL,
	location_streetaddress varchar(128) NOT NULL,
	location_city varchar(60),
	location_state varchar(40),
	location_zip varchar(20),
	location_phone varchar(30),
	location_note varchar(1024),
	PRIMARY KEY (location_id),
	FOREIGN KEY (human_id) REFERENCES human (human_id)
);

CREATE TABLE gamer_game (
	gamer_id int NOT NULL,
	game_id int NOT NULL,
	FOREIGN KEY (gamer_id) REFERENCES gamer (gamer_id),
	FOREIGN KEY (game_id) REFERENCES game (game_id)
);

CREATE TABLE gamer_event (
	gamer_id int NOT NULL,
	event_id int NOT NULL,
	FOREIGN KEY (gamer_id) REFERENCES gamer (gamer_id),
	FOREIGN KEY (event_id) REFERENCES event (event_id)
);

CREATE TABLE gamer_location (
	gamer_id int NOT NULL,
	location_id int NOT NULL,
	FOREIGN KEY (gamer_id) REFERENCES gamer (gamer_id),
	FOREIGN KEY (location_id) REFERENCES location (location_id)
);

CREATE TABLE event_game (
	event_id int NOT NULL,
	game_id int NOT NULL,
	FOREIGN KEY (event_id) REFERENCES event (event_id),
	FOREIGN KEY (game_id) REFERENCES game (game_id)
);

CREATE TABLE event_location (
	event_id int NOT NULL,
	location_id int NOT NULL,
	FOREIGN KEY (event_id) REFERENCES event (event_id),
	FOREIGN KEY (location_id) REFERENCES location (location_id)
);
