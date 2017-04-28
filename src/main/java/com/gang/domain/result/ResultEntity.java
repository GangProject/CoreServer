package com.gang.domain.result;

import com.gang.api.common.Dto;
import com.gang.domain.summoner.SummonerEntity;
import lombok.*;
import net.rithms.riot.dto.League.League;
import net.rithms.riot.dto.Summoner.Summoner;

import javax.persistence.*;

/**
 * Created by Junwoo on 2017-04-07.
 */
@Entity
@Getter
@Setter
@Data
@Table( name = "ResultEntity")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity implements Dto{
    /**
     *  연습용 result 임.. 미래에 회의를 통해 다시 구성할 예정..
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "summonerId")
    private long summonerId;

    @Column(name = "name")
    private String name;

    @Column(name ="winingRate")
    private double winingRate;

    @Column(name ="tier")
    private String tier;

    @Column(name = "division")
    private String division;

    @Column(name = "wins")
    private int wins;

    @Column(name ="losses")
    private int losses;

    @Column(name = "leaguePoints")
    private int leaguePoints;

    public static ResultEntity of(SummonerEntity summonerEntity, League league){
        double wins = league.getEntries().get(0).getWins();
        double losses = league.getEntries().get(0).getLosses();
        double winningRate = (wins/(wins+losses))*100;

        return ResultEntity.builder()
                .summonerId(summonerEntity.getSummonerId())
                .name(summonerEntity.getName())
                .winingRate(winningRate)
                .tier(league.getTier())
                .division(league.getEntries().get(0).getDivision())
                .wins((int)wins)
                .losses((int)losses)
                .leaguePoints(league.getEntries().get(0).getLeaguePoints())
                .build();
    }

    public static ResultEntity test(long summonerId,String name,String tier,String division,int wins,int losses,int leaguePoints){
        double winningRate = (wins/(wins+losses))*100;

        return ResultEntity.builder()
                .summonerId(summonerId)
                .name(name)
                .winingRate(winningRate)
                .tier(tier)
                .division(division)
                .wins(wins)
                .losses(losses)
                .leaguePoints(leaguePoints)
                .build();
    }

}
