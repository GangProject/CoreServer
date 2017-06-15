package com.gang.constant;

import com.gang.core.StringNotFoundException;
import net.rithms.riot.api.RiotStringNotFound;

/**
 * Created by Junwoo on 2017-04-07.
 */
public enum Key {
    KEYONE(4,"RGAPI-ee2bd3aa-238e-409c-88db-24a3b745773"),
    KEYSIX(5,"RGAPI-545beb9f-67a8-472e-a7f2-ed4dae63d479 "),
    KEYTWO(3,"RGAPI-342e18e7-3f6f-42ca-8eaf-063956bd50ad"),
    KEYTHREE(2,"RGAPI-3a7ff858-0698-4186-8093-708a986da6dc"),
    KEYFOUR(1,"RGAPI-43d14c22-daa7-4869-adbf-8dc0bafe27bb"),
    KEYFIVE(0,"RGAPI-b1d04bb3-a207-47cd-9807-3939277ae77a");


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
