package com.gang.domain.RecentGame;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import net.rithms.riot.dto.Match.Participant;
import net.rithms.riot.dto.Match.ParticipantIdentity;


import java.util.HashMap;


/**
 * Created by seungki on 2017-04-27.
 */
@Builder
@Getter
@Setter
public class GamePlayer {
    private long summonerid;
    private long gameid;
    private String name;
    private String createDate;
    private String gameMode;
    private String champion;
    private int map;
    private String subType;
    private int teamId;
    private String spell1;
    private String spell2;
    private String item0;
    private String item1;
    private String item2;
    private String item3;
    private String item4;
    private String item5;
    private String item6;
    private  int kill;
    private  int death;
    private  int assist;
    private long damage;
    private long ward;
    private long gold;
    private long cs;
    private String tier;
    private long pinkward;
    private long level;
    private double kdaPoint;
    private long deleteward;



    public static GamePlayer ofParty(double kda,Participant p, ParticipantIdentity p2, String name, HashMap<String,String> spell, HashMap<String,String> item
    ,String tier){
        return GamePlayer.builder()
                .teamId(p.getTeamId())
                .summonerid(p2.getPlayer().getSummonerId())
                .champion(name)
                .name(p2.getPlayer().getSummonerName())
                .spell1(spell.get("spell1"))
                .spell2(spell.get("spell2"))
                .item0(item.get("Item0"))
                .item1(item.get("Item1"))
                .item2(item.get("Item2"))
                .item3(item.get("Item3"))
                .item4(item.get("Item4"))
                .item5(item.get("Item5"))
                .item6(item.get("Item6"))
                .kill((int)p.getStats().getKills())
                .death((int)p.getStats().getDeaths())
                .assist((int)p.getStats().getAssists())
                .gold(p.getStats().getGoldEarned())
                .ward(p.getStats().getWardsPlaced())
                .damage(p.getStats().getTotalDamageDealtToChampions())
                .tier(tier)
                .cs(p.getStats().getMinionsKilled()+p.getStats().getNeutralMinionsKilled())
                .pinkward(p.getStats().getVisionWardsBoughtInGame())
                .level(p.getStats().getChampLevel())
                .kdaPoint(kda)
                .deleteward(p.getStats().getWardsKilled())
                .build();
    }


}

