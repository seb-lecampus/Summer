package com.example.Season.service;

import com.example.Season.domain_object.GameFactorys;
import com.example.Season.domain_object.GamePlugin;
import com.example.Season.dto.TokensDTO;
import com.example.Season.rest_param.GameCreationParams;
import com.example.Season.dto.GameDTO;
import com.example.Season.rest_param.GameMoveParam;

import java.util.Collection;
import java.util.List;

public interface GameService {
    int createGame(GameCreationParams params);
    int createGameWithPlugin(GamePlugin game);

    int createGameWithPlugin(GamePlugin game, int player_count, int board_size);

    Collection<GameDTO> getCurrentGames();

    void deleteGame(int id);

    GameDTO getGameDTOById(int gameId);

    List<TokensDTO> getPossibleMove(int gameId);

    void playGame(int id, GameMoveParam move);
}
