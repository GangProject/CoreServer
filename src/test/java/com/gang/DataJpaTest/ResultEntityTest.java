package com.gang.DataJpaTest;

import com.gang.domain.result.ResultEntity;
import com.gang.domain.result.ResultRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by Junwoo on 2017-04-28.
 */
@RunWith(SpringRunner.class)
@org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
public class ResultEntityTest {
    @Autowired
    private ResultRepository resultRepository;

    private ResultEntity resultEntity;

    @Before
    public void setup() throws Exception{
        resultEntity = ResultEntity.test(99999,"Bvest","Diamond","I",100,30,75);
    }

    @Test
    public void insertAndSelect() throws Exception{
        ResultEntity beforeEntity = resultRepository.save(resultEntity);
        ResultEntity afterEntity = resultRepository.findBySummonerId(resultEntity.getSummonerId());
        assertThat(beforeEntity.getName(),is(afterEntity.getName()));
    }
}
