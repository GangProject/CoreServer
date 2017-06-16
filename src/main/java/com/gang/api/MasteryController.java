package com.gang.api;

import com.gang.domain.ITEM.ItemEntity;
import com.gang.domain.Mastery.MasteryEntity;
import com.gang.domain.Mastery.MasteryEntity;
import com.gang.domain.Mastery.MasteryEntityRepository;
import com.gang.domain.Mastery.MasteryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.rithms.riot.dto.Static.Mastery;
import net.rithms.riot.dto.Static.MasteryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public MasteryList list() throws Exception{
        try{
            return masteryService.save();
        }catch (Exception e){
            throw e;
        }
    }

}
