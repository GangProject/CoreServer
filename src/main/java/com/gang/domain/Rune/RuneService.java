package com.gang.domain.Rune;

import com.gang.core.manager.RuneApiManager;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Static.Rune;
import net.rithms.riot.dto.Static.RuneList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

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

}
