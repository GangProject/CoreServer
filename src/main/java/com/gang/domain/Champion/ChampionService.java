package com.gang.domain.Champion;

import com.gang.core.StringNotFoundException;
import com.gang.core.manager.ChampionApiManager;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Region;
import net.rithms.riot.constant.staticdata.ChampData;
import net.rithms.riot.dto.Static.ChampionList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;


/**
 * Created by seungki on 2017-04-07.
 */
@Service
public class ChampionService {

    @Autowired
    private ChampionEntityRepository championEntityRepository;
    @Autowired
    private ChampionApiManager championApiManager;

    //현재 LOL에 있는 챔피언 아이디 값과 이름값 디비에 저장
    public List<ChampionEntity> list() throws RiotApiException,StringNotFoundException,InterruptedException{
        RiotApi api = new RiotApi("RGAPI-e8372943-2ac3-4bed-8d2c-86f9c86174fe");
        ChampionList championList;
        List<ChampionEntity> championList_db=championEntityRepository.findAll();
        championList = championApiManager.getDataChampionList(Region.KR, ChampData.ALL);
        Iterator<String> iterator = championList.getKeys().values().iterator();
        if(championList_db.size()==0) {
            while (iterator.hasNext()) {
                String s = iterator.next();
                championEntityRepository.save(ChampionEntity.of(championList.getData().get(s).getId(), championList.getData().get(s).getName(),
                        championList.getData().get(s).getKey()));
            }

        }else if(championList_db.size()!=championList.getKeys().values().size()){
            for(ChampionEntity c : championList_db){
                championEntityRepository.delete(c);
            }
            while (iterator.hasNext()) {
                String s = iterator.next();
                championEntityRepository.save(ChampionEntity.of(championList.getData().get(s).getId(), championList.getData().get(s).getName(),
                        championList.getData().get(s).getKey()));
            }

        }
        return championEntityRepository.findAll();
    }
}
