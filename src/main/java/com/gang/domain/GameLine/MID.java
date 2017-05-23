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
@Table( name = "MID")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MID {

    @Id
    @Column(name= "mid_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int mid_id;

    @Column(name="total_Game")
    private int totalGame;

    @Column(name="win")
    private int win;

    @Column(name="rose")
    private int rose;

    @Column(name="playerId")
    private long player_id;
}
