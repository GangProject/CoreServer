package com.gang.domain.AggregateStats;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

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
    private int win;
    private int lost;
    private double winningRate;
    private double avgKill;
    private double avgDeath;
    private double avgAssist;
    private int played;
    private double avgCs;
    private String tier;
    //추가
    private double avgGoldEarned;
    private int totalMaxChampionsKilled;
    private int totalMaxNumDeaths;
    private double avgDamageDealt;
    private int totalDoubleKills;
    private int totalTripleKills;
    private int totalQuadraKills;
    private int totalPentaKills;


    public static AggregateStatsDto of(AggregateStatsEntity ase,int id,String name){
        double kda = (double)(ase.getTotalChampionKills()+ase.getTotalAssists())/ase.getTotalDeathPerSession();
        double winningRate = ((double)ase.getTotalSessionWon()/ase.getTotalSessionPlayed())*100.0;
        double avgKill = (double)ase.getTotalChampionKills()/ase.getTotalSessionPlayed();
        double avgDeath = (double)ase.getTotalDeathPerSession()/ase.getTotalSessionPlayed();
        double avgAssist = (double)ase.getTotalAssists()/ase.getTotalSessionPlayed();
        double avgCs = ase.getTotalMinionKills()/ase.getTotalSessionPlayed();

        double avgGoldEarned = (double)ase.getTotalGoldEarned()/ase.getTotalSessionPlayed();
        double avgDamageDealt = (double)ase.getTotalDamageDealt()/ase.getTotalSessionPlayed();

        kda = Double.parseDouble(String.format("%.2f",kda));
        winningRate = Double.parseDouble(String.format("%.2f",winningRate));
        avgKill = Double.parseDouble(String.format("%.2f",avgKill));
        avgDeath = Double.parseDouble(String.format("%.2f",avgDeath));
        avgAssist = Double.parseDouble(String.format("%.2f",avgAssist));
        avgCs = Double.parseDouble(String.format("%.2f",avgCs));

        avgGoldEarned = Double.parseDouble(String.format("%.2f",avgGoldEarned));
        avgDamageDealt = Double.parseDouble(String.format("%.2f",avgDamageDealt));

        return AggregateStatsDto.builder()
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
                .avgGoldEarned(avgGoldEarned)
                .totalMaxChampionsKilled(ase.getTotalMaxChampionsKilled())
                .totalMaxNumDeaths(ase.getTotalMaxNumDeaths())
                .avgDamageDealt(avgDamageDealt)
                .totalDoubleKills(ase.getTotalDoubleKills())
                .totalTripleKills(ase.getTotalTripleKills())
                .totalQuadraKills(ase.getTotalQuadraKills())
                .totalPentaKills(ase.getTotalPentaKills())
                .build();
    }

    public int compareTo(AggregateStatsDto aggregateStatsDto){
        return played<aggregateStatsDto.played? 1:-1;
    }
}
