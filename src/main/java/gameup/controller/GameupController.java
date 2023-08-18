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
import gameup.controller.model.GameDTO;
import gameup.controller.model.GamerDTO;
import gameup.controller.model.EventDTOFull;
import gameup.entity.Event;
import gameup.service.GameupService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/gameup")
@Slf4j
public class GameupController {
	@Autowired
	private GameupService gameupService;
	
	//  %%%%%[  @GET Entities  ]%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	
	//  Event -----------------------------------------------------
	
	@GetMapping("/event")
	@ResponseStatus(code = HttpStatus.OK)
	public List<EventDTO> retrieveAllEvents()	{
		log.info("[ Retrieving all Events ]");
		return gameupService.retrieveAllEvents();		}
	
	@GetMapping("/event/{eventId}")
	@ResponseStatus(code = HttpStatus.OK)
	public EventDTOFull retrieveEventById(@PathVariable Long eventId)	{
		System.out.println(">>>>>    You have made it to the @GetMapping!");
		log.info("[ Retrieving Event with ID = {} ]", eventId);
		return gameupService.retrieveEventById(eventId);		}
	
	// Game -----------------------------------------------------
	
	@GetMapping("/game")
	@ResponseStatus(code = HttpStatus.OK)
	public List<GameDTO> retrieveAllGames()	{
		log.info("[ Retrieving all Games ]");
		return gameupService.retrieveAllGames();		}
	
	@GetMapping("/game/{gameId}")
	@ResponseStatus(code = HttpStatus.OK)
	public GameDTO retrieveGameById(@PathVariable Long gameId)	{
		log.info("[ Retrieving Game with ID = {} ]", gameId);
		return gameupService.retrieveGameById(gameId);		}
	
	// Gamer -----------------------------------------------------

	@GetMapping("/gamer")
	@ResponseStatus(code = HttpStatus.OK)
	public List<GamerDTO> retrieveAllGamers()	{
		log.info("[ Retrieving all Gamers ]");
		return gameupService.retrieveAllGamers();		}
	
	@GetMapping("/gamer/{gamerId}")
	@ResponseStatus(code = HttpStatus.OK)
	public GamerDTO retrieveGamerById(@PathVariable Long gamerId)	{
		log.info("[ Retrieving Gamer with ID = {} ]", gamerId);
		return gameupService.retrieveGamerById(gamerId);		}

	// Human -----------------------------------------------------
	
	// Location --------------------------------------------------

	
	
//	@GetMapping("/human")
//	@GetMapping("/location")

	
	//  =====[  @POST Entities  ]======================================================
	//  =====[  @POST Relationships  ]=================================================
	//  =====[  @PUT Entities  ]=======================================================
	//  =====[  @PUT Entities  ]=======================================================
	//  =====[  @DELETE Entities  ]====================================================
	//  =====[  @DELETE Relationships  ]===============================================


}	//  End of GameupController Class
