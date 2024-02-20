package com.example.Season;

import fr.le_campus_numerique.square_games.engine.*;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGame;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGame;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class tetst {
    public static void main(String[] args) throws InvalidPositionException {
        Game game = new TicTacToeGameFactory().createGame(2, 4);
        System.out.println(new TaquinGameFactory().getPlayerCountRange());
        System.out.println(game.getCurrentPlayerId());
        //System.out.println(game.getRemainingTokens());
        //System.out.println(game.getRemovedTokens());

        var v = Stream.concat(game.getRemovedTokens().stream(), Stream.concat(game.getRemainingTokens().stream(), game.getBoard().values().stream())).filter(Token::canMove).map(e -> List.of(e.getName(), e.getAllowedMoves())).toList();
        System.out.println(v);
    }
}
