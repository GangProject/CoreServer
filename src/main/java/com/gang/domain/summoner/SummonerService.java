package com.gang.domain.summoner;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Summoner.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Junwoo on 2017-04-07.
 */
@Service
public class SummonerService {
    @Autowired
    private SummonerRepository summonerRepository;

    @Transactional(readOnly = false)
    public void saveSummonerEntity(String name){
        RiotApi api = new RiotApi("RGAPI-e8372943-2ac3-4bed-8d2c-86f9c86174fe");
        Summoner summoner = null;

        try {
            summoner = api.getSummonerByName(Region.KR, name);
            System.out.println(summoner.getName());
            summonerRepository.save(SummonerEntity.of(summoner));
        }catch(RiotApiException e) {
            System.out.println(e);
        }
    }
}
