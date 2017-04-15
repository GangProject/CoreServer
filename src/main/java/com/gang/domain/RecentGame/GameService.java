package com.gang.domain.RecentGame;

import com.gang.core.manager.GameApiManager;
import com.gang.core.manager.SummonerApiManager;
import com.gang.domain.Champion.ChampionEntity;
import com.gang.domain.Champion.ChampionEntityRepository;
import com.gang.domain.ITEM.ItemEntityRepository;
import com.gang.domain.Spell.SpellEntity;
import com.gang.domain.Spell.SpellRepository;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Game.Game;
import net.rithms.riot.dto.Game.RawStats;
import net.rithms.riot.dto.Game.RecentGames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
    @Autowired
    private ItemEntityRepository itemEntityRepository;

    public List<GameEntity> gameList(String name) throws Exception{
        long id = summonerApiManager.getSummonerByName(Region.KR,name).getId();
        RecentGames game=gameApiManager.getRecentGames(Region.KR,id);
        Iterator<Game> iterator=game.getGames().iterator();
        HashMap<String,String> h = new HashMap<String,String>();
        RawStats itemList = null;
        while(iterator.hasNext()){
            Game g=iterator.next();
            ChampionEntity champ_id=championEntityRepository.findByChampid(g.getChampionId());
            SpellEntity spell1 = spellRepository.findBySpellid(g.getSpell1());
            SpellEntity spell2 = spellRepository.findBySpellid(g.getSpell2());
            if(g.getStats().getItem0()==0){
                h.put("item0","null");
            }else if(g.getStats().getItem0()!=0){
                System.out.print(itemEntityRepository.findByItemid(g.getStats().getItem0()).getName()+"hiyo");
                h.put("item0",itemEntityRepository.findByItemid(g.getStats().getItem0()).getName());
            }
            if(g.getStats().getItem1()==0){
                h.put("item1","null");
            }else if(g.getStats().getItem1()!=0){
                h.put("item1",itemEntityRepository.findByItemid(g.getStats().getItem1()).getName());
            }
            if(g.getStats().getItem2()==0){
                h.put("item2","null");
            }else if(g.getStats().getItem2()!=0){
                h.put("item2",itemEntityRepository.findByItemid(g.getStats().getItem2()).getName());
            }
            if(g.getStats().getItem3()==0){
                h.put("item3","null");
            }else if(g.getStats().getItem3()!=0){
                h.put("item3",itemEntityRepository.findByItemid(g.getStats().getItem3()).getName());
            }
            if(g.getStats().getItem4()==0){
                h.put("item4","null");
            }else if(g.getStats().getItem4()!=0){
                h.put("item4",itemEntityRepository.findByItemid(g.getStats().getItem4()).getName());
            }
            if(g.getStats().getItem5()==0){
                h.put("item5","null");
            }else if(g.getStats().getItem5()!=0){
                h.put("item5",itemEntityRepository.findByItemid(g.getStats().getItem5()).getName());
            }
            if(g.getStats().getItem6()==0){
                h.put("item6","null");
            }else if(g.getStats().getItem6()!=0) {
                h.put("item6", itemEntityRepository.findByItemid(g.getStats().getItem6()).getName());
            }
            gameEntityRepository.save(GameEntity.of(g,id,champ_id,spell1,spell2,h));
        }

        return gameEntityRepository.findAll();
    }
}
