package com.example.Season;

import com.example.Season.domain_object.GameFactorys;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import fr.le_campus_numerique.square_games.engine.Token;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

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
    public List<List<Object>> getPossibleMove(int gameId) {
        Game g = games.get(gameId);
        return Stream.concat(g.getRemovedTokens().stream(), Stream.concat(g.getRemainingTokens().stream(), g.getBoard().values().stream())).filter(Token::canMove).map(e -> List.of(e.getName(), e.getAllowedMoves())).toList();
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
