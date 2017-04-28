package com.gang.api;

import com.gang.api.common.ResponseDto;
import com.gang.domain.AggregateStats.AggregateStatsResponseDto;
import com.gang.domain.RankedStats.RankedStatsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Junwoo on 2017-04-27.
 */
@RestController
@Api(value = "rankedStats", description = "랭크 챔피언 API", basePath= "/api/rankedStats")
@RequestMapping("/api/rankedStats")
public class RankedStatsController {

    @Autowired
    RankedStatsService rankedStatsService;

    @GetMapping("/info")
    @ApiOperation(value = "랭크 챔피언 정보", notes = "랭크 챔피언 정보 반환")
    public AggregateStatsResponseDto byName(@RequestParam(value = "name")String name){
        try{
            return AggregateStatsResponseDto.of(rankedStatsService.infoRankedStats(name));
        }catch(Exception e){
            return AggregateStatsResponseDto.Empty();
        }
    }

    @GetMapping("/delete")
    @ApiOperation(value = "챔피언 랭크 정보 삭제", notes = "챔피언 랭크 정보 삭제")
    public ResponseDto delete(@RequestParam(value = "name")String name){
        try{
            rankedStatsService.rankedStatsRemove(name);
            return ResponseDto.ofSuccess(null,"성공");
        }catch(Exception e){
            return ResponseDto.ofFailure(null,"실패");
        }
    }
}
