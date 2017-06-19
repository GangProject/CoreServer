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

import java.util.*;

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

    public List<RuneEntity> getRune() throws Exception {

        RuneList list = runeApiManager.getRune();
        Iterator<String> iterator = list.getData().keySet().iterator();
        List<RuneEntity> r1 = runeEntityRepository.findAll();
        if (r1.size() == 0) {
            while (iterator.hasNext()) {
                String key = iterator.next();
                RuneEntity r = RuneEntity.of(list.getData().get(key));
                runeEntityRepository.save(r);
            }
        } else if (r1.size() != list.getData().keySet().size()) {
            for (RuneEntity ru : r1) {
                runeEntityRepository.delete(ru);
            }
            while (iterator.hasNext()) {
                String key = iterator.next();
                RuneEntity r = RuneEntity.of(list.getData().get(key));
                runeEntityRepository.save(r);
            }

        }

        return runeEntityRepository.findAll();

    }

    //다시할껏
    public List<RunEntityListDto> Rune_summer(String id) throws Exception {
        Summoner summoner = summonerApiManager.getSummonerByName(Region.KR, id);
        if (summoner == null) {
            return null;
        }
        int num = 0;
        Map<String, RunePages> list = runeApiManager.getRune(String.valueOf(summoner.getId()));
        Iterator<RunePage> iter = list.get(String.valueOf(summoner.getId())).getPages().iterator();
        System.out.println(list.get(String.valueOf(summoner.getId())).getPages().iterator());
        HashMap<String, Integer> rune = new HashMap<>();
        List<RuneEntityCountDto>  yellow = null;
        List<RuneEntityCountDto> red = null;
        List<RuneEntityCountDto>  black = null;
        List<RuneEntityCountDto>  blue = null;
        HashMap<String, HashMap> addRune = new HashMap<>();
        HashMap<String, HashMap> temp = new HashMap<>();
        List<RunEntityListDto> r_list = new ArrayList<>();
        HashMap<Integer,RunEntityListDto> rlist = new HashMap<>();
        List<RuneEntityCountDto> relist = null;
        int count = 0;
        int number=0;
        RuneEntity name=null;
        while (iter.hasNext()) {
            yellow=new ArrayList<>();
            black=new ArrayList<>();
            red=new ArrayList<>();
            blue=new ArrayList<>();
            relist = new ArrayList<>();
            rune.clear();
            RunePage r = iter.next();
            if (r.getSlots()==null) {
                r_list.add(RunEntityListDto.of(r.getName(), null));
            } else {
                Iterator<RuneSlot> slot = r.getSlots().iterator();
                while (slot.hasNext()) {
                    RuneSlot s = slot.next();
                    name = runeEntityRepository.findByRunid(s.getRuneId());
                    if (rune.containsKey(name.getName())) {
                        rune.put(name.getName(), rune.get(name.getName()) + 1);
                    } else {
                        rune.put(name.getName(), 1);
                    }
                }
                Iterator<String> k = rune.keySet().iterator();
                while (k.hasNext()) {
                    String k1 = k.next();
                    Integer nu = rune.get(k1);
                    RuneEntity rn = runeEntityRepository.findByName(k1);
                    if(rn.getType().equals("red")){
                            red.add(RuneEntityCountDto.of(k1, nu,rn.getDescript()));
                    }else if(rn.getType().equals("blue")){
                            blue.add(RuneEntityCountDto.of(k1, nu,rn.getDescript()));
                    }else if(rn.getType().equals("black")){
                            black.add(RuneEntityCountDto.of(k1, nu,rn.getDescript()));
                    }else if(rn.getType().equals("yellow")){
                            yellow.add(RuneEntityCountDto.of(k1, nu,rn.getDescript()));
                    }

                }
                r_list.add(RunEntityListDto.of(r.getName(), RunEntityDto.of(red,yellow,blue,black)));

            }

        }

            return r_list;
    }
    public void insert(HashMap h,RuneEntity runeEntity){
        if(h.get(runeEntity.getName())==null){
            h.put(runeEntity.getName(),1);
        }else{
            int k = (int)h.get(runeEntity.getName())+1;
            h.put(runeEntity.getName(),k);
        }
    }

}