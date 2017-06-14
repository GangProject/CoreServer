package com.gang.api;

import com.gang.domain.Rune.RuneEntity;
import com.gang.domain.Rune.RuneService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

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
    public List<RuneEntity> rune() throws Exception{
        try{
            return runeService.getRune();
        }catch (Exception e){
            throw e;
        }
    }
    @GetMapping("/id")
    public HashMap rune(@RequestParam(value = "summonername") String id) throws Exception{
        try{
            return runeService.Rune_summer(id);
        }catch (Exception e){
            throw e;
        }
    }

}
