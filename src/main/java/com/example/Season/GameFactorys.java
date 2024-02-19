package com.example.Season;

import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;

import java.util.ArrayList;
import java.util.Collection;

public enum GameFactorys {
    TICTACTOE(new TicTacToeGameFactory()),
    TAQUIN(new TaquinGameFactory()),
    PUISSANCE4(new ConnectFourGameFactory());

    public final static int length = GameFactorys.values().length;
    public final GameFactory factory;

    GameFactorys(GameFactory factory){
        this.factory = factory;
    }

    static public ArrayList<String> getIdentifiers(){
        ArrayList<String> identifiers = new ArrayList<>(GameFactorys.length);
        for(GameFactorys e : GameFactorys.values()){
            identifiers.add(e.factory.getGameId());
        }
        return identifiers;
    }

    static public GameFactorys getById(String id){
        for(GameFactorys e : GameFactorys.values()){
           if(e.factory.getGameId().equals(id))
               return e;
        }
        return null;
    }
}
