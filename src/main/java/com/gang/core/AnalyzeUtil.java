package com.gang.core;

import java.util.LinkedList;
import java.util.List;

import com.gang.domain.result.ResultEntity;
import com.gang.domain.summoner.SummonerEntity;
import net.rithms.riot.dto.League.League;
import net.rithms.riot.dto.Summoner.Summoner;
import org.springframework.stereotype.Component;

/**
 * Created by Junwoo & Seungi on 2017-04-07.
 * 나중에 ~ league , matchlist 등을 받아서, 분석하여 우리가 만든 객체에 저장해서 반환해줄 클래스임..
 */
@Component
public class AnalyzeUtil {
    // 여러 것들을 생성할 것임 차차..

    public List<ResultEntity> analyzeExcute(SummonerEntity summonerEntity, List<League> leagues){
        List<ResultEntity> list = new LinkedList<ResultEntity>();

        League leagueSolo = leagues.get(0); //솔로랭크만 테스트해보았다.
        League leagueFree = leagues.get(1); //자유 랭크

        ResultEntity resultSoloEntity = ResultEntity.of(summonerEntity,leagueSolo);
        ResultEntity resultFreeEntity = ResultEntity.of(summonerEntity,leagueFree);

        list.add(resultSoloEntity);
        list.add(resultFreeEntity);

        return list;
    }
    /*
     * 최소 10게임 이상일때만 적용시킴. 아니면 그냥 자기 티어(챔프별로)
       ..이상 +400 적용
       70% > 400
       65% > 300
       60% > 200
       55% > 100
       50% > 0
       45% > -100
       40% > -200
       35% > -300
       30% > -400
       ... 같게 적용(-400)
     */
    public int analyzeMmr(int mmr,double winningRate,int played){
        if(played<10) return mmr; //10게임 미만이면 그냥 반환.

        if(winningRate>=70){
            mmr = mmr + 400;
        }else if(winningRate>=65){
            mmr = mmr + 300;
        }else if(winningRate>=60){
            mmr = mmr + 200;
        }else if(winningRate>=55){
            mmr = mmr + 100;
        }else if(winningRate>=50){
            mmr = mmr;
        }else if(winningRate>=45){
            mmr = mmr - 100;
        }else if(winningRate>=40){
            mmr = mmr - 200;
        }else if(winningRate>=35){
            mmr = mmr - 300;
        }else{
            mmr = mmr-400;
        }

        return mmr;
    }


}
