package com.gang;

import net.rithms.riot.constant.PlatformId;
import net.rithms.riot.dto.Summoner.Summoner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(CoreServerApplication.class, args);

		ApiConfig config = new ApiConfig().setKey("RGAPI-e8372943-2ac3-4bed-8d2c-86f9c86174fe");
		RiotApi api = new RiotApi(config);

		Summoner summoner = api.getSummonerByName(PlatformId.KR, "tryndamere");
		System.out.println("Name: " + summoner.getName() + ", ID: " + summoner.getId());

	}
}
