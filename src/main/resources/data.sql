
INSERT INTO human (human_firstname, human_lastname, human_email, human_phone, human_note)
values ('Fred', 'Flintstone', 'fred@bedrock.net', '111-111-1111', 'Yabba Dabba Doo!');
INSERT INTO human (human_firstname, human_lastname, human_email, human_phone, human_note)
values ('Barney', 'Rubble', 'barney@bedrock.net', '222-222-2222', 'Rocks are people, too');
INSERT INTO human (human_firstname, human_lastname, human_email, human_phone, human_note)
values ('Bernard', 'Feldman', 'bernie@quips.com', '333-333-3333', 'First Person Shooter...');
INSERT INTO human (human_firstname, human_lastname, human_email, human_phone, human_note)
values ('Jerry', 'Smith', 'jerry@gws.com', '444-444-4444', 'Store Manager, Alpha City Games Workshop ');
INSERT INTO human (human_firstname, human_lastname, human_email, human_phone, human_note)
values ('Rick', 'Sanchez', 'rick@gws.com', '555-555-5555', 'Store Manager, Bakersville Games Workshop ');


INSERT INTO gamer (human_id, gamer_handle, gamer_email, gamer_discord, gamer_url, gamer_note)
values (1, 'FreakyFred', 'freakyfred@gmail.com', 'FreakyFred', 'http://profiles.dnd.com/FreakyFred', 'rock and roll, dudes...');
INSERT INTO gamer (human_id, gamer_handle, gamer_email, gamer_discord, gamer_url, gamer_note)
values (2, 'BarneyFife', 'barneyfife@gmail.com', 'BarneyFife', 'http://wh40k.com/profiles/BarneyFife', 'I like pizza!');
INSERT INTO gamer (human_id, gamer_handle, gamer_email, gamer_discord, gamer_url, gamer_note)
values (3, 'uncleBernie', 'unclebernie@urbanterror.info', 'uncleBernie', 'http://urbanterror.info/profiles/uncleBernie', 'G36 > The Cheatin Gun!');
INSERT INTO gamer (human_id, gamer_handle, gamer_email, gamer_discord, gamer_url, gamer_note)
values (3, 'giggles', 'giggles@urbanterror.info', 'giggles', 'http://urbanterror.info/profiles/giggles', 'Hee hee hee!');

INSERT INTO game (game_name, game_url, game_note)
values ('Dungeons & Dragons', 'http://welcome.dnd.com', 'Fun for all ages!');
INSERT INTO game (game_name, game_url, game_note)
values ('Warhammer 40k', 'http://welcome.wh40k.com', 'Not for the faint of heart...');
INSERT INTO game (game_name, game_url, game_note)
values ('Monopoly', 'http://ilovemonopoly.com', 'The One and Only');
INSERT INTO game (game_name, game_url, game_note)
values ('Sorry', 'http://youwillbesorry.com', 'simple & brainless');
INSERT INTO game (game_name, game_url, game_note)
values ('Candyland', 'http://candylandsweetness.com', 'Simplicity itself');
INSERT INTO game (game_name, game_url, game_note)
values ('UrbanTerror', 'http://urbanterror.info', 'Simplicity itself');

INSERT INTO event (event_name, event_level, event_date, event_starttime, event_endtime)
values ('Games Workshop Tournament', 'All levels', 'Sat Sep 24th, 2025', '1pm ET', '6pm ET');
INSERT INTO event (event_name, event_level, event_date, event_starttime, event_endtime)
values ('Game Night at Barneys House!', 'Level 25 and up', 'Sun Sep 25th, 2025', '5pm CT', '11pm CT');

INSERT INTO location (human_id, location_name, location_streetaddress, location_city, location_state, location_zip, location_phone, location_note)
values (4, 'Games Workshop - Alpha City ', '1111 Alpha Drive', 'Alpha City', 'Alaska', '11111', '111-111-1111', 'THE BEST place to spend money...');
INSERT INTO location (human_id, location_name, location_streetaddress, location_city, location_state, location_zip, location_phone, location_note)
values (5, 'Games Workshop - Bakersville', '2222 Baker Drive', 'Bakersville', 'Bidaho', '22222', '222-222-2222', 'No!  Spend your money here!');
INSERT INTO location (human_id, location_name, location_streetaddress, location_city, location_state, location_zip, location_phone, location_note)
values (2, 'Barneys House', '3333 Chuckles Drive', 'Covington', 'Connecticut', '33333', '333-333-3333', 'I provide the food, you bring the beer');
INSERT INTO location (human_id, location_name, location_streetaddress, location_city, location_state, location_zip, location_phone, location_note)
values (3, 'Bernies Place', '4444 Drivers Lane', 'Dusseldorf', 'Delaware', '44444', '444-444-4444', 'Just dont park on the grass');

INSERT INTO gamer_game (gamer_id, game_id) values (1,1);
INSERT INTO gamer_game (gamer_id, game_id) values (1,2);
INSERT INTO gamer_game (gamer_id, game_id) values (2,1);
INSERT INTO gamer_game (gamer_id, game_id) values (2,2);
INSERT INTO gamer_game (gamer_id, game_id) values (3,5);
INSERT INTO gamer_game (gamer_id, game_id) values (3,6);
INSERT INTO gamer_game (gamer_id, game_id) values (4,5);
INSERT INTO gamer_game (gamer_id, game_id) values (4,6);

INSERT INTO gamer_event (gamer_id, event_id) values (1,1);
INSERT INTO gamer_event (gamer_id, event_id) values (1,2);
INSERT INTO gamer_event (gamer_id, event_id) values (2,1);
INSERT INTO gamer_event (gamer_id, event_id) values (2,2);
INSERT INTO gamer_event (gamer_id, event_id) values (3,2);
INSERT INTO gamer_event (gamer_id, event_id) values (4,1);

INSERT INTO gamer_location (gamer_id, location_id) values (1,1);
INSERT INTO gamer_location (gamer_id, location_id) values (1,2);
INSERT INTO gamer_location (gamer_id, location_id) values (2,1);
INSERT INTO gamer_location (gamer_id, location_id) values (2,2);
INSERT INTO gamer_location (gamer_id, location_id) values (3,4);

INSERT INTO event_game (event_id, game_id) values (1,1);
INSERT INTO event_game (event_id, game_id) values (1,2);
INSERT INTO event_game (event_id, game_id) values (1,3);
INSERT INTO event_game (event_id, game_id) values (1,4);
INSERT INTO event_game (event_id, game_id) values (1,5);
INSERT INTO event_game (event_id, game_id) values (2,1);
INSERT INTO event_game (event_id, game_id) values (2,2);
INSERT INTO event_game (event_id, game_id) values (1,6);
INSERT INTO event_game (event_id, game_id) values (2,6);

INSERT INTO event_location (event_id, location_id) values (1,1);
INSERT INTO event_location (event_id, location_id) values (1,2);
INSERT INTO event_location (event_id, location_id) values (2,3);

