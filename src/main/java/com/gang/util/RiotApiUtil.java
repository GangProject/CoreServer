package com.gang.util;

import org.springframework.stereotype.Component;

/**
 * Created by Junwoo on 2017-04-03.
 */
@Component
public class RiotApiUtil {
    //api key
    private static final String API_KEY = "RGAPI-e8372943-2ac3-4bed-8d2c-86f9c86174fe";

    //request URL
    private static final String BASE_URL = "https://kr.api.riotgames.com/api/lol/KR/";
    private static final String SUMMONER_BY_NAME = "v1.4/summoner/by-name/";
    private static final String LEAGUE_BY_ID = "v2.5/league/by-summoner/";

    public String getApiKey(){
        return API_KEY;
    }

    public String getSummonerByNameURL(String name){
        return BASE_URL+SUMMONER_BY_NAME+name+"?api_key="+getApiKey();
    }


}
