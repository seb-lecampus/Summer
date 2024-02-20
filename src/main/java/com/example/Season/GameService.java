package com.example.Season;

import java.util.Collection;

public interface GameService {
    int createGame(GameCreationParams params);

    Collection<GameDTO> getCurrentGames();

    void deleteGame(int id);

    GameDTO getGameDTOById(int gameId);

    void playGame(int id, GameMoveParam move);
}
