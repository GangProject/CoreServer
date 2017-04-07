package com.gang.core;

import java.util.List;
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

    public String analyzeExcute(Summoner summoner, List<League> leagues){
        League league = leagues.get(0); //솔로랭크만 테스트해보았다.

        System.out.println(league.getTier());
        System.out.println(league.getEntries());
        System.out.println(league.getEntries().get(0).getDivision());
        System.out.println(league.getEntries().get(0).getWins());
        System.out.println(league.getEntries().get(0).getLosses());

        System.out.println(summoner.getName());
        System.out.println(summoner.getId());

        return "ㅋㅋ";
    }


}
