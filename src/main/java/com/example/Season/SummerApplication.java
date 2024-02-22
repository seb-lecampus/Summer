package com.example.Season;

import com.example.Season.domain_object.GameCatalog;
import com.example.Season.domain_object.GamePlugin;
import com.example.Season.dto.GameDTO;
import com.example.Season.dto.TokensDTO;
import com.example.Season.rest_param.GameCreationParams;
import com.example.Season.rest_param.GameMoveParam;
import com.example.Season.service.GameService;
import com.example.Season.service.HeartbeatSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@SpringBootApplication
public class SummerApplication {

	public static void main(String[] args) {
		System.out.println("kjnik");
		SpringApplication.run(SummerApplication.class, args);
	}

}
