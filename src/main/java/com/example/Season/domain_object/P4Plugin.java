package com.example.Season.domain_object;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class P4Plugin implements GamePlugin {
    GameFactory factory = new ConnectFourGameFactory();
    @Autowired
    ResourceBundleMessageSource msg;
    @Value("${P4.default.player_count}")
    private int default_players_count;

    @Value("${P4.default.size}")
    private int default_board_size;

    @Override
    public String getName(Locale locale) {
        return msg.getMessage("P4", null, locale);
    }

    @Override
    public String getId() {
        return factory.getGameId();
    }

    @Override
    public Game createGame() {
        return factory.createGame(default_players_count, default_board_size);
    }

    @Override
    public Game CreateGame(int playerCount, int boardSize) {
        return factory.createGame(playerCount, boardSize);
    }

    @Override
    public int getDefaultPlayerCount() {
        return default_players_count;
    }

    @Override
    public int getDefaultBoardSize() {
        return default_board_size;
    }
}
