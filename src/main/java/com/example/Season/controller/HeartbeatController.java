package com.example.Season.controller;

import com.example.Season.service.HeartbeatSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HeartbeatController {
    Random rng = new Random();
    @Autowired
    HeartbeatSensor heartbeatSensor;

    @GetMapping("/heartbeat")
    public int getHeartbeat(){
        return heartbeatSensor.getInt();
    }
}
