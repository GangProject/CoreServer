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
@Table( name = "TOP")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TOP {

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
    private long player_id;
}
