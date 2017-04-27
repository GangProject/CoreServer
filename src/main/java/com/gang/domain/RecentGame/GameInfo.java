package com.gang.domain.RecentGame;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by seungki on 2017-04-27.
 */
@Getter
@Setter
@Builder
public class GameInfo {
    private String winner;
    private List<GamePlayer> list;

    public static GameInfo of(List<GamePlayer> list,String name){
        return GameInfo.builder()
                .list(list)
                .winner(name)
                .build();
    }
}
