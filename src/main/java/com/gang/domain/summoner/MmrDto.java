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
    private int myMmr;
    private String myTierKor;
    private String myTierEng;
    private String myDivision;

    private int predictMmr;
    private String predictTierKor;
    private String predictTierEng;
    private String predictDivision;

    public static MmrDto of(int myMmr, String myTierKor, String myTierEng, String myDivision,
            int predictMmr, String predictTierKor,String predictTierEng, String predictDivision) {
        return MmrDto.builder()
                .myMmr(myMmr)
                .myTierKor(myTierKor)
                .myTierEng(myTierEng)
                .myDivision(myDivision)
                .predictMmr(predictMmr)
                .predictTierKor(predictTierKor)
                .predictTierEng(predictTierEng)
                .predictDivision(predictDivision)
                .build();
    }
}
