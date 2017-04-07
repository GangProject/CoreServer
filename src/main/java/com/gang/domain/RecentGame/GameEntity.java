package com.gang.domain.RecentGame;

import com.gang.domain.Champion.ChampionEntity;
import lombok.*;
import net.rithms.riot.dto.Game.Game;

import javax.persistence.*;

/**
 * Created by seungki on 2017-04-07.
 */
@Getter
@Setter
@Table(name="GameEntity")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "summoner_id")
    private long summonerid;

    @Column(name="game_id")
    private long gameid;

    @Column(name = "createDate")
    private long createDate;

    @Column(name="game_mode")
    private String gameMode;

    @Column(name = "championId")
    private String champion;

    @Column(name = "mapId")
    private int map;

    @Column(name = "subType")
    private String subType;

    @Column(name = "teamId")
    private int teamId;



    public static GameEntity of(Game game, Long id, ChampionEntity championEntity){
        return GameEntity.builder()
                .gameid(game.getGameId())
                .gameMode(game.getGameMode())
                .subType(game.getSubType())
                .createDate(game.getCreateDate())
                .map(game.getMapId())
                .teamId(game.getTeamId())
                .summonerid(id)
                .champion(championEntity.getName())
                .build();
    }


}
