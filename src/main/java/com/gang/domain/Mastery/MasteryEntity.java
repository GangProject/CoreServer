package com.gang.domain.Mastery;

import lombok.*;
import net.rithms.riot.dto.Static.Mastery;

import javax.persistence.*;

/**
 * Created by seungki on 2017-06-12.
 */
@Getter
@Setter
@Entity
@Data
@Table(name="\"MasteryEntity\"")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MasteryEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "masteryid")
    private int mid;

    @Column(name = "masteryname")
    private String name;

    public static MasteryEntity of(Mastery m){
        return builder()
                .name(m.getName())
                .mid(m.getId())
                .build();
    }
}
