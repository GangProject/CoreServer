package com.gang.domain.ITEM;

import com.gang.core.manager.ItemApiManager;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.Static.Item;
import net.rithms.riot.dto.Static.ItemList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by seungki on 2017-04-07.
 */
@Service
public class ItemService {

    @Autowired
    private ItemApiManager itemApiManager;

    @Autowired
    private ItemEntityRepository itemEntityRepository;

    public List<ItemEntity> recentItem() throws Exception{
        ItemList itemList=itemApiManager.getDataItemList(Region.KR);
        Iterator<Item> iterator=itemList.getData().values().iterator();
        List<ItemEntity> it = itemEntityRepository.findAll();
        if(it.size()==0){
            while(iterator.hasNext()){
                Item item =iterator.next();
                itemEntityRepository.save(ItemEntity.of(item));
            }
        }else if(it.size()!=itemList.getData().values().size()){
            for(ItemEntity item:it){
                itemEntityRepository.delete(item);
            }
            while(iterator.hasNext()){
                Item item =iterator.next();
                itemEntityRepository.save(ItemEntity.of(item));
            }
        }
        return itemEntityRepository.findAll();
    }
}
