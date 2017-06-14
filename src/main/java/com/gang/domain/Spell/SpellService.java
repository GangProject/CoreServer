package com.gang.domain.Spell;

import com.gang.core.manager.SpellApiManager;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Static.SummonerSpell;
import net.rithms.riot.dto.Static.SummonerSpellList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by seungki on 2017-04-15.
 */
@Service
public class SpellService {

    @Autowired
    private SpellApiManager spellApiManager;
    @Autowired
    private SpellRepository spellRepository;

    public Iterator exam() throws Exception{
        SummonerSpellList list = spellApiManager.getDataSummonerSpellList(Region.KR);
        Iterator<SummonerSpell> s = list.getData().values().iterator();
        return s;
    }
    public List<SpellEntity> spell() throws Exception{
        SummonerSpellList list = spellApiManager.getDataSummonerSpellList(Region.KR);
        Iterator<SummonerSpell> s = list.getData().values().iterator();
        List<SpellEntity> sp = spellRepository.findAll();
        if(sp.size()==0){
            while(s.hasNext()){
                SummonerSpell spell = s.next();

                spellRepository.save(SpellEntity.of(spell,Ename(spell)));
            }
        }else if(sp.size()!=list.getData().values().size()){
            for(SpellEntity sp1 : sp){
                spellRepository.delete(sp1);
            }
            while(s.hasNext()){
                SummonerSpell spell = s.next();
                spellRepository.save(SpellEntity.of(spell,Ename(spell)));
            }
        }

        return spellRepository.findAll();
    }
    public String Ename(SummonerSpell s){
        String k = s.getKey();
        String spell = k.substring(8,k.length());
        return spell;
    }
}
