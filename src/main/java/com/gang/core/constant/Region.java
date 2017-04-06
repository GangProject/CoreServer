package com.gang.core.constant;

import lombok.Getter;

/**
 * Created by Junwoo on 2017-04-05.
 */
@Getter
public enum Region {
    //국가 , 필요하면 더 추가할 것.
    JP("jp.api.pvp.net","jp"),
    KR("kr.api.pvp.net","kr"),
    GLOBAL("global.api.pvp.net","global");

    private String endpoint; //엔드 포인트
    private String region; //국가

    Region(String endpoint,String region){
        this.endpoint = endpoint;
        this.region = region;
    }

    public static Region getRegionByName(String name){
        for(Region region : Region.values()){
            if(region.getRegion().equals(name.toLowerCase())){
                return region;
            }
        }
    }

    @Override
    public String toString(){
        return getRegion();
    }
}
