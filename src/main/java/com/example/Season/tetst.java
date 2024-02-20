package com.example.Season;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGame;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGame;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;

public class tetst {
    public static void main(String[] args) throws InvalidPositionException {
        Game game = new TaquinGameFactory().createGame(1, 4);
        System.out.println(new TaquinGameFactory().getPlayerCountRange());
        System.out.println(game.getCurrentPlayerId());
        System.out.println(game.getRemainingTokens());
        System.out.println(game.getRemovedTokens());

        game.getBoard().
    }
}
