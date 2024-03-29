package com.example.Season.domain_object;

import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;

import java.util.ArrayList;

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
            identifiers.add(e.factory.getGameFactoryId());
        }
        return identifiers;
    }

    static public GameFactorys getById(String id){
        for(GameFactorys e : GameFactorys.values()){
           if(e.factory.getGameFactoryId().equals(id))
               return e;
        }
        return null;
    }
}
