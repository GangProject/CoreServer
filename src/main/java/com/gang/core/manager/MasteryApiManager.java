package com.gang.core.manager;

import com.gang.core.RiotApiManager;
import com.gang.core.StringNotFoundException;
import net.rithms.riot.api.RateLimitException;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.League.League;
import net.rithms.riot.dto.Static.Mastery;
import net.rithms.riot.dto.Static.MasteryList;
import net.rithms.riot.dto.Summoner.MasteryPages;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by seungki on 2017-06-15.
 */
@Component
public class MasteryApiManager extends RiotApiManager {

    public MasteryApiManager() throws Exception{
        super();
    }

    public MasteryPages masteryPages(long summonerId) throws StringNotFoundException,InterruptedException{
        MasteryPages pages = null;
        Boolean success = false;

        while(!success){ //key 횟수 초과될 수 있으므로 , 돌리기용.
            System.out.println(key.toString());
            try {
                pages = api.getMasteryPages(Region.KR,summonerId);

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

        return pages;
    }

    public MasteryList masteryPages(int id) throws StringNotFoundException,InterruptedException{
        MasteryList masterylIST = null;
        Boolean success = false;

        while(!success){ //key 횟수 초과될 수 있으므로 , 돌리기용.
            System.out.println(key.toString());
            try {
                masterylIST = api.getDataMasteryList(Region.KR);
                System.out.print("");
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

        return masterylIST;
    }

}
