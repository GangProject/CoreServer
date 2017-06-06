package com.gang.domain.RecentGame;

import com.gang.domain.Champion.ChampionEntity;
import com.gang.domain.Player.PlayerEntity;
import com.gang.domain.Spell.SpellEntity;
import lombok.*;
import net.rithms.riot.dto.Game.Game;
import net.rithms.riot.dto.Match.Participant;
import net.rithms.riot.dto.Match.ParticipantIdentity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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
    private String createDate;

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

    @Column(name = "item0")
    private String item0;

    @Column(name = "item1")
    private String item1;

    @Column(name = "item2")
    private String item2;

    @Column(name = "item3")
    private String item3;

    @Column(name = "item4")
    private String item4;

    @Column(name = "item5")
    private String item5;

    @Column(name = "item6")
    private String item6;

    @Column(name = "gamekill")
    private  int kill;

    @Column(name = "gamedeath")
    private  int death;

    @Column(name = "gameassist")
    private  int assist;

    @Column(name = "gamedate")
    private  long date;

    @OneToMany(mappedBy = "Game")
    private List<PlayerEntity> p;

    @Transient
    private long damage;

    @Transient
    private long ward;

    @Transient
    private long gold;

    public static GameEntity of(Game game, String time,Long id, ChampionEntity championEntity, SpellEntity spellEntity1,SpellEntity spellEntity2,HashMap<String,String> gameItem
                                ){
        return GameEntity.builder()
                .gameid(game.getGameId())
                .gameMode(game.getGameMode())
                .subType(game.getSubType())
                .createDate(time)
                .date(game.getCreateDate())
                .map(game.getMapId())
                .teamId(game.getTeamId())
                .summonerid(id)
                .champion(championEntity.getName())
                .spell1(spellEntity1.getName())
                .spell2(spellEntity2.getName())
                .item0(gameItem.get("item0"))
                .item1(gameItem.get("item1"))
                .item2(gameItem.get("item2"))
                .item3(gameItem.get("item3"))
                .item4(gameItem.get("item4"))
                .item5(gameItem.get("item5"))
                .item6(gameItem.get("item6"))
                .kill(game.getStats().getChampionsKilled())
                .death(game.getStats().getNumDeaths())
                .assist(game.getStats().getAssists())
                .build();
    }



}
