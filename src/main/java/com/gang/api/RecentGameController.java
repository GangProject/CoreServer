package com.gang.api;

import com.gang.core.StringNotFoundException;
import com.gang.domain.GameLine.TopLine;
import com.gang.domain.RecentGame.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.dto.Game.RecentGames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by seungki on 2017-04-07.
 */
@RestController
@RequestMapping(value = "/api/recent")
@Api(value = "최근게임 정보", description = "최근게임 API", basePath= "/api/recent")
public class RecentGameController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/dbGame",method = RequestMethod.GET)
    public List<ResposeGame> game(@RequestParam(value = "name") String name) throws Exception{
        try {
            System.out.println("seungki");
            long start = System.currentTimeMillis();
            List<ResposeGame> L = gameService.dbgameList(name);
            long end = System.currentTimeMillis();

            System.out.println( "실행 시간 : " + ( end - start )/1000.0 );

            return L;
        }catch (Exception e){
            throw e;
        }
    }
    @ApiOperation(value = " 전적 갱신", notes = "전적갱신")
    @RequestMapping(value = "/game",method = RequestMethod.GET)
    public List<ResposeGame> game_refresh(@RequestParam(value = "name") String name) throws Exception{
        try {
            System.out.println("seungki_2");
            long start = System.currentTimeMillis();
            List<ResposeGame> l = gameService.gameList(name);
            long end = System.currentTimeMillis();
            System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
            return l;
        }catch (Exception e){
            throw e;
        }
    }

    @ApiOperation(value = "게임자세하게 보기",notes = "게임정보")
    @RequestMapping(value = "/game/info",method = RequestMethod.GET)
    public Game_info gameInfo(@RequestParam(value = "gameid") long id) throws Exception{
        try {
            long start = System.currentTimeMillis();
            Game_info g= gameService.gameInfo(id);
            long end = System.currentTimeMillis();
            System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
            return g;
        }catch (Exception e){
            throw e;
        }
    }
    @ApiOperation(value = "잘하는 라인 보기",notes = "라인2개")
    @RequestMapping(value = "/game/line",method = RequestMethod.GET)
    public List<TopLine> game_line(@RequestParam(value = "summonername") String id) throws Exception{
        try {
            return gameService.Top_Line(id);
        }catch (Exception e){
            throw e;
        }
    }
}
