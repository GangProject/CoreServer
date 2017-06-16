package com.gang.api;

import com.gang.domain.ITEM.ItemEntity;
import com.gang.domain.Mastery.*;
import com.gang.domain.Mastery.MasteryEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.rithms.riot.dto.Static.Mastery;
import net.rithms.riot.dto.Static.MasteryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

/**
 * Created by seungki on 2017-06-12.
 */
@RestController
@Api(value = "특성 정보", description = "특성 API", basePath= "/api/mastery")
@RequestMapping(value = "/api/mastery")
public class MasteryController {


    @Autowired
    private MasteryService masteryService;
    @GetMapping(value = "/list")
    @ApiOperation(value = "모든특성디비저장",notes = "모든 특성 디비저장")
    public List<MasteryEntity> list() throws Exception{
        try{
            return masteryService.save();
        }catch (Exception e){
            throw e;
        }
    }

    @GetMapping(value = "/summoner")
    @ApiOperation(value = "해당소환사가 가지고 있는 특성",notes = "해당소환사가 가지고 있는 특성")
    public List<MasteryCountDto> list(@RequestParam(name = "SummonerName")String name) throws Exception{
        try{
            return masteryService.Summoner(name);
        }catch (Exception e){
            throw e;
        }
    }

}
