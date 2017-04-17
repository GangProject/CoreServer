package com.gang.domain.Player;

import lombok.*;
import net.rithms.riot.dto.Game.Game;
import net.rithms.riot.dto.Game.Player;

import javax.persistence.*;

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
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "gameid")
    private long gameid;

    @Column(name = "team")
    private int teamid;

    @Column(name ="championId")
    private int championid;

    @Column(name = "playerId")
    private long playerid;

    public static PlayerEntity of(Game g, Player p){
        return builder()
                .gameid(g.getGameId())
                .championid(p.getChampionId())
                .playerid(p.getSummonerId())
                .teamid(p.getTeamId())
                .build();
    }
}
