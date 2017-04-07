package com.gang.core;

import com.gang.constant.Key;
import com.gang.domain.summoner.SummonerEntity;
import com.google.common.util.concurrent.RateLimiter;
import net.rithms.riot.api.RateLimitException;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Region;
import net.rithms.riot.constant.staticdata.ChampData;
import net.rithms.riot.dto.Game.RecentGames;
import net.rithms.riot.dto.League.League;
import net.rithms.riot.dto.Static.ChampionList;
import net.rithms.riot.dto.Static.ItemList;
import net.rithms.riot.dto.Summoner.Summoner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.io.InterruptedIOException;
import java.io.Serializable;

/**
 * Created by Junwoo & Seungi on 2017-04-07.
 */
@Component
public class RiotApiManager implements Serializable{
    private static final long serialVersionUID = -3301605591108950415L;

    protected RiotApi api = null;
    protected Key key = null;
    protected int currentKey;
    protected int keyLength;

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

    public ChampionList getDataChampionList(Region region,ChampData champData) throws StringNotFoundException,InterruptedException{
        ChampionList championList = null;
        Boolean success = false;

        while(!success){ //key 횟수 초과될 수 있으므로 , 돌리기용.
            System.out.println(key.toString());
            try {
                championList = api.getDataChampionList(Region.KR, "", "", false, ChampData.ALL);
                success = true;
            } catch (RateLimitException e) { //key 요청 횟수 초과시
                System.out.println("key 바꿈");
                changeKey();
                Thread.sleep(1000); //1초 동안 sleep
            } catch (RiotApiException e) {
                System.out.println(e);
            }
        }

        return championList;
    }
    public RecentGames getRecentGames(Region region,long id) throws Exception{
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
    public ItemList getDataItemList(Region region) throws Exception{
        ItemList itemList=null;
        Boolean success = false;
        while(!success){ //key 횟수 초과될 수 있으므로 , 돌리기용.
            System.out.println(key.toString());
            try {
                itemList = api.getDataItemList(Region.KR);
                success = true;
            } catch (RateLimitException e) { //key 요청 횟수 초과시
                System.out.println("key 바꿈");
                changeKey();
                Thread.sleep(1000); //1초 동안 sleep
            } catch (RiotApiException e) {
                System.out.println(e);
            }
        }

        return itemList;
    }
}
