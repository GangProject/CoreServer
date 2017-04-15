package com.gang.api;

import com.gang.domain.RecentGame.GameEntity;
import com.gang.domain.Spell.SpellEntity;
import com.gang.domain.Spell.SpellService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by seungki on 2017-04-15.
 */
@RestController
@RequestMapping(value = "/api/spell")
@Api(value = "spell 저장", description = "spell", basePath= "/api/spell")
public class SpellController {

    @Autowired
    private SpellService spellService;


    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public List<SpellEntity> save() throws Exception {
        return spellService.spell();
    }
}
