package com.example.Season;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public interface GameService {
    int createGame(GameCreationParams params);

    Collection<GameDTO> getCurrentGames();

    void deleteGame(int id);

    GameDTO getGameDTOById(int gameId);

    List<List<Object>> getPossibleMove(int gameId);

    void playGame(int id, GameMoveParam move);
}
