package com.gang.domain.Rune;

import lombok.*;
import net.rithms.riot.dto.Static.RuneList;

import javax.persistence.*;

/**
 * Created by seungki on 2017-04-28.
 */
@Entity
@Getter
@Setter
@Data
@Table( name = "RuneEnity")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "runid")
    private long runid;

    @Column(name = "runname")
    private String name;

    public static RuneEntity of(long id,String name){
        return builder()
                .name(name)
                .runid(id)
                .build();
    }
}
