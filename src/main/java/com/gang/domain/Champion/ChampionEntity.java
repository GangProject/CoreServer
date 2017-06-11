package com.gang.domain.Champion;

import lombok.*;

import javax.persistence.*;

/**
 * Created by seungki on 2017-04-07.
 */

@Getter
@Setter
@Entity
@Data
@Table(name="ChampionEntity")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChampionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @NonNull
    private int id;

    @Column(name="name")
    @NonNull
    private String name;

    @Column(name = "championId")
    @NonNull
    private int champid;


    public static ChampionEntity of(int id,String name){
        return ChampionEntity.builder()
                .champid(id)
                .name(name)
                .build();
    }


}
