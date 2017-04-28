package com.gang.domain.RecentGame;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import java.util.List;

/**
 * Created by seungki on 2017-04-28.
 */
@Getter
@Setter
@Builder
public class Game_info {

    private List<GameInfo> list;

    public static Game_info of(List<GameInfo> list){
        return Game_info.builder()
                .list(list)
                .build();
    }

}
