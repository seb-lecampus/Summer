package com.example.Season;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public GameDTO getGameDTOById(int gameId) {
        var game = games.get(gameId);
        return new GameDTO(gameId, game.getFactoryId(), game.getPlayerIds().size(), game.getBoardSize(), game);
    }

    @Override
    public void playGame(int id, GameMoveParam move) {
        Game game = games.get(id);
        if(game.getCurrentPlayerId().equals(UUID.fromString(move.PlayerId()))){
            try {
                game.getRemainingTokens().iterator().next().moveTo(new CellPosition(move.x(), move.y()));
                System.out.println("moved");
            } catch (InvalidPositionException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Bad player");
        }
    }
}
