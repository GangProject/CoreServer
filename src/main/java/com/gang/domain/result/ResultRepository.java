package com.gang.domain.result;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Junwoo on 2017-04-26.
 */
@Repository
public interface ResultRepository extends JpaRepository<ResultEntity,Integer>{
    ResultEntity findByName(String name);
    ResultEntity findBySummonerId(long SummonerId);
}