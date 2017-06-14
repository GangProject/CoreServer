package com.gang.api;

import com.gang.domain.RecentGame.GameEntity;
import com.gang.domain.Spell.SpellEntity;
import com.gang.domain.Spell.SpellService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
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
    @ApiOperation(value = " 확인", notes = "확인용 필요없는거")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Iterator list() throws Exception {
        return spellService.exam();
    }
}
