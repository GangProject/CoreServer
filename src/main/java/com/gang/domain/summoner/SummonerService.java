package com.gang.domain.summoner;

import com.gang.core.RiotApiManager;
import com.gang.core.StringNotFoundException;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Champion.ChampionList;
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
    private RiotApiManager riotApiManager;

    @Autowired
    private SummonerRepository summonerRepository;

    @Transactional(readOnly = false)
    public void saveSummonerEntity(String name) throws StringNotFoundException,InterruptedException{
        Summoner summoner = null;

        summoner = riotApiManager.getSummonerByName(Region.KR, name);
        System.out.println(summoner.getName());
        if(summonerRepository.findByName(summoner.getName())==null) {
            summonerRepository.save(SummonerEntity.of(summoner));
        }
    }
}
