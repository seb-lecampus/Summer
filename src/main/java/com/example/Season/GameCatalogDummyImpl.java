package com.example.Season;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GameCatalogDummyImpl implements GameCatalog {

    @Override
    public Collection<String> getGameIdentifiers() {
        return GameFactorys.getIdentifiers();
    }
}
