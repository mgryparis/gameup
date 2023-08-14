
INSERT INTO human (human_firstname, human_lastname, human_email, human_phone, human_note)
values ('Fred', 'Flintstone', 'fred@bedrock.net', '111-111-1111', 'Yabba Dabba Doo!');
INSERT INTO human (human_firstname, human_lastname, human_email, human_phone, human_note)
values ('Barney', 'Rubble', 'barney@bedrock.net', '222-222-2222', 'Rocks are people, too');

INSERT INTO gamer (human_id, gamer_handle, gamer_email, gamer_discord, gamer_url, gamer_note)
values (1, 'FreakyFred', 'freakyfred@gmail.com', 'FreakyFred', 'http://profiles.dnd.com/FreakyFred', 'rock and roll, dudes...');
INSERT INTO gamer (human_id, gamer_handle, gamer_email, gamer_discord, gamer_url, gamer_note)
values (2, 'BarneyFife', 'barneyfife@gmail.com', 'BarneyFife', 'http://wh40k.com/profiles/BarneyFife', 'I like pizza!');

INSERT INTO game (game_name, game_url, game_note)
values ('Dungeons & Dragons', 'http://welcome.dnd.com', 'Fun for all ages!');
INSERT INTO game (game_name, game_url, game_note)
values ('Warhammer 40k', 'http://welcome.wh40k.com', 'Not for the faint of heart...');

INSERT INTO event (event_name, event_level, event_date, event_starttime, event_endtime)
values ('GameShop Party', 'All levels', 'Sat Sep 24th, 2025', '1pm ET', '6pm ET');
INSERT INTO event (event_name, event_level, event_date, event_starttime, event_endtime)
values ('Game Party Time', 'Level 25 and up', 'Sun Sep 25th, 2025', '5pm CT', '11pm CT');

INSERT INTO location (human_id, location_name, location_streetaddress, location_city, location_state, location_zip, location_phone, location_note)
values (1, 'GameShop', '1111 Alpha Drive', 'Alpha City', 'Alaska', '11111', '111-111-1111', 'THE place to spend money...');
INSERT INTO location (human_id, location_name, location_streetaddress, location_city, location_state, location_zip, location_phone, location_note)
values (2, 'Barneys House', '2222 Baker Drive', 'Bakersville', 'Bidaho', '22222', '222-222-2222', 'I provide the food, you bring the beer');

INSERT INTO gamer_game (gamer_id, game_id) values (1,1);
INSERT INTO gamer_game (gamer_id, game_id) values (1,2);
INSERT INTO gamer_game (gamer_id, game_id) values (2,1);
INSERT INTO gamer_game (gamer_id, game_id) values (2,2);

INSERT INTO gamer_event (gamer_id, event_id) values (1,1);
INSERT INTO gamer_event (gamer_id, event_id) values (1,2);
INSERT INTO gamer_event (gamer_id, event_id) values (2,1);
INSERT INTO gamer_event (gamer_id, event_id) values (2,2);

INSERT INTO gamer_location (gamer_id, location_id) values (1,1);
INSERT INTO gamer_location (gamer_id, location_id) values (1,2);
INSERT INTO gamer_location (gamer_id, location_id) values (2,1);
INSERT INTO gamer_location (gamer_id, location_id) values (2,2);

INSERT INTO event_game (event_id, game_id) values (1,1);
INSERT INTO event_game (event_id, game_id) values (1,2);
INSERT INTO event_game (event_id, game_id) values (2,1);
INSERT INTO event_game (event_id, game_id) values (2,2);

INSERT INTO event_location (event_id, location_id) values (1,1);
INSERT INTO event_location (event_id, location_id) values (1,2);
INSERT INTO event_location (event_id, location_id) values (2,1);
INSERT INTO event_location (event_id, location_id) values (2,2);
