package com.gang.domain.Mastery;

import com.gang.core.manager.GameApiManager;
import com.gang.core.manager.ItemApiManager;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Static.*;
import net.rithms.riot.dto.Static.Mastery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by seungki on 2017-06-12.
 */
@Service
public class MasteryService {

    @Autowired
    private GameApiManager gameApiManager;

    @Autowired
    private  MasteryEntityRepository masteryEntityRepository;

    public List<MasteryEntity> save() throws Exception{
        MasteryList m = gameApiManager.getMastery(Region.KR);
        Iterator<net.rithms.riot.dto.Static.Mastery> m1=m.getData().values().iterator();
        while(m1.hasNext()){
            Mastery m2 = m1.next();
            masteryEntityRepository.save(MasteryEntity.of(m2));
        }
        return masteryEntityRepository.findAll();
    }

}
