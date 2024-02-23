package com.example.Season.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomHeartbeat implements HeartbeatSensor {
    private Random rng = new Random();

    @Override
    public int getInt() {
        return rng.nextInt(230 - 40) + 40;
    }
}
