package com.example.Season;

import com.sun.source.tree.Tree;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    static int id = 0;
    Map<Integer, Game> games = new TreeMap<>();

    @Override
    public int createGame(GameCreationParams params) {
        int id = ++GameServiceImpl.id;
        games.put(id, GameFactorys.getById(params.factory()).factory.createGame(params.players(), params.size()));
        return id;
    }

    @Override
    public Collection<GameDTO> getCurrentGames() {
        return games.entrySet().stream().map(GameDTO::fromMap).toList();
    }

    @Override
    public void deleteGame(int id) {
        games.remove(id);
    }

    @Override
    public GameDTO getGameById(int gameId) {
        var game = games.get(gameId);
        return new GameDTO(gameId, game.getFactoryId(), game.getPlayerIds().size(), game.getBoardSize());
    }
}
