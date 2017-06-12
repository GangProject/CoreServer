package com.gang.domain.GameLine;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by seungki on 2017-05-23.
 */
public interface Mid2Repository extends JpaRepository<Mid2,Integer>{
    Mid2 findByplayerid(long id);
}
