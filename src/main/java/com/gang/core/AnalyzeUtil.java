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
    public int analyzeMmr(double kda, int mmr,double winningRate,int played){
        if(played<10) return mmr; //10게임 미만이면 그냥 반환.

        if(winningRate>=70){
            mmr = mmr + 400;
        }else if(winningRate>=69){
            mmr = mmr + 380;
        }else if(winningRate>=68){
            mmr = mmr + 360;
        }else if(winningRate>=67){
            mmr = mmr + 340;
        }else if(winningRate>=66){
            mmr = mmr + 320;
        }else if(winningRate>=65){
            mmr = mmr + 300;
        }else if(winningRate>=64){
            mmr = mmr + 280;
        }else if(winningRate>=63){
            mmr = mmr + 260;
        }else if(winningRate>=62){
            mmr = mmr + 240;
        }else if(winningRate>=61){
            mmr = mmr + 220;
        }else if(winningRate>=60){
            mmr = mmr + 200;
        }else if(winningRate>=59){
            mmr = mmr + 180;
        }else if(winningRate>=58){
            mmr = mmr + 160;
        }else if(winningRate>=57){
            mmr = mmr + 140;
        }else if(winningRate>=56){
            mmr = mmr + 120;
        }else if(winningRate>=55){
            mmr = mmr + 100;
        }else if(winningRate>=54){
            mmr = mmr + 80;
        }else if(winningRate>=53){
            mmr = mmr + 60;
        }else if(winningRate>=52){
            mmr = mmr + 40;
        }else if(winningRate>=51){
            mmr = mmr + 20;
        }else if(winningRate>=50){
            mmr = mmr;
        }else if(winningRate>=49){
            mmr = mmr - 20;
        }else if(winningRate>=48){
            mmr = mmr -40;
        }else if(winningRate>=47){
            mmr = mmr - 60;
        }else if(winningRate>=46){
            mmr = mmr - 80;
        }else if(winningRate>=45){
            mmr = mmr - 100;
        }else if(winningRate>=44){
            mmr = mmr - 120;
        }else if(winningRate>=43){
            mmr = mmr - 140;
        }else if(winningRate>=42){
            mmr = mmr - 160;
        }else if(winningRate>=41){
            mmr = mmr - 180;
        }else if(winningRate>=40){
            mmr = mmr - 200;
        }else if(winningRate>=39){
            mmr = mmr - 220;
        }else if(winningRate>=38){
            mmr = mmr - 240;
        }else if(winningRate>=37){
            mmr = mmr - 260;
        }else if(winningRate>=36){
            mmr = mmr - 280;
        }else if(winningRate>=35){
            mmr = mmr - 300;
        }else{
            mmr = mmr-400;
        }

        if(kda>=4.5){
            mmr = mmr + 103;
        }else if(kda>=4.4){
            mmr = mmr + 95;
        }else if(kda>=4.3){
            mmr = mmr + 90;
        }else if(kda>=4.2){
            mmr = mmr +85;
        }else if(kda>=4.1){
            mmr = mmr + 83;
        }else if(kda>=4.0){
            mmr = mmr + 77;
        }else if(kda>=3.9){
            mmr = mmr + 74;
        }else if(kda>=3.8){
            mmr = mmr + 72;
        }else if(kda>=3.7){
            mmr = mmr + 65;
        }else if(kda>=3.6){
            mmr = mmr + 63;
        }else if(kda>=3.5){
            mmr = mmr + 58;
        }else if(kda>=3.4){
            mmr = mmr + 52;
        }else if(kda>=3.3){
            mmr = mmr + 47;
        }else if(kda>=3.2){
            mmr = mmr + 41;
        }else if(kda>=3.1){
            mmr = mmr + 36;
        }else if(kda>=3.0){
            mmr = mmr + 31;
        }else if(kda>=2.9){
            mmr = mmr + 26;
        }else if(kda>=2.8){
            mmr = mmr + 21;
        }else if(kda>=2.7){
            mmr = mmr + 16;
        }else if(kda>=2.6){
            mmr = mmr + 8;
        }else if(kda>=2.5){

        }else if(kda>=2.4){
            mmr = mmr - 8;
        }else if(kda>=2.3){
            mmr = mmr - 16;
        }else if(kda>=2.2){
            mmr = mmr - 21;
        }else if(kda>=2.1){
            mmr = mmr - 26;
        }else if(kda>=2.0){
            mmr = mmr - 33;
        }else if(kda>=1.9){
            mmr = mmr - 41;
        }else if(kda>=1.8){
            mmr = mmr - 48;
        }else if(kda>=1.7){
            mmr = mmr - 57;
        }else if(kda>=1.6){
            mmr = mmr - 75;
        }else if(kda>=1.5){
            mmr = mmr - 83;
        }else{
            mmr = mmr - 103;
        }

        return mmr;
    }


}
