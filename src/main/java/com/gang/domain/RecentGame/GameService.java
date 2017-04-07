package com.gang.domain.RecentGame;

import com.gang.core.RiotApiManager;
import com.gang.domain.Champion.ChampionEntityRepository;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Game.Game;
import net.rithms.riot.dto.Game.RecentGames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by seungki on 2017-04-07.
 */
@Service
public class GameService {

    @Autowired
    private RiotApiManager riotApiManager;
    @Autowired
    private GameEntityRepository gameEntityRepository;
    @Autowired
    private ChampionEntityRepository championEntityRepository;

    public List<GameEntity> gameList(String name) throws Exception{
        long id = riotApiManager.getSummonerByName(Region.KR,name).getId();
        RecentGames game=riotApiManager.getRecentGames(Region.KR,id);
        Iterator<Game> iterator=game.getGames().iterator();
        while(iterator.hasNext()){
            Game g=iterator.next();
            gameEntityRepository.save(GameEntity.of(g,id,championEntityRepository.findById(g.getChampionId())));
        }

        return gameEntityRepository.findAll();
    }
}