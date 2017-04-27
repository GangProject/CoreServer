package com.gang.constant;

import java.util.NoSuchElementException;

/**
 * Created by Junwoo on 2017-04-27.
 */
public enum Region {
    BR("br.api.pvp.net", "br"),
    EUNE("eune.api.pvp.net", "eune"),
    EUW("euw.api.pvp.net", "euw"),
    JP("jp.api.pvp.net", "jp"),
    KR("kr.api.pvp.net", "kr"),
    LAS("las.api.pvp.net", "las"),
    LAN("lan.api.pvp.net", "lan"),
    NA("na.api.pvp.net", "na"),
    OCE("oce.api.pvp.net", "oce"),
    PBE("pbe.api.pvp.net", "pbe"),
    RU("ru.api.pvp.net", "ru"),
    TR("tr.api.pvp.net", "tr"),
    GLOBAL("global.api.pvp.net", "global");

    private String endpoint;
    private String region;

    public static Region getRegionByName(String name) {
        for (Region region : Region.values()) {
            if (region.getName().equals(name.toLowerCase())) {
                return region;
            }
        }
        throw new NoSuchElementException("Unknown region name: " + name);
    }

    public static Region getRegionByPlatformId(Platform platformId) {
        return getRegionByName(platformId.getName());
    }

    Region(String endpoint, String region) {
        this.endpoint = endpoint;
        this.region = region;
    }

    public String getEndpoint(boolean withRegion) {
        String url = "https://" + endpoint + "/api/lol/";
        if (withRegion) {
            url += getName();
        }
        return url;
    }

    public String getEndpoint() {
        return getEndpoint(true);
    }

    public String getName() {
        return region;
    }

    @Override
    public String toString() {
        return getName();
    }
}