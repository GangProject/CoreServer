package com.gang.api;

import com.gang.domain.AggregateStats.AggregateExpertsResponseDto;
import com.gang.domain.AggregateStats.AggregateStatsResponseDto;
import com.gang.domain.ChampionStats.ChampionStatsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Junwoo on 2017-04-28.
 */
@RestController
@Api(value = "championExperts", description = "챔피언 장인 API", basePath= "/api/championExperts")
@RequestMapping("/api/championExperts")
public class ChampionExpertsControoler {
    @Autowired
    ChampionStatsService championStatsService;

    @GetMapping("/info")
    @ApiOperation(value = "챔피언 장인 정보", notes = "챔피언 장인 정보 반환")
    public AggregateExpertsResponseDto byName(@RequestParam(value = "name")String name){
        try{
            return AggregateExpertsResponseDto.of(championStatsService.expertList(name));
        }catch(Exception e){
            return AggregateExpertsResponseDto.Empty();
        }
    }
}
