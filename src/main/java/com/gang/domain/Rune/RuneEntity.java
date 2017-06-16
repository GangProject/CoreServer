package com.gang.domain.Rune;

import lombok.*;
import net.rithms.riot.dto.Static.Rune;
import net.rithms.riot.dto.Static.RuneList;

import javax.persistence.*;

/**
 * Created by seungki on 2017-04-28.
 */
@Entity
@Getter
@Setter
@Data
@Table( name = "RuneEntity")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "runid")
    private int runid;

    @Column(name = "runname")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "descript")
    private  String descript;


    public static RuneEntity of(Rune rune){
        return builder()
                .name(rune.getName())
                .runid(rune.getId())
                .type(rune.getRune().getType())
                .descript(rune.getDescription())
                .build();
    }
}
