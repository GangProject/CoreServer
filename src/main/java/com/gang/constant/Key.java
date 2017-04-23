package com.gang.constant;

import com.gang.core.StringNotFoundException;
import net.rithms.riot.api.RiotStringNotFound;

/**
 * Created by Junwoo on 2017-04-07.
 */
public enum Key {
    KEYONE(0,"RGAPI-e8372943-2ac3-4bed-8d2c-86f9c86174fe"),
    KEYTWO(1,"RGAPI-342e18e7-3f6f-42ca-8eaf-063956bd50ad"),
    KEYTHREE(2,"RGAPI-3a7ff858-0698-4186-8093-708a986da6dc");

    private int id;
    private String key;

    public static Key getKeyById(int id) throws StringNotFoundException{
        for(Key key : Key.values()){
            if(key.getId()==id){
                return key;
            }
        }
        throw new StringNotFoundException("해당 API KEY를 찾을 수 없습니다 key id : "+ id);
    }

    Key(int id,String key){
        this.id = id;
        this.key = key;
    }

    public int getId(){
        return id;
    }

    public String getKey(){
        return key;
    }

    public static int getLength(){
        return Key.values().length;
    }

    @Override
    public String toString(){
        return getId()+" "+getKey();
    }
}
