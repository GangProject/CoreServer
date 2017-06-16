package com.gang.domain.Mastery;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.rithms.riot.dto.Static.MasteryList;

import java.util.HashMap;
import java.util.List;

/**
 * Created by seungki on 2017-06-16.
 */
@Getter
@Setter
@Builder
public class MasteryListDto {
    String name;
    int number;
    List<MasteryNameDto> mastery;


    public static MasteryListDto of(String name,int number,List<MasteryNameDto> h){
        return builder()
                .name(name)
                .number(number)
                .mastery(h)
                .build();
    }



}

