package com.gang.domain.Mastery;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by seungki on 2017-06-16.
 */
@Getter
@Setter
@Builder
public class MasteryNameDto {
    String name;
    int number;

    public static MasteryNameDto ofFe(String name,int number){
        return builder()
                .name(name)
                .number(number)
                .build();
    }
    public static MasteryNameDto ofDe(String name,int number){
        return builder()
                .name(name)
                .number(number)
                .build();
    }
    public static MasteryNameDto ofRe(String name,int number){
        return builder()
                .name(name)
                .number(number)
                .build();
    }
}
