package com.gang.domain.summoner;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Junwoo on 2017-04-07.
 */
@Entity
@Getter
@Setter
@Table( name = "Summoner")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Summoner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "profileIconId")
    private String profileIconId;

    @Column(name = "revisionDate")
    private String revisionDate;

    @Column(name = "summonerLevel")
    private long summonerLevel;

    public static Summoner of(SummonerDto ){

    }
}
