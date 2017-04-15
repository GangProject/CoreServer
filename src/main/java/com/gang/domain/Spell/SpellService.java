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

    public List<SpellEntity> spell() throws Exception{
        SummonerSpellList list = spellApiManager.getDataSummonerSpellList(Region.KR);
        Iterator<SummonerSpell> s = list.getData().values().iterator();
        while(s.hasNext()){
            SummonerSpell spell = s.next();
            spellRepository.save(SpellEntity.of(spell));
        }
        return spellRepository.findAll();
    }
}
