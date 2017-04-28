package com.gang.api;

import com.gang.api.common.ResponseDto;
import com.gang.domain.result.ResultResponseDto;
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
    public ResultResponseDto byName(@RequestParam(value = "name")String name){
        try{
            return ResultResponseDto.of(summonerService.infoSummoner(name));
        }catch(Exception e){
            return ResultResponseDto.Empty();
        }
    }

    @GetMapping("/save")
    @ApiOperation(value = "소환사 저장", notes = "소환사 저장")
    public ResponseDto save(@RequestParam(value = "name")String name){
        try{
            return ResponseDto.ofSuccess(summonerService.firstAccess(null,name),"성공");
        }catch(Exception e){
            return ResponseDto.ofFailure(null,"실패했습니다.");
        }
    }

    @GetMapping("/delete")
    @ApiOperation(value = "소환사 정보 삭제", notes = "소환사 정보 삭제")
    public ResponseDto delete(@RequestParam(value = "name")String name){
        try{
            summonerService.summonerRemove(name);
            return ResponseDto.ofSuccess(null,"성공");
        }catch(Exception e){
            return ResponseDto.ofFailure(null,"실패");
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
