package com.gang.domain.summoner;

import net.rithms.riot.dto.Summoner.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Junwoo on 2017-04-07.
 */
@Repository
public interface SummonerRepository extends JpaRepository<SummonerEntity,Integer> {
    SummonerEntity findByName(String name);
    SummonerEntity findBySummonerId(int summonerId);
}
