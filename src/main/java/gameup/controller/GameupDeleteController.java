package gameup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import gameup.service.GameupDeleteService;
import gameup.service.GameupPostPutService;
import lombok.extern.slf4j.Slf4j;
import gameup.controller.model.DeleteEventDTO;
import gameup.controller.model.Event2GameDTO;
import gameup.controller.model.Event2LocationDTO;
import gameup.controller.model.EventDTO;
import gameup.controller.model.GameDTO;
import gameup.controller.model.Gamer2EventDTO;
import gameup.controller.model.Gamer2GameDTO;
import gameup.controller.model.Gamer2LocationDTO;
import gameup.controller.model.GamerDTO;
import gameup.controller.model.Human2GamerDTO;
import gameup.controller.model.Human2LocationDTO;
import gameup.controller.model.HumanDTO;
import gameup.controller.model.LocationDTO;

@RestController
@RequestMapping("/gameup")
@Slf4j
public class GameupDeleteController {
	@Autowired
	private GameupDeleteService gameupDeleteService;
	
	//  @POST/@PUT Events --------------------------------------------------------
	@DeleteMapping("/event/{eventId}")
	public DeleteEventDTO deleteEvent(@PathVariable Long eventId)	{
		log.info("Deleting Event with ID = " + eventId);
		return gameupDeleteService.deleteEvent(eventId);			}
	
	//  @POST/@PUT Games --------------------------------------------------------

	//  @POST/@PUT Gamers --------------------------------------------------------

	//  @POST/@PUT Locations --------------------------------------------------------

	//  @POST/@PUT Humans --------------------------------------------------------

}	//  End of GameUpDeleteController Class
