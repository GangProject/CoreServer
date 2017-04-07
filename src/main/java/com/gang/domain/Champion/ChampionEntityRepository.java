package com.gang.domain.Champion;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by seungki on 2017-04-07.
 */
public interface ChampionEntityRepository extends JpaRepository<ChampionEntity,Integer> {
    ChampionEntity findById(int id);
}
