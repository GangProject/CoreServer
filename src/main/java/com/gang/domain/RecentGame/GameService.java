package com.gang.domain.RecentGame;

import com.gang.core.manager.GameApiManager;
import com.gang.core.manager.LeagueApiManager;
import com.gang.core.manager.SummonerApiManager;
import com.gang.domain.Champion.ChampionEntity;
import com.gang.domain.Champion.ChampionEntityRepository;
import com.gang.domain.GameLine.*;
import com.gang.domain.ITEM.ItemEntityRepository;
import com.gang.domain.Player.PlayerEntity;
import com.gang.domain.Player.PlayerEntityRepository;
import com.gang.domain.Spell.SpellEntity;
import com.gang.domain.Spell.SpellRepository;
import com.gang.domain.summoner.SummonerEntity;
import com.gang.domain.summoner.SummonerRepository;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Game.Game;
import net.rithms.riot.dto.Game.Player;
import net.rithms.riot.dto.Game.RecentGames;
import net.rithms.riot.dto.League.League;
import net.rithms.riot.dto.Match.MatchDetail;
import net.rithms.riot.dto.Match.Participant;
import net.rithms.riot.dto.Match.ParticipantIdentity;
import net.rithms.riot.dto.Match.Team;
import net.rithms.riot.dto.Summoner.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Duration;
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

    @Autowired
    private LeagueApiManager leagueApiManager;

    @Autowired
    private Mid2Repository midRepository;

    @Autowired
    private BottomRepository bottomRepository;

    @Autowired
    private JunggleRepository junggleRepository;

    @Autowired
    private TopRepository topRepository;

    @Autowired
    private SummonerRepository summonerRepository;
    public MatchDetail exam(String name) throws Exception{
        long id = summonerApiManager.getSummonerByName(Region.KR, name).getId();
        RecentGames game = gameApiManager.getRecentGames(Region.KR, id);
        Iterator<Game> iterator = game.getGames().iterator();
        MatchDetail m = gameApiManager.getRecentGamesInfo(Region.KR,2846412620L);
        return s;
    }
    public List<ResposeGame> gameList(String name) throws Exception {
        long id = summonerApiManager.getSummonerByName(Region.KR, name).getId();
        RecentGames game = gameApiManager.getRecentGames(Region.KR, id);
        Iterator<Game> iterator = game.getGames().iterator();
        List<ResposeGame> recent_list = new ArrayList<>();
        Game r=iterator.next();
        String gameMdde;
        recent_list = recent(gameEntityRepository.findBySummoneridOrderByDateDesc(id));
        if(r.getCreateDate()==Long.parseLong(recent_list.get(0).getGameEntity().getCreateDate())){
            return recent_list;
        }else{
            while (iterator.hasNext()){
                    if(r.getCreateDate()==Long.parseLong(recent_list.get(0).getGameEntity().getCreateDate())){
                        break;
                    }else {
                        game_line(r, id, r.getStats().isWin());
                        ChampionEntity champ_id = championEntityRepository.findByChampid(r.getChampionId());
                        SpellEntity spell1 = spellRepository.findBySpellid(r.getSpell1());
                        SpellEntity spell2 = spellRepository.findBySpellid(r.getSpell2());
                        HashMap<String, String> itemName = itemName(r);
                        String k = timeChange(r);
                        player(r, name,id);
                        if(r.getSubType().equals("RANKED_SOLO_5x5")){
                            gameMdde="솔랭";
                        }else if(r.getSubType().equals("RANKED_FLEX_SR")){
                            gameMdde="자유랭크";
                        }else if(r.getSubType().equals("ARAM")){
                            gameMdde="칼바람";
                        }else{
                            gameMdde="일반";
                        }
                        MatchDetail m = gameApiManager.getRecentGamesInfo(Region.KR,r.getGameId());

                        //gameEntityRepository.save(GameEntity.of(gameMdde,r, k, id, champ_id, spell1, spell2, itemName));
                    }
                    r=iterator.next();
            }
            recent_list = recent(gameEntityRepository.findBySummoneridOrderByDateDesc(id));
            return recent_list;
        }


    }

    public List<ResposeGame> dbgameList(String name) throws Exception {
        long id = summonerApiManager.getSummonerByName(Region.KR, name).getId();
        List<ResposeGame> recent_list = new ArrayList<>();
        List<GameEntity> list= gameEntityRepository.findBySummoneridOrderByDateDesc(id);
        if (list.size()==0) {
            System.out.println("처음");
            RecentGames game = gameApiManager.getRecentGames(Region.KR, id);
            Iterator<Game> iterator = game.getGames().iterator();
            long start = System.currentTimeMillis();
            iteratorGame(iterator,name,id);
            long end = System.currentTimeMillis();
            System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
            recent_list = recent(gameEntityRepository.findBySummoneridOrderByDateDesc(id));
            return recent_list;
        }else{
            System.out.println("두번쨰");
            recent_list = recent(gameEntityRepository.findBySummoneridOrderByDateDesc(id));
            return recent_list;
    }

}
    //해당게임에서 사용한 아이템 정렬
    public HashMap<String,String> itemName(Game g){
        HashMap<String,String> h = new HashMap<String,String>();
        if(g.getStats().getItem0()==0){
            h.put("item0","null");
        }else if(g.getStats().getItem0()!=0){
            String item=String.valueOf(g.getStats().getItem0());
            h.put("item0",item);
        }
        if(g.getStats().getItem1()==0){
            h.put("item1","null");
        }else if(g.getStats().getItem1()!=0){
            String item=String.valueOf(g.getStats().getItem1());
            h.put("item1",item);
        }
        if(g.getStats().getItem2()==0){
            h.put("item2","null");
        }else if(g.getStats().getItem2()!=0){
            String item=String.valueOf(g.getStats().getItem2());
            h.put("item2",item);
        }
        if(g.getStats().getItem3()==0){
            h.put("item3","null");
        }else if(g.getStats().getItem3()!=0){
            String item=String.valueOf(g.getStats().getItem3());
            h.put("item3",item);
        }
        if(g.getStats().getItem4()==0){
            h.put("item4","null");
        }else if(g.getStats().getItem4()!=0){
            String item=String.valueOf(g.getStats().getItem4());
            h.put("item4",item);
        }
        if(g.getStats().getItem5()==0){
            h.put("item5","null");
        }else if(g.getStats().getItem5()!=0){
            String item=String.valueOf(g.getStats().getItem5());
            h.put("item5",item);
        }
        if(g.getStats().getItem6()==0){
            h.put("item6","null");
        }else if(g.getStats().getItem6()!=0) {
            String item=String.valueOf(g.getStats().getItem6());
            h.put("item6", item);
        }
        return h;
    }
    public String timeChange(Game game){
        Long l = game.getCreateDate();
        Date date = new Date(l); // 'epoch' in long
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        String dateString =formatter.format(date);
        String now=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMM")).toString();
        long time=0;
        String t;
        if(!dateString.equals(now)){
            time = Integer.parseInt(now)-Integer.parseInt(dateString);
            t=time+"달전";
            return t;
        }else{
            formatter = new SimpleDateFormat("yyyyMMdd");
             dateString = formatter.format(date);
             now =LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")).toString();
            if(dateString.equals(now)){
                formatter = new SimpleDateFormat("yyyyMMddHH");
                dateString = formatter.format(date);
                now =LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHH")).toString();
                time = Long.parseLong(now)-Long.parseLong(dateString);
                t=time+"시간전";
                if(dateString.equals(now)){
                    formatter = new SimpleDateFormat("yyyyMMddHHmm");
                    dateString = formatter.format(date);
                    now =LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHH")).toString();
                    time = Long.parseLong(now)-Long.parseLong(dateString);
                    t=time+"분전";
                }
            }else {
                time = Integer.parseInt(now) - Integer.parseInt(dateString);
                t = time + "일전";
            }
            return t;
        }
    }
    //함께 게임한 사용자들 정리
    public void player(Game game,String name,long id) throws Exception{
        Iterator<Player> plist = game.getFellowPlayers().iterator();
        List<PlayerEntity> red = new ArrayList<>();
        List<PlayerEntity> blue = new ArrayList<>();
        List<PlayerEntity> list = new ArrayList<>();
        if(game.getTeamId()==100){
            blue.add(PlayerEntity.ofMy(game,name,id));
        }else{
            red.add(PlayerEntity.ofMy(game,name,id));
        }
        while(plist.hasNext()){

            Player p = plist.next();
            //String k = summonerApiManager.getSummonerById(Region.KR,p.getSummonerId()).getName();
            SummonerEntity s=summonerRepository.findBySummonerId((int)p.getSummonerId());
            String k=null;
            if(s==null){
                Summoner summoner = summonerApiManager.getSummonerById(Region.KR,p.getSummonerId());
                summonerRepository.save(SummonerEntity.of(summoner));
                k = summoner.getName();
            }else{
                k=s.getName();
            }


            //100blue
            if(game.getTeamId()==100){
                String champName = championEntityRepository.findByChampid(p.getChampionId()).getEname();
                if(game.getTeamId()==p.getTeamId()){

                    blue.add(PlayerEntity.of(game,p,k,champName));
                }else{

                    red.add(PlayerEntity.of(game,p,k,champName));
                }
            }else{
                if(game.getTeamId()==p.getTeamId()){
                    String champName = championEntityRepository.findByChampid(p.getChampionId()).getEname();
                    red.add(PlayerEntity.of(game,p,k,champName));
                }else{
                    String champName = championEntityRepository.findByChampid(p.getChampionId()).getEname();
                    blue.add(PlayerEntity.of(game,p,k,champName));
                }

            }
        }
        list.addAll(blue);
        list.addAll(red);
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
            if(g.getSubType().equals("RANKED_SOLO_5x5")){
                game_line(g, id, g.getStats().isWin());
            }
            if(g.getSubType().equals("RANKED_FLEX_SR")){
                game_line(g, id, g.getStats().isWin());
            }
            if(g.getSubType().equals("ARAM")){

            }
            if(g.getSubType().equals("NORMAL")) {

            }

            ChampionEntity champ_id = championEntityRepository.findByChampid(g.getChampionId());
            SpellEntity spell1 = spellRepository.findBySpellid(g.getSpell1());
            SpellEntity spell2 = spellRepository.findBySpellid(g.getSpell2());
            HashMap<String, String> itemName = itemName(g);
            String k = timeChange(g);
            player(g, name,id);
            String gameMode=null;
            if(g.getSubType().equals("RANKED_SOLO_5x5")){
                gameMode="솔랭";
            }
            if(g.getSubType().equals("RANKED_FLEX_SR")){
                gameMode="자유랭크";
            }
            if(g.getSubType().equals("ARAM")){
                gameMode="칼바람";
            }
            if(g.getSubType().equals("NORMAL")){
                gameMode="일반";
            }
            MatchDetail m = gameApiManager.getRecentGamesInfo(Region.KR,g.getGameId());
            gameEntityRepository.save(GameEntity.of(kill(g),TimeDuration(m),CreateTime(g.getCreateDate()),gameMode,g, k, id, champ_id, spell1.getEname(), spell2.getEname(), itemName));
        }
    }

    public Game_info gameInfo(long id) throws Exception{

        MatchDetail matchDetail = gameApiManager.getRecentGamesInfo(Region.KR,id);
        Iterator<Participant> list = matchDetail.getParticipants().iterator();
        Iterator<ParticipantIdentity> p_list = matchDetail.getParticipantIdentities().iterator();

        List<GamePlayer> red = new ArrayList<>();
        List<GamePlayer> blue = new ArrayList<>();


        while(list.hasNext()) {
            Participant part = list.next();
            ParticipantIdentity partici = p_list.next();
            String summerid=String.valueOf(partici.getPlayer().getSummonerId());
            List<League> league = leagueApiManager.getLeagueEntryBySummoner(Region.KR,summerid);
            String tier=league.get(0).getTier()+league.get(0).getEntries().iterator().next().getDivision();
            GamePlayer game=GamePlayer.ofParty(part,partici,championEntityRepository.findByChampid(part.getChampionId()).getName(),spell(part),itemPlayer(part),tier);

            if(game.getTeamId()==100){
                blue.add(game);
            }else{
                red.add(game);
            }
        }

        Iterator<Team> team=matchDetail.getTeams().iterator();
        int teamid=0;
        while (team.hasNext()){
            Team t = team.next();
            if(t.isWinner()==true){
                teamid=t.getTeamId();
            }
        }
        List<GameInfo> gameInfos = new ArrayList<>();
        Iterator<Team> team1=matchDetail.getTeams().iterator();
        if(teamid==100){
            while (team1.hasNext()){
                Team t = team1.next();
                if (t.getTeamId()==100){
                    gameInfos.add(GameInfo.of(blue,true,t.getDragonKills(),t.getTowerKills(),t.getBaronKills()));
                }else{
                    gameInfos.add(GameInfo.of(red,false,t.getDragonKills(),t.getTowerKills(),t.getBaronKills()));
                }
            }
        }else{
            while (team1.hasNext()){
                Team t = team1.next();
                if (t.getTeamId()==200){
                    gameInfos.add(GameInfo.of(blue,false,t.getDragonKills(),t.getTowerKills(),t.getBaronKills()));
                }else{
                    gameInfos.add(GameInfo.of(red,true,t.getDragonKills(),t.getTowerKills(),t.getBaronKills()));
                }
            }
        }

        return Game_info.of(gameInfos);

    }
   public HashMap<String,String> itemPlayer(Participant p){
       HashMap<String,String> h = new HashMap<String,String>();
       if(p.getStats().getItem0()==0){
           h.put("Item0",null);
       }else{
           h.put("Item0",itemEntityRepository.findByItemid((int)p.getStats().getItem0()).getName());
       }
       if(p.getStats().getItem1()==0){
           h.put("Item1",null);
       }else{
           h.put("Item1",itemEntityRepository.findByItemid((int)p.getStats().getItem1()).getName());
       }
       if(p.getStats().getItem2()==0){
           h.put("Item2",null);
       }else{
           h.put("Item2",itemEntityRepository.findByItemid((int)p.getStats().getItem2()).getName());
       }
       if(p.getStats().getItem3()==0){
           h.put("Item3",null);
       }else{
           h.put("Item3",itemEntityRepository.findByItemid((int)p.getStats().getItem3()).getName());
       }
       if(p.getStats().getItem4()==0){
           h.put("Item4",null);
       }else{
           h.put("Item4",itemEntityRepository.findByItemid((int)p.getStats().getItem4()).getName());
       }
       if(p.getStats().getItem5()==0){
           h.put("Item5",null);
       }else{
           h.put("Item5",itemEntityRepository.findByItemid((int)p.getStats().getItem5()).getName());
       }
       if(p.getStats().getItem6()==0){
           h.put("Item6",null);
       }else{
           h.put("Item6",itemEntityRepository.findByItemid((int)p.getStats().getItem6()).getName());
       }
       return h;
   }
    public HashMap<String,String> spell(Participant p){
       HashMap<String,String> spell = new HashMap<>();
       spell.put("spell1",spellRepository.findBySpellid(p.getSpell1Id()).getEname());
       spell.put("spell2",spellRepository.findBySpellid(p.getSpell2Id()).getEname());
       return spell;
    }
    public void game_line(Game game,long id,boolean win) throws  Exception{
        //4는 서폿
        //3 정글
        //2 미드
        //1탑

       Iterator<ParticipantIdentity> list = gameApiManager.getRecentGamesInfo(Region.KR,game.getGameId()).getParticipantIdentities().iterator();
       long p_id=0;

       while(list.hasNext()){
           ParticipantIdentity p = list.next();
           if(p.getPlayer().getSummonerId()==id){
               p_id=p.getParticipantId();
               break;
           }
       }
        Iterator<Participant> p_list = gameApiManager.getRecentGamesInfo(Region.KR,game.getGameId()).getParticipants().iterator();
       String lane=null;
       System.out.println(p_id+"leeseung");
       while (p_list.hasNext()){
           Participant p = p_list.next();
           if(p.getParticipantId()==p_id){
               lane=p.getTimeline().getLane();
           }
       }
       System.out.println(lane+"leeseung");

        line(lane,id,win);
    }
    public <T>boolean check(T t) {
        if (t == null) {
            return false;
        } else {
            return true;
        }
    }
    public void line(String line,long id,boolean win){
        System.out.println(line+"power");
        if(line.equals("MIDDLE")){
            Mid2 mid = midRepository.findByplayerid(id);
            if(check(mid)){
                mid.setTotalGame(mid.getTotalGame()+1);
                if(win){
                    mid.setWin(mid.getWin()+1);
                }else{
                    mid.setRose(mid.getRose()+1);
                }
                midRepository.save(mid);
            }else{
                if(win){
                   midRepository.save(Mid2.of_p_w(id));
                }else{
                    midRepository.save(Mid2.of_p_r(id));
                }
            }

        }
        if(line.equals("TOP")){
            Top top = topRepository.findByplayerid(id);
            if(check(top)){
                top.setTotalGame(top.getTotalGame()+1);
                if(win){
                    top.setWin(top.getWin()+1);
                }else{
                    top.setRose(top.getRose()+1);
                }
                topRepository.save(top);
            }else{
                if(win){
                    topRepository.save(Top.of_p_w(id));
                }else{
                    topRepository.save(Top.of_p_r(id));
                }
            }
        }
        if(line.equals("JUNGGLE")){
            Junggle junggle = junggleRepository.findByplayerid(id);
            if(check(junggle)){
                junggle.setTotalGame(junggle.getTotalGame()+1);
                if(win){
                    junggle.setWin(junggle.getWin()+1);
                }else{
                    junggle.setRose(junggle.getRose()+1);
                }
                junggleRepository.save(junggle);
            }else{
                if(win){
                    junggleRepository.save(Junggle.of_p_w(id));
                }else{
                    junggleRepository.save(Junggle.of_p_r(id));
                }
            }
        }
        if(line.equals("BOTTOM")){
            Bottom bottom = bottomRepository.findByplayerid(id);
            if(check(bottom)){
                bottom.setTotalGame(bottom.getTotalGame()+1);
                if(win){
                    bottom.setWin(bottom.getWin()+1);
                }else{
                    bottom.setRose(bottom.getRose()+1);
                }
                bottomRepository.save(bottom);
            }else{
                if(win){
                    bottomRepository.save(Bottom.of_p_w(id));
                }else{
                    bottomRepository.save(Bottom.of_p_r(id));
                }
            }
        }
    }

    public List<TopLine> Top_Line(long id){
        Mid2 mid = midRepository.findByplayerid(id);
        Bottom bottom = bottomRepository.findByplayerid(id);
        Junggle junggle = junggleRepository.findByplayerid(id);
        Top top = topRepository.findByplayerid(id);
        int mid_percent,bottom_percent,junggle_percent,top_percent;
        if(mid==null){
             mid_percent = 0;
        }else{
            mid_percent = (int)(((double) mid.getWin()/(double)mid.getTotalGame())*100);
        }
        if(bottom==null){
             bottom_percent = 0;
        }else{
            bottom_percent = (int)(((double) bottom.getWin()/(double)bottom.getTotalGame())*100);
        }
        if(junggle==null){
            junggle_percent = 0;
        }else{
            junggle_percent = (int)(((double) junggle.getWin()/(double)junggle.getTotalGame())*100);
        }
        if(top==null){
            top_percent = 0;
        }else{
            top_percent = (int)(((double) top.getWin()/(double)top.getTotalGame())*100);
        }

        PriorityQueue<TopLine> p = new PriorityQueue<>();
        p.add(new TopLine("미드",mid_percent));
        p.add(new TopLine("바텀",bottom_percent));
        p.add(new TopLine("정글",junggle_percent));
        p.add(new TopLine("탑",top_percent));

        List<TopLine> list = new ArrayList<>();
        p.poll();
        p.poll();
        list.add(p.poll());
        list.add(p.poll());


        return list;
    }

    public String TimeDuration(MatchDetail m){
        String t = String.valueOf(Duration.ofMinutes(m.getMatchDuration()));
        String time = t.replaceAll("[^0-9]","");
        String ss =time.substring(time.length()-2,time.length())+"초";
        String mm = time.substring(0,time.length()-2)+"분";
        String duration = mm+ss;
        return duration;
    }

    public String CreateTime(long l){
        Date d = new Date(l);
        SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd HH:mm");
        String k =s.format(d);
        return k;
    }

    public String kill(Game game){
        int doublekill = game.getStats().getDoubleKills();
        int triple = game.getStats().getTripleKills();
        int quadra = game.getStats().getQuadraKills();
        int penta = game.getStats().getPentaKills();
        String kill=null;
        if(penta>0){
            return   kill="펜타킬";
        }
        else if(quadra>0){
            return  kill="쿼드라킬";
        }
        else if(triple>0){
            return  kill="트리플킬";
        }
        else if(doublekill>0){
           return  kill="더블킬";
        }else{
            return null;
        }

    }


}
