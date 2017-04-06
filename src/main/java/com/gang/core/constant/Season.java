package com.gang.core.constant;

/**
 * Created by Junwoo on 2017-04-05.
 */
public enum Season
{
    //Season
    SEASON3("SEASON3"),
    SEASON4("SEASON4"),
    SEASON5("SEASON5"),
    SEASON6("SEASON6"),
    SEASON7("SEASON7"),

    //Current Season
    CURRENT("SEASON2017");

    private String season;

    Season(String season){
        this.season = season;
    }

    public String getName(){
        return season;
    }
    @Override
    public String toString(){
        return getName();
    }
}
