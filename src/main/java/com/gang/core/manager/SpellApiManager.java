package com.gang.core.manager;

import com.gang.core.RiotApiManager;
import com.gang.core.StringNotFoundException;
import net.rithms.riot.api.RateLimitException;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Static.SummonerSpellList;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by seungki on 2017-04-15.
 */
@Component
public class SpellApiManager extends RiotApiManager {
    public SpellApiManager() throws Exception{
        super();
    }
    public SummonerSpellList getDataSummonerSpellList(Region region) throws StringNotFoundException,InterruptedException{
        SummonerSpellList spell = null;
        Boolean success = false;

        while(!success){ //key 횟수 초과될 수 있으므로 , 돌리기용.
            System.out.println(key.toString());
            try {
                spell=api.getDataSummonerSpellList(region);
                success = true;
            } catch (RateLimitException e) { //key 요청 횟수 초과시
                System.out.println("key 바꿈");
                changeKey();
                Thread.sleep(1000); //1초 동안 sleep
            } catch (RiotApiException e) {
                System.out.println(e);
            }
        }

        return spell;
    }
}
