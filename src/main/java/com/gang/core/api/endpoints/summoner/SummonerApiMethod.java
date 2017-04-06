package com.gang.core.api.endpoints.summoner;

/**
 * Created by Junwoo on 2017-04-05.
 */
abstract public class SummonerApiMethod extends ApiMethod{

    protected SummonerApiMethod(ApiConfig config){
        super(config,"summoner");
        requireApiKey();
    }
}
