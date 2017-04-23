package com.gang.domain.RecentGame;

import com.gang.core.manager.GameApiManager;
import com.gang.core.manager.SummonerApiManager;
import com.gang.domain.Champion.ChampionEntity;
import com.gang.domain.Champion.ChampionEntityRepository;
import com.gang.domain.ITEM.ItemEntityRepository;
import com.gang.domain.Player.PlayerEntity;
import com.gang.domain.Player.PlayerEntityRepository;
import com.gang.domain.Spell.SpellEntity;
import com.gang.domain.Spell.SpellRepository;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Game.Game;
import net.rithms.riot.dto.Game.Player;
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
    @Autowired
    private PlayerEntityRepository playerEntityRepository;

    public List<ResposeGame> gameList(String name) throws Exception{
        long id = summonerApiManager.getSummonerByName(Region.KR,name).getId();
        RecentGames game=gameApiManager.getRecentGames(Region.KR,id);
        Iterator<Game> iterator=game.getGames().iterator();
        List<ResposeGame> recent_list =new ArrayList<>();
        RawStats itemList = null;
        while(iterator.hasNext()){
            Game g=iterator.next();
            ChampionEntity champ_id=championEntityRepository.findByChampid(g.getChampionId());
            SpellEntity spell1 = spellRepository.findBySpellid(g.getSpell1());
            SpellEntity spell2 = spellRepository.findBySpellid(g.getSpell2());
            HashMap<String,String> itemName = itemName(g);
            String k=timeChange(g);
            player(g,name);
            gameEntityRepository.save(GameEntity.of(g,k,id,champ_id,spell1,spell2,itemName));
        }

        recent_list=recent (gameEntityRepository.findBySummonerid(id));
        return recent_list;
    }
    //해당게임에서 사용한 아이템 정렬
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
    //함께 게임한 사용자들 정리
    public void player(Game game,String name) throws Exception{
        Iterator<Player> plist = game.getFellowPlayers().iterator();
        List<PlayerEntity> red = new ArrayList<>();
        List<PlayerEntity> blue = new ArrayList<>();
        List<PlayerEntity> list = new ArrayList<>();
        if(game.getTeamId()==100){
            blue.add(PlayerEntity.ofMy(game,name,summonerApiManager.getSummonerByName(Region.KR,name).getId()));
        }else{
            red.add(PlayerEntity.ofMy(game,name,summonerApiManager.getSummonerByName(Region.KR,name).getId()));
        }
        while(plist.hasNext()){
            Player p = plist.next();
            String k = summonerApiManager.getSummonerById(Region.KR,p.getSummonerId()).getName();
            //100blue
            if(game.getTeamId()==100){
                if(game.getTeamId()==p.getTeamId()){
                    blue.add(PlayerEntity.of(game,p,k));
                }else{
                    red.add(PlayerEntity.of(game,p,k));
                }
            }else{
                if(game.getTeamId()==p.getTeamId()){
                    red.add(PlayerEntity.of(game,p,k));
                }else{
                    blue.add(PlayerEntity.of(game,p,k));
                }
            }
        }
        list.addAll(blue);
        list.addAll(red);;
        playerEntityRepository.save(list);
    }

    public List<ResposeGame> recent(List<GameEntity> list){
        List<ResposeGame> R_list = new ArrayList<>();
        for (GameEntity g : list){
            R_list.add(ResposeGame.of(g,playerEntityRepository.findByGameid(g.getId())));
        }
        return R_list;
    }

}
