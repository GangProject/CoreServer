package com.gang.domain.AggregateStats;

import java.util.List;
import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Junwoo on 2017-04-28.
 */
@Getter
@Setter
@Builder
public class AggregateExpertsResponseDto {
    private List<AggregateExpertsDto> experts;
    private int resultCount;

    public static AggregateExpertsResponseDto Empty(){
        return AggregateExpertsResponseDto.builder()
                .experts(Lists.newArrayList())
                .resultCount(0)
                .build();
    }

    public static AggregateExpertsResponseDto of(List<AggregateExpertsDto> aggregateExpertsDtos){
        return AggregateExpertsResponseDto.builder()
                .experts(aggregateExpertsDtos)
                .resultCount(aggregateExpertsDtos.size())
                .build();
    }
}
