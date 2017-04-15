package com.gang.domain.RecentGame;

import com.gang.domain.Champion.ChampionEntity;
import com.gang.domain.Spell.SpellEntity;
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

    @Column(name = "spell1")
    private String spell1;

    @Column(name = "spell2")
    private String spell2;

    public static GameEntity of(Game game, Long id, ChampionEntity championEntity, SpellEntity spellEntity1,SpellEntity spellEntity2){
        return GameEntity.builder()
                .gameid(game.getGameId())
                .gameMode(game.getGameMode())
                .subType(game.getSubType())
                .createDate(game.getCreateDate())
                .map(game.getMapId())
                .teamId(game.getTeamId())
                .summonerid(id)
                .champion(championEntity.getName())
                .spell1(spellEntity1.getName())
                .spell2(spellEntity2.getName())
                .build();
    }


}
