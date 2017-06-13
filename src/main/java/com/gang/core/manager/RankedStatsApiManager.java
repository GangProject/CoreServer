package com.gang.core.manager;

import com.gang.core.RiotApiManager;
import com.gang.core.StringNotFoundException;
import com.gang.domain.RankedStats.RankedStatsEntity;
import net.rithms.riot.api.RateLimitException;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Region;
import net.rithms.riot.constant.Season;
import net.rithms.riot.dto.Stats.RankedStats;
import org.springframework.stereotype.Component;

/**
 * Created by Jnwoo on 2017-04-27.
 */
@Component
public class RankedStatsApiManager extends RiotApiManager{

    public RankedStatsApiManager() throws Exception{
        super();
    }

    public RankedStatsEntity getRankedStatsById(Region region, long summonerId) throws StringNotFoundException,InterruptedException{
        RankedStats rankedStats = null;
        Boolean success = false;
        RankedStatsEntity rankedStatsEntity = null;

        while(!success){ //key 횟수 초과될 수 있으므로 , 돌리기용.
            System.out.println(key.toString());
            try {
                rankedStats = api.getRankedStats(Region.KR, summonerId);
                success = true;
            } catch (RateLimitException e) { //key 요청 횟수 초과시
                System.out.println("key 바꿈");
                changeKey();
                Thread.sleep(1000); //1초 동안 sleep
            } catch (RiotApiException e) {
                System.out.println(e);
                changeKey();
                Thread.sleep(1000);
            }
        }
        rankedStatsEntity = RankedStatsEntity.of(rankedStats);

        return rankedStatsEntity;
    }

}
