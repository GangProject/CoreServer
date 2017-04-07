package com.gang.domain.result;

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
public class ResultEntity {
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


    public static ResultEntity of(Summoner summoner,League league){
        return ResultEntity.builder()
                .summonerId(summoner.getId())
                .name(summoner.getName())
                .build();
    }
}
