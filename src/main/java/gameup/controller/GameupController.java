package gameup.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import gameup.controller.model.EventDTO;
import gameup.service.GameupService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/gameup")
@Slf4j
public class GameupController {
	@Autowired
	private GameupService gameupService;
	
	//  =====[  @GET Entities  ]=======================================================
	//  @Get /<entity> returns a list of all entities without children
	//  @Get /<entity>/{id} returns specified entity with all its children
	
	@GetMapping("/event")
	@ResponseStatus(code = HttpStatus.OK)
	public List<EventDTO> retrieveAllEvents()	{
		log.info("[ Retrieving all Events ]");
		return gameupService.retrieveAllEvents();		}
	
	@GetMapping("/event/{eventId}")
	@ResponseStatus(code = HttpStatus.OK)
	public EventDTO retrieveEventById(@PathVariable Long eventId)	{
		System.out.println(">>>>>    You have made it to the @GetMapping!");
		log.info("[ Retrieving Event with ID = {} ]", eventId);
		return gameupService.retrieveEventById(eventId);		}
	
	
	

//	@GetMapping("/event/{eventId}")
//	@GetMapping("/game")
//	@GetMapping("/game/{gameId}")
//	@GetMapping("/gamer")
//	@GetMapping("/gamer/{gamerId}")
//	@GetMapping("/human")
//	@GetMapping("/human/{humanId}")
//	@GetMapping("/location")
//	@GetMapping("/location/{locationId}")

	
	//  =====[  @POST Entities  ]======================================================
	//  =====[  @POST Relationships  ]=================================================
	//  =====[  @PUT Entities  ]=======================================================
	//  =====[  @PUT Entities  ]=======================================================
	//  =====[  @DELETE Entities  ]====================================================
	//  =====[  @DELETE Relationships  ]===============================================


}	//  End of GameupController Class
