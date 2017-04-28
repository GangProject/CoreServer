package com.gang.domain.ChampionStats;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Junwoo on 2017-04-28.
 */
public interface ChampionStatsRepository extends JpaRepository<ChampionStatsEntity,Integer> {
    List<ChampionStatsEntity> findByChampionId(int championId);
}