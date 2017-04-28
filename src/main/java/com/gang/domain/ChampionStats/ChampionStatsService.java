package com.gang.domain.ChampionStats;

import com.gang.domain.AggregateStats.AggregateExpertsDto;
import com.gang.domain.AggregateStats.AggregateStatsDto;
import com.gang.domain.AggregateStats.AggregateStatsEntity;
import com.gang.domain.Champion.ChampionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Junwoo on 2017-04-28.
 */
@Service
public class ChampionStatsService {
    @Autowired
    private ChampionStatsRepository championStatsRepository;

    @Autowired
    private ChampionEntityRepository championEntityRepository;

    /*
    챔피언 별 장인 순위.. 승률에 따른 랭크를 넘겨주려고 함.
     */
    public List<AggregateExpertsDto> expertList(String name){
        int championId = championEntityRepository.findByName(name).getChampid();
        List<AggregateExpertsDto> experts = new LinkedList<AggregateExpertsDto>();
        List<ChampionStatsEntity> all = championStatsRepository.findByChampionId(championId);

        /*
          아직 db 에 챔피언을 저장해놓지 않았으므로 .. 챔피언 이름은 다시 수정할 것.
         */
        for (ChampionStatsEntity c : all){ //모두 같은 챔피언들을 갖고, KDA별로 정렬함.
            int champId = c.getChampionId();

            try {
                experts.add(AggregateExpertsDto.of(c.getStats(), champId, "피즈"));
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        Collections.sort(experts);

        return experts;
    }
}
