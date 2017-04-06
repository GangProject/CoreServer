package com.gang.core.api;

import java.util.logging.Logger;

/**
 * Created by Junwoo & Seungki on 2017-04-05.
 */
public class RiotApi {

    public static final Logger log = Logger.getLogger(RiotApi.class.getName());

    private final ApiConfig config;
    private final EndpointManager endpointManager;



    /**
     * 이름을 갖고 소환사 정보를 얻어 내는 메소드.
     */
    public Summoner getSummonerByName(Region region, String summonerName) throws RiotApiException {
        Objects.requireNonNull(region);
        Objects.requireNonNull(summonerName);
        Map<String, Summoner> summoners = getSummonersByName(region, summonerName);
        summoners = Convert.normalizeSummonerNames(summoners);
        String key = Convert.normalizeSummonerName(summonerName);
        if (!summoners.containsKey(key)) {
            throw new RiotApiException(RiotApiException.DATA_NOT_FOUND);
        }
        return summoners.get(key);
    }
}
