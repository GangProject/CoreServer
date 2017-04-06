package com.gang.api;

import com.gang.domain.Champion.ChampionEntity;
import com.gang.domain.Champion.ChampionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.rithms.riot.api.RiotApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by seungki on 2017-04-07.
 */
@RestController
@Api(value = "챔피언 정보", description = "챔피언 API", basePath= "/api/champion")
@RequestMapping(value = "/api/champion")
public class ChampionController {

    @Autowired
    private ChampionService championService;

    @RequestMapping(value = "/save",method = RequestMethod.GET)
    @ApiOperation(value = " 현재 롤 챔피언 정보", notes = "현재 롤 챔피언 정보 저장")
    public List<ChampionEntity> ChampionSave() throws RiotApiException {
        try {
            return championService.list();
        } catch (RiotApiException e) {
            throw e;
        }
    }
}
