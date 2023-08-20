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
import gameup.controller.model.DeleteGamer2EventDTO;
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
	
	//  @Delete Events --------------------------------------------------------
	
	//  Delete an Event
	@DeleteMapping("/event/{eventId}")
	public DeleteEventDTO deleteEvent(@PathVariable Long eventId)	{
		log.info("Deleting Event with ID = " + eventId);
		return gameupDeleteService.deleteEvent(eventId);			}
	
	//  Remove a Gamer from an Event, If no more Gamers left, Cancel the event
	@DeleteMapping("/event/removeGamer")
	public DeleteGamer2EventDTO removeGamerFromEvent(@RequestBody DeleteGamer2EventDTO deleteGamer2EventDTO)	{
		log.info("Removing Gamer " + deleteGamer2EventDTO.getGamerId()
		+ " from Event " + deleteGamer2EventDTO.getEventId());
		return gameupDeleteService.deleteGamer2Event(deleteGamer2EventDTO);								}
	
	//  Remove a Game from an Event
	
	//  Remove a Location from an Event
	
	//  @Delete Games --------------------------------------------------------

	//  @Delete Gamers --------------------------------------------------------

	//  @Delete Locations --------------------------------------------------------

	//  @Delete Humans --------------------------------------------------------
	
}	//  End of GameUpDeleteController Class
