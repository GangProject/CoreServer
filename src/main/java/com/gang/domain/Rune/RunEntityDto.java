package com.gang.domain.Rune;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by seungki on 2017-06-15.
 */@Builder
@Getter
@Setter
public class RunEntityDto {
    List<RuneEntityCountDto> red;
    List<RuneEntityCountDto> yellow;
    List<RuneEntityCountDto> blue;
    List<RuneEntityCountDto> black;

    public static RunEntityDto of(List<RuneEntityCountDto> red,List<RuneEntityCountDto> yellow,List<RuneEntityCountDto> blue,
                                  List<RuneEntityCountDto> black){
        return builder()

                .red(red)
                .blue(blue)
                .black(black)
                .yellow(yellow)
                .build();

    }
 }
