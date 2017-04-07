package com.gang.core.manager;

import com.gang.core.RiotApiManager;
import net.rithms.riot.api.RateLimitException;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Game.RecentGames;
import org.springframework.stereotype.Component;

/**
 * Created by Seungi on 2017-04-07.
 */
@Component
public class GameApiManager extends RiotApiManager {

    public GameApiManager() throws Exception{
        super();
    }

    public RecentGames getRecentGames(Region region, long id) throws Exception{
        RecentGames recentGames=null;
        Boolean success = false;
        while(!success){ //key 횟수 초과될 수 있으므로 , 돌리기용.
            System.out.println(key.toString());
            try {
                recentGames = api.getRecentGames(Region.KR,id);
                success = true;
            } catch (RateLimitException e) { //key 요청 횟수 초과시
                System.out.println("key 바꿈");
                changeKey();
                Thread.sleep(1000); //1초 동안 sleep
            } catch (RiotApiException e) {
                System.out.println(e);
            }
        }

        return recentGames;
    }
}
