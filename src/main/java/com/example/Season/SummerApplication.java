package com.example.Season;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@SpringBootApplication
public class SummerApplication {

	public static void main(String[] args) {
		System.out.println("kjnik");
		SpringApplication.run(SummerApplication.class, args);
	}

	@RestController
	public class HelloWorldController {
		@GetMapping("hello-world")
		public String HelloWorld(){
			return "Hello World";
		}
	}

	public interface HeartbeatSensor {
		int getInt();
	}

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

	@Service
	public class RandomHeartbeat implements HeartbeatSensor {
		Random rng = new Random();
		@Override
		public int getInt() {
			return rng.nextInt(230-40)+40;
		}
	}

}
