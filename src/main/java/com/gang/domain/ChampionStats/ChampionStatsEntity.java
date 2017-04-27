package com.gang.domain.ChampionStats;

import com.gang.domain.AggregateStats.AggregateStatsEntity;
import com.gang.domain.RankedStats.RankedStatsEntity;
import lombok.*;
import net.rithms.riot.dto.Stats.ChampionStats;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Junwoo on 2017-04-27.
 */
@Entity
@Getter
@Setter
@Data
@Table( name = "ChampionStatsEntity")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChampionStatsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "championId")
    private int championId;

    @ManyToOne
    @JoinColumn(name="id",insertable = false,updatable = false)
    private RankedStatsEntity rankedStatsEntity;

    @OneToOne(mappedBy="championStatsEntity")
    private AggregateStatsEntity stats;

    public static ChampionStatsEntity of(ChampionStats championStats){
        return ChampionStatsEntity.builder()
                .championId(championStats.getId())
                .stats(AggregateStatsEntity.of(championStats.getStats()))
                .build();
    }
}
