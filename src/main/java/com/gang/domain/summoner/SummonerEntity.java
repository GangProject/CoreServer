package com.gang.domain.summoner;

import com.gang.api.common.Dto;
import lombok.*;
import net.rithms.riot.dto.Summoner.Summoner;

import javax.persistence.*;

/**
 * Created by Junwoo on 2017-04-07.
 */
@Entity
@Getter
@Setter
@Data
@Table( name = "SummonerEntity")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SummonerEntity implements Dto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "summonerId")
    private int summonerId;

    @Column(name = "name")
    private String name;

    @Column(name = "profileIconId")
    private int profileIconId;

    @Column(name = "revisionDate")
    private long revisionDate;

    @Column(name = "summonerLevel")
    private long summonerLevel;

    public static SummonerEntity of(Summoner summoner){
        return SummonerEntity.builder()
                .summonerId((int)summoner.getId())
                .name(summoner.getName())
                .profileIconId(summoner.getProfileIconId())
                .summonerLevel(summoner.getSummonerLevel())
                .revisionDate(summoner.getRevisionDate())
                .build();
    }

    public static SummonerEntity test(int summonerId,String name,int profileIconId,long revisionDate,long summonerLevel){
        return SummonerEntity.builder()
                .summonerId(summonerId)
                .name(name)
                .profileIconId(profileIconId)
                .summonerLevel(revisionDate)
                .revisionDate(summonerLevel)
                .build();
    }
}
