package com.example.Season.dto;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Map;

public record GameDTO(Integer id,
                      String factoryId,
                      int players,
                      int boardSize,
                      Game game
)/* implements Comparable<GameDTO>*/ {
    /*@Override
    public int compareTo(GameDTO o) {
        return o.id.compareTo(this.id);
    }*/

    static public GameDTO fromMap(Map.Entry<Integer, Game> e) {
        var game = e.getValue();
        return new GameDTO(e.getKey(), game.getFactoryId(), game.getPlayerIds().size(), game.getBoardSize(), game);
    }

}
