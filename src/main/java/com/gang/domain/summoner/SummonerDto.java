package com.gang.domain.summoner;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Junwoo on 2017-04-03.
 */
@Getter
@Setter
@Builder
public class SummonerDto {
    private long id;
    private int profileIconId;
    private String name;
    private long summonerLevel;
    private long revisionDate;

    public static SummonerDto of(Summoner summoner){
        return SummonerDto.builder()
                .id(summoner.getId())
                .profileIconId(summoner.getProfileIconId())
                .name(summoner.getName())
                .summonerLevel(summoner.getSummonerLevel())
                .revisionDate(summoner.getRevisionDate())
                .build();
    }
}
