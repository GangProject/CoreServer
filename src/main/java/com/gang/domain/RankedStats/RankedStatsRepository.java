package com.gang.domain.RankedStats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Junwoo on 2017-04-28.
 */
@Repository
public interface RankedStatsRepository extends JpaRepository<RankedStatsEntity,Integer> {
    RankedStatsEntity findBySummonerId(long summonerId);
}
