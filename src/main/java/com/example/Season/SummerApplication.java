package com.example.Season;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class SummerApplication {

	public static void main(String[] args) {
		System.out.println("kjnik");
		SpringApplication.run(SummerApplication.class, args);
	}

	@RestController
	public class GameController{
		@Autowired
		GameService game;

		@Autowired
		GameCatalog cat;

		@GetMapping("/cat")
		public Collection<String> getCat(){
			return cat.getGameIdentifiers();
		}

		@GetMapping("/games")
		public Collection<GameDTO> getGames(){
			//System.out.println(game.getCurrentGames());
			return game.getCurrentGames();
		}

		@GetMapping("/games/{gameId}")
		public List<List<Object>> getGameById(@PathVariable int gameId) {
			// TODO - actually get and return game with id 'gameId'
			System.out.println("GET /games/"+gameId);
			//return game.getGameDTOById(gameId);
			return game.getPossibleMove(gameId);
		}

		@PutMapping("/games/{gameId}")
		public void moveToken(@PathVariable int gameId, @RequestBody GameMoveParam body) {
			// TODO - actually get and return game with id 'gameId'
			System.out.println("PUT /games/"+gameId);
			System.out.println(body);
			game.playGame(gameId, body);
		}

		@PostMapping("/games")
		public int createGame(@RequestBody GameCreationParams params) {
			// TODO - actually create a new game
			System.out.println("POST /games/");
			System.out.println(params);
			return game.createGame(params);
		}

		@DeleteMapping("/games/{gameId}")
		public void deleteGame(@PathVariable int gameId) {
			game.deleteGame(gameId);
		}
	}

	public interface HeartbeatSensor {
		int getInt();
	}

	@RestController
	public class HeartbeatController {
		Random rng = new Random();
		@Autowired
		HeartbeatSensor heartbeatSensor;

		@GetMapping("/heartbeat")
		public int getHeartbeat(){
			return heartbeatSensor.getInt();
		}
	}

	@Service
	public class RandomHeartbeat implements HeartbeatSensor {
		Random rng = new Random();
		@Override
		public int getInt() {
			return rng.nextInt(230-40)+40;
		}
	}

}
