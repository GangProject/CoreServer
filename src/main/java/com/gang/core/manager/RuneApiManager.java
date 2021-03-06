package com.gang.core.manager;

import com.gang.core.RiotApiManager;
import com.gang.core.StringNotFoundException;
import net.rithms.riot.api.RateLimitException;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.dto.Static.RuneList;
import net.rithms.riot.dto.Summoner.RunePage;
import net.rithms.riot.dto.Summoner.RunePages;
import org.springframework.stereotype.Component;

import javax.swing.plaf.synth.Region;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by seungki on 2017-04-28.
 */
@Component
public class RuneApiManager extends RiotApiManager {
    public RuneApiManager() throws Exception{
        super();
    }

    public RuneList getRune() throws StringNotFoundException,InterruptedException{
        RuneList runeList = null;
        Boolean success = false;

        while(!success){ //key 횟수 초과될 수 있으므로 , 돌리기용.
            System.out.println(key.toString());
            try {
                runeList = api.getDataRuneList(net.rithms.riot.constant.Region.KR);
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


        return runeList;
    }
    public Map<String, RunePages> getRune(String id) throws StringNotFoundException,InterruptedException{
        Map<String,RunePages> runePage =null;
        Boolean success = false;

        while(!success){ //key 횟수 초과될 수 있으므로 , 돌리기용.
            System.out.println(key.toString());
            try {
                runePage = api.getRunePages(net.rithms.riot.constant.Region.KR,id);
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


        return runePage;
    }


}
