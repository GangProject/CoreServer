package com.gang.api;

import com.gang.domain.Rune.RunEntityDto;
import com.gang.domain.Rune.RunEntityListDto;
import com.gang.domain.Rune.RuneEntity;
import com.gang.domain.Rune.RuneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by seungki on 2017-04-28.
 */
@RestController
@Api(value = "룬정보 ", description = "룬 API", basePath= "/api/rune")
@RequestMapping("/api/rune")
public class RuneController {

    @Autowired
    private RuneService runeService;

    @GetMapping("/info")
    @ApiOperation(value = "롤 룬다가져오기",notes = "룬")
    public List<RuneEntity> rune() throws Exception{
        try{
            return runeService.getRune();
        }catch (Exception e){
            throw e;
        }
    }
    @GetMapping("/summonerName")
    @ApiOperation(value = "소환사가 가지고 있는 룬정보",notes = "룬정보")
    public Map rune(@RequestParam(value = "summonername") String id) throws Exception{
        try{
            Map h =runeService.Rune_summer(id);
            if(h==null){
                h.put(0,null);
                return h;
            }
            return h;
        }catch (Exception e){
            throw e;
        }
    }

}
