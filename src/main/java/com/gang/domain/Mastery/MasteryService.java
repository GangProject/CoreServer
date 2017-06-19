package com.gang.domain.Mastery;

import com.gang.core.manager.GameApiManager;
import com.gang.core.manager.ItemApiManager;
import com.gang.core.manager.MasteryApiManager;
import com.gang.core.manager.SummonerApiManager;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Static.*;
import net.rithms.riot.dto.Static.Mastery;
import net.rithms.riot.dto.Summoner.MasteryPage;
import net.rithms.riot.dto.Summoner.MasteryPages;
import net.rithms.riot.dto.Summoner.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
    private MasteryApiManager masteryApiManager;

    @Autowired
    private SummonerApiManager summonerApiManager;

    @Autowired
    private  MasteryEntityRepository masteryEntityRepository;

    public List<MasteryEntity> save() throws Exception{
        MasteryList m = gameApiManager.getMastery(Region.KR);
        List<MasteryEntity> mast = masteryEntityRepository.findAll();
        Iterator<net.rithms.riot.dto.Static.Mastery> m1=m.getData().values().iterator();
        if(mast.size()==0) {
            while (m1.hasNext()) {
                Mastery m2 = m1.next();
                masteryEntityRepository.save(MasteryEntity.of(m2));
            }
        }else{
            for(MasteryEntity m3 : mast){
               masteryEntityRepository.delete(m3);
            }
            while (m1.hasNext()) {
                Mastery m2 = m1.next();
                masteryEntityRepository.save(MasteryEntity.of(m2));
            }
        }
        List<MasteryEntity> m2 = masteryEntityRepository.findAll();
       return m2;
    }
    public List<MasteryCountDto> Summoner(String name) throws Exception{
        Summoner summoner = summonerApiManager.getSummonerByName(Region.KR,name);
        MasteryPages k = masteryApiManager.masteryPages(summoner.getId());
        List<MasteryCountDto> mlist = new ArrayList<>();
        Iterator<MasteryPage> mp = k.getPages().iterator();
        List<MasteryNameDto> fe = null;
        List<MasteryNameDto> de = null;
        List<MasteryNameDto> re = null;

        while (mp.hasNext()) {
            fe = new ArrayList<>();
            de = new ArrayList<>();
            re = new ArrayList<>();
            MasteryPage mp2 = mp.next();
            System.out.println(mp2.getName()+"이름");
            if (mp2.getMasteries()!=null) {
                Iterator<net.rithms.riot.dto.Summoner.Mastery> mp3 = mp2.getMasteries().iterator();
                int fecount=0;
                int decount=0;
                int recount=0;
                while (mp3.hasNext()) {
                    int ch = 0;
                    System.out.println(mp2.getName()+"이름");
                    net.rithms.riot.dto.Summoner.Mastery m4 = mp3.next();
                    MasteryEntity mast = masteryEntityRepository.findByMid(m4.getId());
                    System.out.println("여기");
                    for (Ferocity f : Ferocity.values()) {
                        if (f.getFname().equals(mast.getName())) {
                            ch = 1;
                            fe.add(MasteryNameDto.ofFe(mast.getName(),m4.getRank()));
                            fecount+=m4.getRank();
                        }
                    }
                    if (ch != 1) {
                        for (Deceit d : Deceit.values()) {
                            if (d.getDn().equals(mast.getName())) {
                                ch = 1;
                                de.add(MasteryNameDto.ofDe(mast.getName(),m4.getRank()));
                                decount+=m4.getRank();
                            }
                        }
                    }
                    if (ch != 1) {
                        for (Resolution r : Resolution.values()) {
                            if (r.getRn().equals(mast.getName())) {
                                ch = 1;
                                re.add(MasteryNameDto.ofRe(mast.getName(),m4.getRank()));
                                recount+=m4.getRank();
                            }
                        }
                    }
                }
                MasteryListDto ferocity = MasteryListDto.of("흉포", fecount, fe);
                MasteryListDto deceit = MasteryListDto.of("책략", decount, de);
                MasteryListDto resolution = MasteryListDto.of("결의", recount, re);
                String masteryName=null;
                if(fecount==18){
                    masteryName=fconfirm(fe);
                }
                if(decount==18){
                    masteryName=dconfirm(de);
                }
                if(recount==18){
                    masteryName=rconfirm(re);
                }
                MasteryCountDto mac = MasteryCountDto.of(mp2.getName(),masteryName,ferocity, deceit, resolution);
                mlist.add(mac);
            }else{
                MasteryListDto ferocity = MasteryListDto.of("흉포", fe.size(), fe);
                MasteryListDto deceit = MasteryListDto.of("책략", de.size(), de);
                MasteryListDto resolution = MasteryListDto.of("결의", re.size(), re);
                MasteryCountDto mac = MasteryCountDto.of(mp2.getName(),"없음",ferocity, deceit, resolution);
                mlist.add(mac);
            }
            System.out.println("여기1");
        }
        return mlist;
    }
    public String fconfirm(List<MasteryNameDto> h){
        String name=null;
        for(MasteryNameDto M : h){
            if(M.getName().equals("전쟁광의 환희")){
                name="전쟁광의 환희";
            }
            if(M.getName().equals("전투의 열광")){
                name="전투의 열광";
            }
            if(M.getName().equals("죽음불꽃 손길")){
                name="죽음불꽃 손길";
            }
        }
        return name;
    }

    public String dconfirm(List<MasteryNameDto> h){
        String name=null;
        for(MasteryNameDto M : h){
            if(M.getName().equals("폭풍전사의 포효")){
                name="폭풍전사의 포효";
            }
            if(M.getName().equals("천둥군주의 호령")){
                name="천둥군주의 호령";
            }
            if(M.getName().equals("바람술사의 축복")){
                name="바람술사의 축복";
            }
        }
        return name;
    }

    public String rconfirm(List<MasteryNameDto> h){
        String name=null;
        for(MasteryNameDto M : h){
            if(M.getName().equals("착취의 손아귀")){
                name="착취의 손아귀";
            }
            if(M.getName().equals("파괴전차의 용기")){
                name="파괴전차의 용기";
            }
            if(M.getName().equals("굳은 약속")){
                name="굳은 약속";
            }
        }
        return name;
    }

}
