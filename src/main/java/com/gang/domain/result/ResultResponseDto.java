package com.gang.domain.result;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * Created by Junwoo on 2017-04-28.
 */
@Builder
@Setter
@Getter
public class ResultResponseDto {
    private List<ResultEntity> leagues;
    private int resultCount;

    public static ResultResponseDto Empty(){
        return ResultResponseDto.builder()
                .leagues(Lists.newArrayList())
                .resultCount(0)
                .build();
    }

    public static ResultResponseDto of(List<ResultEntity> resultEntityDtos){
        return ResultResponseDto.builder()
                .leagues(resultEntityDtos)
                .resultCount(resultEntityDtos.size())
                .build();
    }
}
