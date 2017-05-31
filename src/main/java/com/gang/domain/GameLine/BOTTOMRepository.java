package com.gang.domain.GameLine;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by seungki on 2017-05-31.
 */
public interface BOTTOMRepository extends JpaRepository<BOTTOM,Integer> {
    BOTTOM findByPlayerid(long id);
}
