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
@Table( name = "JUNGGLE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JUNGGLE{
    @Id
    @Column(name= "junggle_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int junggle_id;

    @Column(name="total_Game")
    private int totalGame;

    @Column(name="win")
    private int win;

    @Column(name="rose")
    private int rose;

    @Column(name="playerId")
    private long player_id;
}
