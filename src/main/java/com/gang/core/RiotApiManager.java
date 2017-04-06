package com.gang.core;

import com.gang.constant.Key;
import com.gang.domain.summoner.SummonerEntity;
import com.google.common.util.concurrent.RateLimiter;
import net.rithms.riot.api.RateLimitException;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Static.ChampionList;
import net.rithms.riot.dto.Summoner.Summoner;
import org.springframework.stereotype.Component;

import java.io.InterruptedIOException;
import java.io.Serializable;

/**
 * Created by Junwoo & Seungi on 2017-04-07.
 */
@Component
public class RiotApiManager implements Serializable{
    private static final long serialVersionUID = -3301605591108950415L;

    private RiotApi api = null;
    private Key key = null;
    private int currentKey;
    private int keyLength;

    //최초 생성.
    public RiotApiManager() throws StringNotFoundException{
        api = new RiotApi();
        currentKey = 1;
        key = Key.getKeyById(currentKey);
        api.setKey(key.getKey());
        keyLength = Key.getLength();
    }

    //키를 바꿔주는 메소드
    public void changeKey() throws StringNotFoundException{
        currentKey = (currentKey+1)%keyLength; //키 인덱스 돌리고
        key = Key.getKeyById(currentKey); //key 구해서
        api.setKey(key.getKey()); //set Key !
    }

    public Summoner getSummonerByName(Region region, String name) throws StringNotFoundException,InterruptedException{
        Summoner summoner = null;
        Boolean success = false;

        while(!success){ //key 횟수 초과될 수 있으므로 , 돌리기용.
            System.out.println(key.toString());
            try {
                summoner = api.getSummonerByName(region, name);
                success = true;
            } catch (RateLimitException e) { //key 요청 횟수 초과시
                System.out.println("key 바꿈");
                changeKey();
                Thread.sleep(2000); //2초 동안 sleep
            } catch (RiotApiException e) {
                System.out.println(e);
            }
        }

        return summoner;
    }
}
