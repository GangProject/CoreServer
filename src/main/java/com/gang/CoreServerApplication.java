package com.gang;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Summoner.Summoner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import java.util.Map;

@SpringBootApplication
public class CoreServerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CoreServerApplication.class);
	}

	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	public static void main(String[] args) {

		SpringApplication.run(CoreServerApplication.class, args);

		RiotApi api = new RiotApi("RGAPI-e8372943-2ac3-4bed-8d2c-86f9c86174fe");
		try {
			Summoner summoner = api.getSummonerByName(Region.KR, "Bvest");
			long id = summoner.getId();
			System.out.println(id);
		}catch(RiotApiException e){
			System.out.println(e);
		}
	}
}
