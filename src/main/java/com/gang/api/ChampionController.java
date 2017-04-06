package com.gang.api;

import com.gang.domain.Champion.ChampionEntity;
import com.gang.domain.Champion.ChampionService;
import net.rithms.riot.api.RiotApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by seungki on 2017-04-07.
 */
@RestController
@RequestMapping(value = "Champion")
public class ChampionController {

    @Autowired
    private ChampionService championService;

    @RequestMapping("/save")
    public List<ChampionEntity> ChampionSave() throws RiotApiException {
        try {
            return championService.list();
        } catch (RiotApiException e) {
            throw e;
        }
    }
}
