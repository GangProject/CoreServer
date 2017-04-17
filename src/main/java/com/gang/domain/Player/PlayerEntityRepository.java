package com.gang.domain.Player;

import net.rithms.riot.dto.Game.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by seungki on 2017-04-17.
 */
public interface PlayerEntityRepository extends JpaRepository<PlayerEntity,Integer>{
    List<PlayerEntity> findByGameidOrderByTeamid(Game g);
}
