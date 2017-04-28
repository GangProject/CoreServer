package com.gang.domain.Rune;

import com.gang.core.manager.RuneApiManager;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Static.Rune;
import net.rithms.riot.dto.Static.RuneList;
import net.rithms.riot.dto.Summoner.RunePage;
import net.rithms.riot.dto.Summoner.RunePages;
import net.rithms.riot.dto.Summoner.RuneSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by seungki on 2017-04-28.
 */
@Service
public class RuneService {

    @Autowired
    private RuneApiManager runeApiManager;

    @Autowired
    private RuneEntityRepository runeEntityRepository;

    public List<RuneEntity> getRune() throws Exception{

        RuneList list = runeApiManager.getRune();
        Iterator<String> iterator = list.getData().keySet().iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
           RuneEntity r =RuneEntity.of(list.getData().get(key).getId(),list.getData().get(key).getName());
           runeEntityRepository.save(r);
        }

        return runeEntityRepository.findAll();

    }
    //다시할껏
    public void Rune_summer(String id ) throws Exception{
        Map<String,RunePages> list =runeApiManager.getRune(id);
        Iterator<RunePage> iter = list.get(id).getPages().iterator();
        HashMap<String,HashMap> rune= new HashMap<>();
        HashMap<String,Integer> slott= new HashMap<>();
        while(iter.hasNext()){
            RunePage r  = iter.next();
            Iterator<RuneSlot> slot = r.getSlots().iterator();
            while (slot.hasNext()){
                RuneSlot s = slot.next();
                slott.put(runeEntityRepository.findByRunid(s.getRuneId()).getName(),0);
                slott.put(runeEntityRepository.findByRunid(s.getRuneId()).getName(),slott.get(runeEntityRepository.findByRunid(s.getRuneId()).getName())+1);
            }
            rune.put(r.getName(),slott);
        }
        System.out.println(rune.toString()+"HERE");



    }

}
