package com.gang.domain.Rune;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by seungki on 2017-06-15.
 */
@Builder
@Getter
@Setter
public class RuneEntityCountDto {

    private String name;
    private int number;
    private String descipt;

    public static RuneEntityCountDto of(String name,int number,String descipt){
        return builder()
                .name(name)
                .number(number)
                .descipt(descipt)
                .build();
    }
}
