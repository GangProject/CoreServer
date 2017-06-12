package com.gang.domain.ITEM;

import lombok.*;
import net.rithms.riot.dto.Static.Image;
import net.rithms.riot.dto.Static.Item;

import javax.persistence.*;

/**
 * Created by seungki on 2017-04-07.
 */
@Getter
@Setter
@Entity
@Data
@Table(name="\"ItemEntity\"")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "itemId")
    private int itemid;

    @Column(name = "name")
    private String name;



    public static ItemEntity of(Item item){
        return ItemEntity.builder()
                .name(item.getName())
                .itemid(item.getId())
                .build();
    }

}
