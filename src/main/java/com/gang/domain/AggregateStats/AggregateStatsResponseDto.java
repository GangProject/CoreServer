package com.gang.domain.AggregateStats;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * Created by Junwoo on 2017-04-27.
 */
@Getter
@Setter
@Builder
public class AggregateStatsResponseDto {
    private List<AggregateStatsDto> stats;
    private int resultCount;

    public static AggregateStatsResponseDto Empty(){
        return AggregateStatsResponseDto.builder()
                .stats(Lists.newArrayList())
                .resultCount(0)
                .build();
    }

    public static AggregateStatsResponseDto of(List<AggregateStatsDto> aggregateStatsDtos){
        return AggregateStatsResponseDto.builder()
                .stats(aggregateStatsDtos)
                .resultCount(aggregateStatsDtos.size())
                .build();
    }
}
