package com.gang.domain.RankedStats;

import com.gang.api.common.ResponseDto;
import com.gang.constant.Tier;
import com.gang.core.AnalyzeUtil;
import com.gang.core.StringNotFoundException;
import com.gang.core.manager.ChampionApiManager;
import com.gang.core.manager.RankedStatsApiManager;
import com.gang.core.manager.SummonerApiManager;
import com.gang.domain.AggregateStats.AggregateStatsDto;
import com.gang.domain.Champion.ChampionEntityRepository;
import com.gang.domain.Champion.ChampionService;
import com.gang.domain.ChampionStats.ChampionStatsEntity;
import com.gang.domain.result.ResultEntity;
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
    private RankedStatsRepository rankedStatsRepository;

    @Autowired
    private ChampionEntityRepository championEntityRepository;

    public List<AggregateStatsDto> infoRankedStats(String name) throws RiotApiException, StringNotFoundException,InterruptedException {
        List<AggregateStatsDto> stats = new LinkedList<AggregateStatsDto>();
        SummonerEntity summonerEntity = summonerRepository.findByName(name);
        RankedStatsEntity rankedStatsEntity = null;

        summonerEntity = summonerService.firstAccess(summonerEntity, name);
        rankedStatsEntity = rankedStatsApiManager.getRankedStatsById(Region.KR, summonerEntity.getSummonerId());
        ResultEntity resultEntity = null;

        try{
            resultEntity = summonerService.infoSummoner(name).get(0);
        }catch(Exception e){

        }

        /*
          아직 db 에 챔피언을 저장해놓지 않았으므로 .. 챔피언 이름은 다시 수정할 것.
         */
        for (ChampionStatsEntity c : rankedStatsEntity.getChampions()) {
            int champId = c.getChampionId();
            String champName="";
            if(champId!=0) {
                champName = championEntityRepository.findByChampid(champId).getName();
            }
            AggregateStatsDto aggregateStatsDto = AggregateStatsDto.of(c.getStats(),champId,champName);
            int mmr = Tier.getMmrByTier(resultEntity.getTier(),resultEntity.getDivision());
            int played = aggregateStatsDto.getPlayed();
            double winningRate = aggregateStatsDto.getWinningRate();
            System.out.println(mmr);
            mmr = analyzeUtil.analyzeMmr(aggregateStatsDto.getKda(),mmr,winningRate,played);
            String analyzeTier = Tier.getTierNameByMmr(mmr);
            aggregateStatsDto.setTier(analyzeTier);
            System.out.println(mmr);
            try {
                stats.add(aggregateStatsDto);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        Collections.sort(stats);
        if(stats!=null && stats.size()>0) stats.remove(0);

        return stats;
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = false)
    public void rankedStatsRemove(String name){
        SummonerEntity summoner = summonerRepository.findByName(name);
        rankedStatsRepository.delete(rankedStatsRepository.findBySummonerId(summoner.getSummonerId()));
    }

}
