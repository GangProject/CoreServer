package com.gang.domain.Player;

import com.gang.domain.RecentGame.GameEntity;
import lombok.*;
import net.rithms.riot.dto.Game.Game;
import net.rithms.riot.dto.Game.Player;

import javax.persistence.*;
import java.util.PriorityQueue;

/**
 * Created by seungki on 2017-04-17.
 */
@Entity
@Data
@Table(name = "PlayerEntity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PlayerEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "gameid")
    private long gameid;

    @Column(name = "teamid")
    private int teamid;

    @Column(name ="championId")
    private int championid;

    @Column(name = "playerId")
    private long playerid;

    @Column(name="playerName")
    private String playername;



    public static PlayerEntity of(Game g, Player p,String name){
        return builder()
                .gameid(g.getGameId())
                .championid(p.getChampionId())
                .playerid(p.getSummonerId())
                .teamid(p.getTeamId())
                .playername(name)
                .build();
    }
    public static PlayerEntity ofMy(Game g,String name,long id){
        return builder()
                .gameid(g.getGameId())
                .championid(g.getChampionId())
                .playerid(id)
                .playername(name)
                .teamid(g.getTeamId())
                .build();
    }
}
