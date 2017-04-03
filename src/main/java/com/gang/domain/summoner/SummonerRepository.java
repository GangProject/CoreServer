package com.gang.domain.summoner;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Junwoo on 2017-04-03.
 */
public interface SummonerRepository extends JpaRepository<Summoner,Long> {
    Summoner findById(String id);
}
