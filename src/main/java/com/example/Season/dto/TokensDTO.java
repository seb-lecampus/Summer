package com.example.Season.dto;

import fr.le_campus_numerique.square_games.engine.CellPosition;

import java.util.Collection;
import java.util.List;

public record TokensDTO(String name, CellPosition pos, Collection<CellPosition> possible_moves) {

}
