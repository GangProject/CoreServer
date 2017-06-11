package com.gang.domain.GameLine;

import lombok.*;

import javax.persistence.*;

/**
 * Created by seungki on 2017-05-23.
 */
@Getter
@Setter
@Entity
@Data
@Table( name = "Junggle")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Junggle{
    @Id
    @Column(name= "junggle_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int junggle_id;

    @Column(name="total_game")
    private int totalGame;

    @Column(name="win")
    private int win;

    @Column(name="rose")
    private int rose;

    @Column(name="playerId")
    private long playerid;

    public static Junggle of_p_w(long id){
        return builder()
                .playerid(id)
                .totalGame(1)
                .win(1)
                .rose(0)
                .build();
    }
    public static Junggle of_p_r(long id){
        return builder()
                .playerid(id)
                .totalGame(1)
                .win(0)
                .rose(1)
                .build();
    }

}
