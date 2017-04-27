package com.gang.domain.RankedStats;

import com.gang.api.common.ResponseDto;
import com.gang.core.AnalyzeUtil;
import com.gang.core.StringNotFoundException;
import com.gang.core.manager.ChampionApiManager;
import com.gang.core.manager.RankedStatsApiManager;
import com.gang.core.manager.SummonerApiManager;
import com.gang.domain.AggregateStats.AggregateStatsDto;
import com.gang.domain.Champion.ChampionEntityRepository;
import com.gang.domain.Champion.ChampionService;
import com.gang.domain.ChampionStats.ChampionStatsEntity;
import com.gang.domain.summoner.SummonerEntity;
import com.gang.domain.summoner.SummonerRepository;
import com.gang.domain.summoner.SummonerService;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Region;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import net.rithms.riot.constant.staticdata.ChampData;
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
    private ChampionService championService;

    @Autowired
    private SummonerApiManager summonerApiManager;

    @Autowired
    private SummonerService summonerService;

    @Autowired
    private SummonerRepository summonerRepository;

    @Autowired
    private ChampionEntityRepository championEntityRepository;

    public List<AggregateStatsDto> infoRankedStats(String name) throws RiotApiException, StringNotFoundException,InterruptedException{
        List<AggregateStatsDto> stats = new LinkedList<AggregateStatsDto>();
        SummonerEntity summonerEntity = summonerRepository.findByName(name);
        RankedStatsEntity rankedStatsEntity = null;

        summonerEntity = summonerService.firstAccess(summonerEntity,name);
        rankedStatsEntity = rankedStatsApiManager.getRankedStatsById(Region.KR,summonerEntity.getSummonerId());

        /*
          아직 db 에 챔피언을 저장해놓지 않았으므로 .. 챔피언 이름은 다시 수정할 것.
         */
        for(ChampionStatsEntity c : rankedStatsEntity.getChampions()){
            int champId = c.getChampionId();

            try {
                stats.add(AggregateStatsDto.of(c.getStats(), champId, "피즈"));
            }catch(Exception e){
                System.out.println(e);
            }
        }

        Collections.sort(stats);

        return stats;
    }
}
