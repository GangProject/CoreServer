package com.gang.domain.summoner;

import com.gang.api.common.Dto;
import com.gang.core.AnalyzeUtil;
import com.gang.domain.result.ResultEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Junwoo on 2017-06-12.
 */
@Getter
@Setter
@Builder
public class MmrDto implements Dto {
    private int mmr;
    private String tier;

    public static MmrDto of(int mmr, String tier) {
        return MmrDto.builder()
                .mmr(mmr)
                .tier(tier)
                .build();
    }
}
