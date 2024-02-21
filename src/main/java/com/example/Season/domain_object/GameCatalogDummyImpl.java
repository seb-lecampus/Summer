package com.example.Season.domain_object;

import com.example.Season.domain_object.GameCatalog;
import com.example.Season.domain_object.GameFactorys;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GameCatalogDummyImpl implements GameCatalog {

    @Override
    public Collection<String> getGameIdentifiers() {
        return GameFactorys.getIdentifiers();
    }
}
