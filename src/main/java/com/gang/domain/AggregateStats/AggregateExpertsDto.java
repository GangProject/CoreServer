package com.gang.domain.AggregateStats;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2017-04-28.
 */
@Builder
@Getter
@Setter
public class AggregateExpertsDto implements Comparable<AggregateExpertsDto>{
    private int id;
    private String name;
    private double kda;
    private int win;
    private int lost;
    private double winningRate;
    private double avgKill;
    private double avgDeath;
    private double avgAssist;
    private int played;
    private double avgCs;

    public static AggregateExpertsDto of(AggregateStatsEntity ase,int id,String name){
        double kda = (double)(ase.getTotalChampionKills()+ase.getTotalAssists())/ase.getTotalDeathPerSession();
        double winningRate = ((double)ase.getTotalSessionWon()/ase.getTotalSessionPlayed())*100.0;
        double avgKill = (double)ase.getTotalChampionKills()/ase.getTotalSessionPlayed();
        double avgDeath = (double)ase.getTotalDeathPerSession()/ase.getTotalSessionPlayed();
        double avgAssist = (double)ase.getTotalAssists()/ase.getTotalSessionPlayed();
        double avgCs = ase.getTotalMinionKills()/ase.getTotalSessionPlayed();

        return AggregateExpertsDto.builder()
                .id(id)
                .name(name)
                .kda(kda)
                .win(ase.getTotalSessionWon())
                .lost(ase.getTotalSessionsLost())
                .winningRate(winningRate)
                .avgKill(avgKill)
                .avgDeath(avgDeath)
                .avgAssist(avgAssist)
                .avgCs(avgCs)
                .played(ase.getTotalSessionPlayed())
                .build();
    }

    public int compareTo(AggregateExpertsDto aggregateExpertsDto){
        return kda<aggregateExpertsDto.kda? 1:-1;
    }
}
