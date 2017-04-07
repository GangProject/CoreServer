package com.gang.core.manager;

import com.gang.core.RiotApiManager;
import com.gang.core.StringNotFoundException;
import net.rithms.riot.api.RateLimitException;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.dto.League.League;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Junwoo on 2017-04-07.
 */
@Component
public class LeagueApiManager extends RiotApiManager{

    public LeagueApiManager() throws Exception{
        super();
    }

    public List<League> getLeagueBySummoner(long summonerId) throws StringNotFoundException,InterruptedException{
        List<League> leagues = null;
        Boolean success = false;

        while(!success){ //key 횟수 초과될 수 있으므로 , 돌리기용.
            System.out.println(key.toString());
            try {
                System.out.print(summonerId);
                leagues = api.getLeagueEntryBySummoner(summonerId);
                success = true;
            } catch (RateLimitException e) { //key 요청 횟수 초과시
                System.out.println("key 바꿈");
                changeKey();
                Thread.sleep(1000); //1초 동안 sleep
            } catch (RiotApiException e) {
                System.out.println(e);
            }
        }

        return leagues;
    }
}
