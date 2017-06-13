package com.gang.core.manager;

import com.gang.core.RiotApiManager;
import net.rithms.riot.api.RateLimitException;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Game.RecentGames;
import net.rithms.riot.dto.Match.MatchDetail;
import net.rithms.riot.dto.Static.MasteryList;
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
                changeKey();
                Thread.sleep(1000);
            }
        }

        return recentGames;
    }

    public MatchDetail getRecentGamesInfo(Region region, long id) throws Exception{
        MatchDetail matchDetail=null;
        Boolean success = false;
        while(!success){ //key 횟수 초과될 수 있으므로 , 돌리기용.
            System.out.println(key.toString());
            try {
                matchDetail = api.getMatch(Region.KR,id);
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

        return matchDetail;
    }
    public MasteryList getMastery(Region region) throws Exception{
        MasteryList masteryList=null;
        Boolean success = false;
        while(!success){ //key 횟수 초과될 수 있으므로 , 돌리기용.
            System.out.println(key.toString());
            try {
                masteryList = api.getDataMasteryList(Region.KR);
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

        return masteryList;
    }

}
