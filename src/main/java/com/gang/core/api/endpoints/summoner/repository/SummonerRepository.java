package com.gang.core.api.endpoints.summoner.repository;

import com.gang.core.api.endpoints.summoner.dto.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Junwoo on 2017-04-03.
 */
public interface SummonerRepository extends JpaRepository<Summoner,Long> {
    Summoner findById(String id);
}
