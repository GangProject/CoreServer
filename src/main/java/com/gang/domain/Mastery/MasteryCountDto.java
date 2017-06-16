package com.gang.domain.Mastery;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * Created by seungki on 2017-06-16.
 */
@Getter
@Setter
@Builder
public class MasteryCountDto {
    String name;
    MasteryListDto ferocity;
    MasteryListDto deceit;
    MasteryListDto resolution;
    String pointMastery;

    public static MasteryCountDto of(String name,String pointMastery,MasteryListDto f,MasteryListDto d, MasteryListDto r){
        return builder()
                .name(name)
                .pointMastery(pointMastery)
                .ferocity(f)
                .deceit(d)
                .resolution(r)
                .build();
    }
}
