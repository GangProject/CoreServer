package com.gang.domain.summoner;

import java.util.List;
import com.gang.api.common.ResponseDto;
import com.gang.constant.Tier;
import com.gang.core.AnalyzeUtil;
import com.gang.core.RiotApiManager;
import com.gang.core.StringNotFoundException;
import com.gang.core.manager.LeagueApiManager;
import com.gang.core.manager.SummonerApiManager;
import com.gang.domain.result.ResultEntity;
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
    private SummonerApiManager summonerApiManager;

    @Autowired
    private LeagueApiManager leagueApiManager;

    //@Autowired
    //private LeagueApiManager leagueApiManager;

    @Autowired
    private SummonerRepository summonerRepository;

    @Transactional(readOnly = false)
    public List<ResultEntity> infoSummoner(String name) throws StringNotFoundException,InterruptedException{
        SummonerEntity summonerEntity = summonerRepository.findByName(name);
        List<League> leagues = null;
        List<ResultEntity> resultEntityDtos = null;

        summonerEntity = firstAccess(summonerEntity,name);

        leagues = leagueApiManager.getLeagueEntryBySummoner(Region.KR, String.valueOf(summonerEntity.getSummonerId()));
        resultEntityDtos = analyzeUtil.analyzeExcute(summonerEntity, leagues);

        return resultEntityDtos;
    }

    @Transactional(readOnly = false)
    public void summonerRemove(String name){
        summonerRepository.delete(summonerRepository.findByName(name));
    }

    public ResponseDto mmr(String name) throws StringNotFoundException, InterruptedException{
        MmrDto mmrDto = null;
        int myMmr = 0;
        String myTierKor = null;
        String myTierEng = null;
        String myDivision = null;

        int analyzeMmr = 0;
        String predictTierKor = null;
        String predictTierEng = null;
        String predictDivision = null;
        ResultEntity resultEntity = null;

        List<ResultEntity> list = infoSummoner(name);
        if(list.size()>0){
            resultEntity = list.get(0);
            myMmr = Tier.getMmrByTier(resultEntity.getTier(),resultEntity.getDivision());
            String myTemp[] = Tier.getTierNameByMmr(myMmr).split(" ");
            String myTemp2[] = Tier.getTierNameEngMmr(myMmr).split(" ");
            myTierKor = myTemp[0];
            myTierEng = myTemp2[0];
            myDivision = myTemp[1];

            analyzeMmr = analyzeUtil.analyzeMmr(myMmr,resultEntity.getWiningRate(),resultEntity.getWins()+resultEntity.getLosses());
            String temp[] = Tier.getTierNameByMmr(analyzeMmr).split(" ");
            String temp2[] = Tier.getTierNameEngMmr(analyzeMmr).split(" ");
            predictTierKor = temp[0];
            predictTierEng = temp2[0];
            predictDivision = temp[1];
            mmrDto = mmrDto.of(myMmr,myTierKor,myTierEng,myDivision,analyzeMmr,predictTierKor,predictTierEng,predictDivision);
        }
        return ResponseDto.ofSuccess(mmrDto,"성공");
    }

    public ResponseDto challengerList() throws StringNotFoundException,InterruptedException{
        LeagueDto leagueDto = null;
        League league;

        league = leagueApiManager.getChallengerLeague(Region.KR);
        leagueDto = LeagueDto.of(league);

        return ResponseDto.ofSuccess(leagueDto,"성공");
    }

    public ResponseDto masterList() throws StringNotFoundException,InterruptedException{
        LeagueDto leagueDto = null;
        League league;

        league = leagueApiManager.getMasterLeague(Region.KR);
        leagueDto = LeagueDto.of(league);

        return ResponseDto.ofSuccess(leagueDto,"성공");
    }

    public SummonerEntity firstAccess(SummonerEntity summonerEntity,String name) throws StringNotFoundException,InterruptedException{
        if(summonerEntity==null){ //최초 소환사 요청이 오면, 저장한다.
            Summoner summoner = summonerApiManager.getSummonerByName(Region.KR, name);
            System.out.println(summoner.getName());
            summonerRepository.save(SummonerEntity.of(summoner));
            summonerEntity = summonerRepository.findByName(name);
        }
        return summonerEntity;
    }
}