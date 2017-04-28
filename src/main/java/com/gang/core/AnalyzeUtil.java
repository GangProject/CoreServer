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
}
