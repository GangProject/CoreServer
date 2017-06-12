package com.gang.domain.Spell;

import lombok.*;
import net.rithms.riot.dto.Static.SummonerSpell;

import javax.persistence.*;

/**
 * Created by seungki on 2017-04-15.
 */
@Getter
@Setter
@Entity
@Data
@Table(name="\"SpellEntity\"")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpellEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "spellId")
    private int spellid;

    @Column(name = "name")
    private String name;

    public static SpellEntity of(SummonerSpell SummonerSpell){
        return builder()
                .name(SummonerSpell.getName())
                .spellid(SummonerSpell.getId())
                .build();

    }
}
