package com.example.Season.rest_param;

import fr.le_campus_numerique.square_games.engine.CellPosition;

public record GameMoveParam(String PlayerId, CellPosition from, CellPosition to) {
}
