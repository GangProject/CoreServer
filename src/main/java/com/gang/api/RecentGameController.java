package com.gang.api;

import com.gang.core.StringNotFoundException;
import com.gang.domain.RecentGame.GameEntity;
import com.gang.domain.RecentGame.GameService;
import com.gang.domain.RecentGame.ResposeGame;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.rithms.riot.api.RiotApiException;
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
            return gameService.dbgameList(name);
        }catch (Exception e){
            throw e;
        }
    }
    @ApiOperation(value = " 전적 갱신", notes = "전적갱신")
    @RequestMapping(value = "/game",method = RequestMethod.GET)
    public List<ResposeGame> game(@RequestParam(value = "name") String name,int a) throws Exception{
        try {
            return gameService.gameList(name);
        }catch (Exception e){
            throw e;
        }
    }
}
