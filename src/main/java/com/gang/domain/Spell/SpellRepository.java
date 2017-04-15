package com.gang.domain.Spell;


import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by seungki on 2017-04-15.
 */
public interface SpellRepository  extends JpaRepository<SpellEntity,Integer> {
    SpellEntity findBySpellid(int id);
}
