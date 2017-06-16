package com.gang.domain.Mastery;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by seungki on 2017-06-12.
 */
public interface MasteryEntityRepository extends JpaRepository<MasteryEntity,Integer> {

    MasteryEntity findByMid(int id);
}
