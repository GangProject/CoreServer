package com.gang.core.api;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.logging.Level;

/**
 * Created by Junwoo & Seungki on 2017-04-05.
 */
@Getter
@Setter
public class ApiConfig implements Cloneable {

    //private int asyncRequestTimeout = 10000;
    private Level debugLevel = Level.WARNING;
    private String key = null;
    private String tournamentKey = null;

    //클로닝, 원래 객체에는 아무런 변화를 주지 않으며 복사,
    @Override
    public ApiConfig clone(){
        return new ApiConfig().setDebugLevel(getDebugLevel());
    }
    //RiotApi의 디버그 레벨 set메소드 이다. 메소드 체이닝을 사용하기 위해, 개체를 return 한다.
    public ApiConfig setDebugLevel(Level debugLevel){
        Objects.requireNonNull(debugLevel,"debug level must not be null");
        this.debugLevel = debugLevel;
        return this;
    }
}
