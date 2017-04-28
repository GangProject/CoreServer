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

    private boolean win;
    private long dragonkill;
    private long towerkill;
    private long baronkill;
    private List<GamePlayer> list;



    public static GameInfo of(List<GamePlayer> list,boolean win,long dragonkill,long towerkill,long baronkill){
        return GameInfo.builder()
                .list(list)
                .win(win)
                .dragonkill(dragonkill)
                .towerkill(towerkill)
                .baronkill(baronkill)
                .build();
    }
}
