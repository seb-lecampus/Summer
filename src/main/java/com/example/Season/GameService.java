package com.example.Season;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Collection;
import java.util.Map;

public interface GameService {
    int createGame(GameCreationParams params);

    Collection<GameDTO> getCurrentGames();

    void deleteGame(int id);

    GameDTO getGameById(int gameId);
}
