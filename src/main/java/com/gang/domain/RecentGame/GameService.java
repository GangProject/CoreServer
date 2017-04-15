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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        RawStats itemList = null;
        while(iterator.hasNext()){
            Game g=iterator.next();
            System.out.print("not here");
            ChampionEntity champ_id=championEntityRepository.findByChampid(g.getChampionId());
            SpellEntity spell1 = spellRepository.findBySpellid(g.getSpell1());
            SpellEntity spell2 = spellRepository.findBySpellid(g.getSpell2());
            HashMap<String,String> itemName = itemName(g);
            String k=timeChange(g);
            gameEntityRepository.save(GameEntity.of(g,k,id,champ_id,spell1,spell2,itemName));
        }
        System.out.print("here");
        return gameEntityRepository.findBySummonerid(id);
    }
    public HashMap<String,String> itemName(Game g){
        HashMap<String,String> h = new HashMap<String,String>();
        if(g.getStats().getItem0()==0){
            h.put("item0","null");
        }else if(g.getStats().getItem0()!=0){
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
        return h;
    }
    public String timeChange(Game game){
        Long l = game.getCreateDate();
        Date date = new Date(l); // 'epoch' in long
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString =formatter.format(date);
        String now=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")).toString();
        int time=0;
        String t;
        if(!dateString.equals(now)){
            time = Integer.parseInt(now)-Integer.parseInt(dateString);
            t=time+"일전";
            return t;
        }else{
            formatter = new SimpleDateFormat("yyyyMMddHH");
             dateString = formatter.format(date);
             now =LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHH")).toString();
            time = Integer.parseInt(now)-Integer.parseInt(dateString);
            t=time+"시간전";
            return t;
        }



    }
}
