package com.example.Season.controller;

import com.example.Season.domain_object.GameCatalog;
import com.example.Season.domain_object.GamePlugin;
import com.example.Season.dto.GameDTO;
import com.example.Season.dto.TokensDTO;
import com.example.Season.rest_param.GameCreationParams;
import com.example.Season.rest_param.GameMoveParam;
import com.example.Season.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

@RestController
public class GameController{
    @Autowired
    private GameService game;

    @Autowired
    private GameCatalog cat;

    @Autowired
    private List<GamePlugin> g;

    @GetMapping("/cat")
    public List<List<String>> getCat(@RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) Locale locale){
        return g.stream().map(e -> List.of(e.getName(locale), e.getId())).toList();
    }

    @GetMapping("/games")
    public Collection<GameDTO> getGames(){
        //System.out.println(game.getCurrentGames());
        return game.getCurrentGames();
    }

    @GetMapping("/games/{gameId}")
    public GameDTO getGameById(@PathVariable int gameId) {
        // TODO - actually get and return game with id 'gameId'
        System.out.println("GET /games/"+gameId);
        return game.getGameDTOById(gameId);
    }

    @GetMapping("/games/{gameId}/moves")
    public List<TokensDTO> getMovesByGame(@PathVariable int gameId) {
        // TODO - actually get and return game with id 'gameId'
        System.out.println("GET /games/"+gameId+"/moves");
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

    @PostMapping("/games2")
    public int createGame2(@RequestBody GameCreationParams params) {
        var plug = g.stream().filter(e -> e.getId().equals(params.factory())).iterator().next();
        int pc = (params.players() != 0) ? params.players() : plug.getDefaultPlayerCount();
        int bs = (params.size() != 0) ? params.size() : plug.getDefaultBoardSize();
        return game.createGameWithPlugin(plug, pc, bs);
    }


    @DeleteMapping("/games/{gameId}")
    public void deleteGame(@PathVariable int gameId) {
        game.deleteGame(gameId);
    }
}
