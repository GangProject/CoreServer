package com.gang.domain.AggregateStats;

import com.gang.domain.ChampionStats.ChampionStatsEntity;
import lombok.*;
import net.rithms.riot.dto.Stats.AggregatedStats;

import javax.persistence.*;

/**
 * Created by Junwoo on 2017-04-27.
 */
@Entity
@Getter
@Setter
@Data
@Table( name = "AggregateStatsEntity")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AggregateStatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "totalSessionPlayed")
    private int totalSessionPlayed; //플레이 횟수

    @Column(name = "totalSessionWon")
    private int totalSessionWon; //이긴 횟수

    @Column(name = "totalSessionLost")
    private int totalSessionsLost; //진 횟수

    @Column(name = "totalChampionKills")
    private int totalChampionKills;

    @Column(name = "totalAssists")
    private int totalAssists;

    @Column(name = "totalDeathsPerSession")
    private int totalDeathPerSession;

    @Column(name = "totalMinionKills")
    private int totalMinionKills;

    @OneToOne
    @JoinColumn(name="id",insertable = false,updatable = false)
    private ChampionStatsEntity championStatsEntity;

    public static AggregateStatsEntity of(AggregatedStats aggregatedStats){
        return AggregateStatsEntity.builder().
                totalSessionPlayed(aggregatedStats.getTotalSessionsPlayed())
                .totalSessionWon(aggregatedStats.getTotalSessionsWon())
                .totalSessionsLost(aggregatedStats.getTotalSessionsLost())
                .totalChampionKills(aggregatedStats.getTotalChampionKills())
                .totalAssists(aggregatedStats.getTotalAssists())
                .totalDeathPerSession(aggregatedStats.getTotalDeathsPerSession())
                .totalMinionKills(aggregatedStats.getTotalMinionKills())
                .build();
    }
}
