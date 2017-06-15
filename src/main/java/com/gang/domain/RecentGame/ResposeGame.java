package com.gang.domain.RecentGame;

import com.gang.domain.Player.PlayerEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.rithms.riot.dto.Game.Player;

import java.util.List;

/**
 * Created by seungki on 2017-04-23.
 */
@Builder
@Getter
@Setter
public class ResposeGame {
   private GameEntity gameEntity;
   private List<PlayerEntity> playerEntity;
   private String success;
   public static ResposeGame of(GameEntity g,List<PlayerEntity> p){
       return builder()
               .gameEntity(g)
               .playerEntity(p)
               .success("성공")
               .build();
   }

    public static ResposeGame ofF(GameEntity g,List<PlayerEntity> p){
        return builder()
                .gameEntity(null)
                .playerEntity(null)
                .success("소환사를 찾을수 없습니다.")
                .build();
    }

}
