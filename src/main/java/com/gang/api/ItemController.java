package com.gang.api;

import com.gang.domain.ITEM.ItemEntity;
import com.gang.domain.ITEM.ItemService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by seungki on 2017-04-07.
 */
@RestController
@Api(value = "아이텝 정보", description = "아이템 API", basePath= "/api/item")
@RequestMapping(value = "/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/list")
    public List<ItemEntity> list() throws Exception{
        try{
            return itemService.recentItem();
        }catch (Exception e){
            throw e;
        }
    }

}
