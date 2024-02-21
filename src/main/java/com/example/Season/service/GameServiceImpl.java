package com.example.Season.service;

import com.example.Season.dto.TokensDTO;
import com.example.Season.rest_param.GameCreationParams;
import com.example.Season.dto.GameDTO;
import com.example.Season.rest_param.GameMoveParam;
import com.example.Season.domain_object.GameFactorys;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import fr.le_campus_numerique.square_games.engine.Token;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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

    public List<TokensDTO> getPossibleMove(Game game) {
        return Stream.concat(game.getRemovedTokens().stream(), Stream.concat(game.getRemainingTokens().stream(), game.getBoard().values().stream())).filter(Token::canMove).map(e -> new TokensDTO(e.getName(), e.getPosition(), e.getAllowedMoves())).toList();
    }

    public List<Token> getMovableToken(Game game) {
        return Stream.concat(game.getRemovedTokens().stream(), Stream.concat(game.getRemainingTokens().stream(), game.getBoard().values().stream())).filter(Token::canMove).toList();
    }

    @Override
    public List<TokensDTO> getPossibleMove(int gameId) {
        return getPossibleMove(games.get(gameId));
    }

    @Override
    public void playGame(int id, GameMoveParam move) {
        Game game = games.get(id);
        if(game.getCurrentPlayerId().equals(UUID.fromString(move.PlayerId()))){
            try {
                var moves = getMovableToken(game);
                var want = moves.stream().filter(tok -> (tok.getPosition() ==  move.from())).toList().getFirst();
                /*if(want == null)
                    want = get*/
                want.moveTo(move.to());
                System.out.println("moved");
            } catch (InvalidPositionException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Bad player");
        }
    }
}
