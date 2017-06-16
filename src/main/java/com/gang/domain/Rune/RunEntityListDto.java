package com.gang.domain.Rune;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by seungki on 2017-06-15.
 */
@Builder
@Getter
@Setter
public class RunEntityListDto {
    private String name;
    private RunEntityDto rune;

    public static RunEntityListDto of(String name,RunEntityDto rune){
        return builder()
                .name(name)
                .rune(rune)
                .build();
    }
}
