package com.gang.domain.Champion;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Region;
import net.rithms.riot.constant.staticdata.ChampData;
import net.rithms.riot.dto.Champion.Champion;
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

    //현재 LOL에 있는 챔피언 아이디 값과 이름값 디비에 저장
    public List<ChampionEntity> list() throws RiotApiException {
        RiotApi api = new RiotApi("RGAPI-e8372943-2ac3-4bed-8d2c-86f9c86174fe");
        ChampionList championList;
        championList = api.getDataChampionList(Region.KR, "", "", false, ChampData.ALL);
        System.out.print(championList.getKeys().values());
        Iterator<String> iterator = championList.getKeys().values().iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            championEntityRepository.save(ChampionEntity.of(championList.getData().get(s).getId(), championList.getData().get(s).getName()));
        }
        return championEntityRepository.findAll();
    }
}
