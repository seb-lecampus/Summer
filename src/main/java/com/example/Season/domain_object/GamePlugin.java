package com.example.Season.domain_object;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Locale;

public interface GamePlugin {
    public String getName(Locale locale);
    public String getId();
    Game createGame();
    Game CreateGame(int playerCount, int boardSize);
    int getDefaultPlayerCount();
    int getDefaultBoardSize();
}
