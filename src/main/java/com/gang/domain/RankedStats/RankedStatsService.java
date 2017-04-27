package com.gang.domain.RankedStats;

import com.gang.api.common.ResponseDto;
import com.gang.core.AnalyzeUtil;
import com.gang.core.StringNotFoundException;
import com.gang.core.manager.RankedStatsApiManager;
import com.gang.core.manager.SummonerApiManager;
import com.gang.domain.summoner.SummonerEntity;
import com.gang.domain.summoner.SummonerRepository;
import com.gang.domain.summoner.SummonerService;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Summoner.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Junwoo on 2017-04-27.
 */
@Service
public class RankedStatsService {
    @Autowired
    private AnalyzeUtil analyzeUtil;

    @Autowired
    private RankedStatsApiManager rankedStatsApiManager;

    @Autowired
    private SummonerApiManager summonerApiManager;

    @Autowired
    private SummonerService summonerService;

    @Autowired
    private SummonerRepository summonerRepository;


    public ResponseDto infoRankedStats(String name) throws StringNotFoundException,InterruptedException{
        SummonerEntity summonerEntity = summonerRepository.findByName(name);
        RankedStatsEntity rankedStatsEntity = null;

        summonerEntity = summonerService.firstAccess(summonerEntity,name);
        rankedStatsEntity = rankedStatsApiManager.getRankedStatsById(Region.KR,summonerEntity.getSummonerId());

        return ResponseDto.ofSuccess(rankedStatsEntity,"성공");
    }
}
