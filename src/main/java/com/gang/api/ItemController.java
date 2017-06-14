package com.gang.api;

import com.gang.core.manager.SummonerApiManager;
import com.gang.domain.ITEM.ItemEntity;
import com.gang.domain.ITEM.ItemService;
import com.gang.domain.RecentGame.GameEntity;
import com.gang.domain.RecentGame.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Game.Game;
import net.rithms.riot.dto.Match.MatchDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

/**
 * Created by seungki on 2017-04-07.
 */
@RestController
@Api(value = "아이텝 정보", description = "아이템 API", basePath= "/api/item")
@RequestMapping(value = "/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private SummonerApiManager summonerApiManager;

    @Autowired
    private GameService gameService;

    @GetMapping(value = "/list")
    public List<ItemEntity> list() throws Exception{
        try{
            return itemService.recentItem();
        }catch (Exception e){
            throw e;
        }
    }

    @ApiOperation(value = " 확인", notes = "확인용 필요없는거")
    @GetMapping(value = "/glist")
    public Iterator glist(@RequestParam(name = "name") String name) throws Exception{
        try{
            long start = System.currentTimeMillis();
            summonerApiManager.getSummonerByName(Region.KR,"류종하");

            long end = System.currentTimeMillis();
            Iterator<Game> l = gameService.exam(name);
            System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
            return l;
        }catch (Exception e){
            throw e;
        }
    }

}
