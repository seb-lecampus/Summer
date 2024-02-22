package com.example.Season;

import com.example.Season.domain_object.GamePlugin;
import com.example.Season.domain_object.TicTacToePlugin;
import fr.le_campus_numerique.square_games.engine.*;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGame;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGame;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;


public class tetst {
    public static void main(String[] args) throws InvalidPositionException {
        SpringApplication.run(tetst.class, args);
    }

    @RestController
    class test{
        @Autowired GamePlugin g;

        @GetMapping("/")
        public void t(){
            g.getName(null);
        }
    }
}
