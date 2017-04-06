package com.gang.api;

import com.gang.domain.summoner.SummonerService;
import io.swagger.annotations.Api;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Summoner.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Junwoo on 2017-04-07.
 */
@RestController
@Api(value = "summoner", description = "소환사 API", basePath= "/api/summoner")
@RequestMapping("/api/summoner")
public class SummonerController {

    @Autowired
    SummonerService summonerService;

    @GetMapping("/save")
    public void byName(@RequestParam(value = "name")String name){
        try{
            summonerService.saveSummonerEntity(name);
        }catch(Exception e){

        }
    }
}
