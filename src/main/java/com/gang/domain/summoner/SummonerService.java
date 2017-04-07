package com.gang.domain.summoner;

import java.util.List;
import com.gang.api.common.ResponseDto;
import com.gang.core.AnalyzeUtil;
import com.gang.core.RiotApiManager;
import com.gang.core.StringNotFoundException;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.League.League;
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
    private AnalyzeUtil analyzeUtil;

    @Autowired
    private RiotApiManager riotApiManager;

    @Autowired
    private SummonerRepository summonerRepository;

    @Transactional(readOnly = false)
    public ResponseDto infoSummoner(String name) throws StringNotFoundException,InterruptedException{
        Summoner summoner = summonerRepository.findByName(name);
        List<League> leagues = null;

        if(summoner==null){ //최초 소환사 요청이 오면, 저장한다.
            summoner = riotApiManager.getSummonerByName(Region.KR, name);
            System.out.println(summoner.getName());
            summonerRepository.save(SummonerEntity.of(summoner));
        }

        leagues = riotApiManager.getLeagueBySummoner(summoner.getId());
        analyzeUtil.analyzeExcute(summoner, leagues);

        return ResponseDto.ofSuccess("성공");
    }
}