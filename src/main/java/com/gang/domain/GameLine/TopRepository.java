package com.gang.domain.GameLine;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by seungki on 2017-05-23.
 */
public interface TopRepository extends JpaRepository<Top,Integer>{
    Top findByplayerid(long id);
}
