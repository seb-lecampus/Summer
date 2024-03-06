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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.StreamSupport.stream;


public class tetst {
    public record player(String name){}

    public static void main(String[] args) {
        ResponseEntity<player[]> response = new RestTemplate().getForEntity("http://172.22.114.56:9191/Players", player[].class);

        player[] playerslist = response.getBody();

        Arrays.stream(playerslist).forEach(e -> System.out.println(e.name()));
    }
}
