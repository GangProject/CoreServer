package com.gang.domain.RecentGame;

import com.gang.core.manager.GameApiManager;
import com.gang.core.manager.SummonerApiManager;
import com.gang.domain.Champion.ChampionEntity;
import com.gang.domain.Champion.ChampionEntityRepository;
import com.gang.domain.Spell.SpellEntity;
import com.gang.domain.Spell.SpellRepository;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Game.Game;
import net.rithms.riot.dto.Game.RecentGames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by seungki on 2017-04-07.
 */
@Service
public class GameService {

    @Autowired
    private SummonerApiManager summonerApiManager;

    @Autowired
    private GameApiManager gameApiManager;

    @Autowired
    private GameEntityRepository gameEntityRepository;
    @Autowired
    private ChampionEntityRepository championEntityRepository;
    @Autowired
    private SpellRepository spellRepository;

    public List<GameEntity> gameList(String name) throws Exception{
        long id = summonerApiManager.getSummonerByName(Region.KR,name).getId();
        RecentGames game=gameApiManager.getRecentGames(Region.KR,id);
        Iterator<Game> iterator=game.getGames().iterator();
        while(iterator.hasNext()){
            Game g=iterator.next();
            ChampionEntity champ_id=championEntityRepository.findByChampid(g.getChampionId());
            SpellEntity spell1 = spellRepository.findBySpellid(g.getSpell1());
            SpellEntity spell2 = spellRepository.findBySpellid(g.getSpell2());
            gameEntityRepository.save(GameEntity.of(g,id,champ_id,spell1,spell2));
        }

        return gameEntityRepository.findAll();
    }
}
