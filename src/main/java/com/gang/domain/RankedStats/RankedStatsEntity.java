package com.gang.domain.RankedStats;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import com.gang.api.common.Dto;
import com.gang.domain.ChampionStats.ChampionStatsEntity;
import lombok.*;
import net.rithms.riot.dto.Stats.ChampionStats;
import net.rithms.riot.dto.Stats.RankedStats;

import javax.persistence.*;

/**
 * Created by Junwoo on 2017-04-27.
 */
@Entity
@Getter
@Setter
@Data
@Table( name = "RankedStatsEntity")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankedStatsEntity implements Dto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "modifyDate")
    private long modifyDate;

    @Column(name = "summonerId")
    private long summonerId;

    @OneToMany(mappedBy="rankedStatsEntity")
    private List<ChampionStatsEntity> champions;

    public static RankedStatsEntity of(RankedStats rankedStats){

        List<ChampionStatsEntity> championList = new LinkedList<ChampionStatsEntity>();
        for(ChampionStats c : rankedStats.getChampions()) {
            championList.add(ChampionStatsEntity.of(c));
        }

        return RankedStatsEntity.builder()
                .summonerId(rankedStats.getSummonerId())
                .modifyDate(rankedStats.getModifyDate())
                .champions(championList)
                .build();
    }
}
