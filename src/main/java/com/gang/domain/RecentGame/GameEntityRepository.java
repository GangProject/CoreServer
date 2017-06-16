package com.gang.domain.RecentGame;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by seungki on 2017-04-07.
 */
public interface GameEntityRepository extends JpaRepository<GameEntity,Integer>{
    List<GameEntity> findBySummoneridOrderByDateDesc(long id);


}
