package com.gang.core.api.endpoints.summoner.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Junwoo on 2017-04-03.
 */
@Entity
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="summoner")
public class Summoner{

    @Id
    @Column(name="id")
    private long id;

    @Column(name="profileIconId")
    private int profileIconId;

    @Column(name="name")
    private String name;

    @Column(name="summonerLevel")
    private long summonerLevel;

    @Column(name="revisionDate")
    private long revisionDate;
}
