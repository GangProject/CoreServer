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
import net.rithms.riot.dto.Match.MatchDetail;
import net.rithms.riot.dto.Match.Participant;
import net.rithms.riot.dto.Match.ParticipantIdentity;
import net.rithms.riot.dto.Match.Team;
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

    public List<ResposeGame> gameList(String name) throws Exception {
        long id = summonerApiManager.getSummonerByName(Region.KR, name).getId();
        RecentGames game = gameApiManager.getRecentGames(Region.KR, id);
        Iterator<Game> iterator = game.getGames().iterator();
        List<ResposeGame> recent_list = new ArrayList<>();
        iteratorGame(iterator,name,id);
        recent_list = recent(gameEntityRepository.findBySummonerid(id));
        return recent_list;
    }

    public List<ResposeGame> dbgameList(String name) throws Exception {
        long id = summonerApiManager.getSummonerByName(Region.KR, name).getId();
        List<ResposeGame> recent_list = new ArrayList<>();
        List<GameEntity> list= gameEntityRepository.findBySummonerid(id);
        if (list.size()==0) {
            System.out.println("here");
            RecentGames game = gameApiManager.getRecentGames(Region.KR, id);
            Iterator<Game> iterator = game.getGames().iterator();
            iteratorGame(iterator,name,id);
            recent_list = recent(gameEntityRepository.findBySummonerid(id));
            return recent_list;
        }else{
            System.out.println("here2");
            recent_list = recent(gameEntityRepository.findBySummonerid(id));
            return recent_list;
    }

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
            R_list.add(ResposeGame.of(g,playerEntityRepository.findByGameid(g.getGameid())));
        }
        return R_list;
    }
    public void iteratorGame(Iterator<Game> iterator,String name,long id) throws Exception{
        while (iterator.hasNext()) {
            Game g = iterator.next();
            ChampionEntity champ_id = championEntityRepository.findByChampid(g.getChampionId());
            SpellEntity spell1 = spellRepository.findBySpellid(g.getSpell1());
            SpellEntity spell2 = spellRepository.findBySpellid(g.getSpell2());
            HashMap<String, String> itemName = itemName(g);
            String k = timeChange(g);
            player(g, name);
            gameEntityRepository.save(GameEntity.of(g, k, id, champ_id, spell1, spell2, itemName));
        }
    }

    public GameInfo gameInfo(String id) throws Exception{
        int gid = Integer.parseInt(id);
        MatchDetail matchDetail = gameApiManager.getRecentGamesInfo(Region.KR,gid);
        Iterator<Participant> list = matchDetail.getParticipants().iterator();
        Iterator<ParticipantIdentity> p_list = matchDetail.getParticipantIdentities().iterator();
        List<GamePlayer> gameinfo = new ArrayList<>();
        int winner=0;
        String winnerName;
        Iterator<Team> team=matchDetail.getTeams().iterator();
        while (team.hasNext()){
            Team t = team.next();
            if(t.isWinner()==true){
                winner=t.getTeamId();
            }
        }
        if(winner==100){
            winnerName="블루팀";
        }else{
            winnerName="레드팀";
        }
        while(list.hasNext()) {
            Participant part = list.next();
            ParticipantIdentity partici = p_list.next();
            GamePlayer game=GamePlayer.ofParty(part,partici,championEntityRepository.findByChampid(part.getChampionId()).getName(),itemPlayer(part),spell(part));
            gameinfo.add(game);
        }
        return GameInfo.of(gameinfo,winnerName);
    }
   public HashMap<String,String> itemPlayer(Participant p){
       HashMap<String,String> h = new HashMap<String,String>();
       if(p.getStats().getItem0()==0){
           h.put("Item0",null);
       }else{
           h.put("item0",itemEntityRepository.findByItemid((int)p.getStats().getItem0()).getName());
       }
       if(p.getStats().getItem1()==0){
           h.put("Item1",null);
       }else{
           h.put("item1",itemEntityRepository.findByItemid((int)p.getStats().getItem1()).getName());
       }
       if(p.getStats().getItem2()==0){
           h.put("Item2",null);
       }else{
           h.put("item2",itemEntityRepository.findByItemid((int)p.getStats().getItem2()).getName());
       }
       if(p.getStats().getItem3()==0){
           h.put("Item3",null);
       }else{
           h.put("item3",itemEntityRepository.findByItemid((int)p.getStats().getItem3()).getName());
       }
       if(p.getStats().getItem4()==0){
           h.put("Item4",null);
       }else{
           h.put("item4",itemEntityRepository.findByItemid((int)p.getStats().getItem4()).getName());
       }
       if(p.getStats().getItem5()==0){
           h.put("Item5",null);
       }else{
           h.put("item5",itemEntityRepository.findByItemid((int)p.getStats().getItem5()).getName());
       }
       if(p.getStats().getItem6()==0){
           h.put("Item6",null);
       }else{
           h.put("item6",itemEntityRepository.findByItemid((int)p.getStats().getItem6()).getName());
       }
       return h;
   }
    public HashMap<String,String> spell(Participant p){
       HashMap<String,String> spell = new HashMap<>();
       spell.put("spell1",spellRepository.findBySpellid(p.getSpell1Id()).getName());
       spell.put("spell2",spellRepository.findBySpellid(p.getSpell2Id()).getName());
       return spell;
    }



}
