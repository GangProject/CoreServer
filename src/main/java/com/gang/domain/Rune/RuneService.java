package com.gang.domain.Rune;

import com.gang.core.manager.RuneApiManager;
import com.gang.core.manager.SummonerApiManager;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Static.Rune;
import net.rithms.riot.dto.Static.RuneList;
import net.rithms.riot.dto.Summoner.RunePage;
import net.rithms.riot.dto.Summoner.RunePages;
import net.rithms.riot.dto.Summoner.RuneSlot;
import net.rithms.riot.dto.Summoner.Summoner;
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

    @Autowired
    private SummonerApiManager summonerApiManager;
    public List<RuneEntity> getRune() throws Exception{

        RuneList list = runeApiManager.getRune();
        Iterator<String> iterator = list.getData().keySet().iterator();
        List<RuneEntity> r1 = runeEntityRepository.findAll();
        if(r1.size()==0) {
            while (iterator.hasNext()) {
                String key = iterator.next();
                RuneEntity r = RuneEntity.of(list.getData().get(key).getId(), list.getData().get(key).getName());
                runeEntityRepository.save(r);
            }
        }else if(r1.size()!=list.getData().keySet().size()){
            for(RuneEntity ru : r1){
                runeEntityRepository.delete(ru);
            }
            while (iterator.hasNext()) {
                String key = iterator.next();
                RuneEntity r = RuneEntity.of(list.getData().get(key).getId(), list.getData().get(key).getName());
                runeEntityRepository.save(r);
            }

        }

        return runeEntityRepository.findAll();

    }
    //다시할껏
    public HashMap<String, HashMap> Rune_summer(String id ) throws Exception{
        Summoner summoner = summonerApiManager.getSummonerByName(Region.KR,id);
        if(summoner==null){
            return null;
        }
        Map<String,RunePages> list =runeApiManager.getRune(String.valueOf(summoner.getId()));
        Iterator<RunePage> iter = list.get(String.valueOf(summoner.getId())).getPages().iterator();
        System.out.println(list.get(String.valueOf(summoner.getId())).getPages().iterator());
        HashMap<String,HashMap> rune= new HashMap<>();
        HashMap<String,Integer> slott;
        HashMap<String,Integer> temp= new HashMap<>();

        while(iter.hasNext()) {
            RunePage r = iter.next();
            Iterator<RuneSlot> slot = r.getSlots().iterator();
            slott=new HashMap<>();
            while (slot.hasNext()) {
                RuneSlot s = slot.next();
                String name=runeEntityRepository.findByRunid(s.getRuneId()).getName();
                if(slott.get(name)==null){
                    slott.put(name, 1);
                }else{
                    slott.put(name, slott.get(name) + 1);
                }
                System.out.println("여기");

            }
            //clone은 복재
            temp=(HashMap)slott.clone();
            rune.put(r.getName(), temp);
            slott.clear();
        }
        System.out.println("끝");
        return rune;

    }

}
