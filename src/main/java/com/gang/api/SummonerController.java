package com.gang.api;

import com.gang.api.common.ResponseDto;
import com.gang.domain.summoner.SummonerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @GetMapping("/info")
    @ApiOperation(value = "솔랭 정보", notes = "솔랭 정보 반환")
    public ResponseDto byName(@RequestParam(value = "name")String name){
        try{
            return summonerService.infoSummoner(name);
        }catch(Exception e){
            return ResponseDto.ofFailure(null,"실패했습니다.");
        }
    }

    @GetMapping("/challengerList")
    @ApiOperation(value = "챌린저 리그 정보", notes = "챌린저 리그 솔랭 정보 반환")
    public ResponseDto challengerList(){
        try{
            return summonerService.challengerList();
        }catch(Exception e){
            return ResponseDto.ofFailure(null,"실패했습니다.");
        }
    }

    @GetMapping("/masterList")
    @ApiOperation(value = "솔랭 정보", notes = "솔랭 정보 반환")
    public ResponseDto masterList(){
        try{
            return summonerService.masterList();
        }catch(Exception e){
            return ResponseDto.ofFailure(null,"실패했습니다.");
        }
    }


}
