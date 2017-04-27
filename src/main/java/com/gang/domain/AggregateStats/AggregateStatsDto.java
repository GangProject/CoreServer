package com.gang.domain.AggregateStats;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Junwoo on 2017-04-27.
 */
@Builder
@Getter
@Setter
public class AggregateStatsDto implements Comparable<AggregateStatsDto>{
    private int id;
    private String name;
    private double kda;
    private double winningRate;
    private double avgKill;
    private double avgDeath;
    private double avgAssist;
    private int played;
    private double avgCs;

    public static AggregateStatsDto of(AggregateStatsEntity ase,int id,String name){
        double kda = (double)(ase.getTotalChampionKills()+ase.getTotalAssists())/ase.getTotalDeathPerSession();
        double winningRate = ((double)ase.getTotalSessionWon()/ase.getTotalSessionPlayed())*100.0;
        double avgKill = (double)ase.getTotalChampionKills()/ase.getTotalSessionPlayed();
        double avgDeath = (double)ase.getTotalDeathPerSession()/ase.getTotalSessionPlayed();
        double avgAssist = (double)ase.getTotalAssists()/ase.getTotalSessionPlayed();
        double avgCs = ase.getTotalMinionKills()/ase.getTotalSessionPlayed();

        return AggregateStatsDto.builder()
                .id(id)
                .name(name)
                .kda(kda)
                .winningRate(winningRate)
                .avgKill(avgKill)
                .avgDeath(avgDeath)
                .avgAssist(avgAssist)
                .avgCs(avgCs)
                .played(ase.getTotalSessionPlayed())
                .build();
    }

    public int compareTo(AggregateStatsDto aggregateStatsDto){
        return played<aggregateStatsDto.played? 1:-1;
    }
}
