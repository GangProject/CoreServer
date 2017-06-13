package com.gang.core.manager;

import com.gang.core.RiotApiManager;
import net.rithms.riot.api.RateLimitException;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Static.ItemList;
import org.springframework.stereotype.Component;

/**
 * Created by Seunki on 2017-04-07.
 */
@Component
public class ItemApiManager extends RiotApiManager {

    public ItemApiManager() throws Exception{
        super();
    }

    public ItemList getDataItemList(Region region) throws Exception{
        ItemList itemList=null;
        Boolean success = false;
        while(!success){ //key 횟수 초과될 수 있으므로 , 돌리기용.
            System.out.println(key.toString());
            try {
                itemList = api.getDataItemList(Region.KR);
                success = true;
            } catch (RateLimitException e) { //key 요청 횟수 초과시
                System.out.println("key 바꿈");
                changeKey();
                Thread.sleep(1000); //1초 동안 sleep
            } catch (RiotApiException e) {
                System.out.println(e);
                changeKey();
                Thread.sleep(1000);
            }
        }

        return itemList;
    }
}
