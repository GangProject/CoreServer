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

    public static GamePlayer ofParty(Participant p, ParticipantIdentity p2, String name, HashMap<String,String> spell, HashMap<String,String> item
    ){
        return GamePlayer.builder()
                .teamId(p.getTeamId())
                .summonerid(p2.getPlayer().getSummonerId())
                .champion(name)
                .spell1(spell.get("spell1"))
                .spell2(spell.get("spell2"))
                .item0(item.get("item0"))
                .item1(item.get("item1"))
                .item2(item.get("item2"))
                .item3(item.get("item3"))
                .item4(item.get("item4"))
                .item5(item.get("item5"))
                .item6(item.get("item6"))
                .kill((int)p.getStats().getKills())
                .death((int)p.getStats().getDeaths())
                .assist((int)p.getStats().getAssists())
                .gold(p.getStats().getGoldEarned())
                .ward(p.getStats().getWardsPlaced())
                .damage(p.getStats().getTotalDamageDealtToChampions())
                .build();
    }


}

