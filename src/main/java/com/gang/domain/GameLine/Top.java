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
@Table( name = "Top")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Top{

    @Id
    @Column(name= "top_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int top_id;

    @Column(name="total_Game")
    private int totalGame;

    @Column(name="win")
    private int win;

    @Column(name="rose")
    private int rose;

    @Column(name="playerId")
    private long playerid;

    public static Top of_p_w(long id){
        return builder()
                .playerid(id)
                .totalGame(1)
                .win(1)
                .rose(0)
                .build();
    }
    public static Top of_p_r(long id){
        return builder()
                .playerid(id)
                .totalGame(1)
                .win(0)
                .rose(1)
                .build();
    }
}
