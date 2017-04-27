package com.gang.DataJpaTest;

import com.gang.domain.summoner.SummonerEntity;
import com.gang.domain.summoner.SummonerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by Junwoo on 2017-04-26.
 */
@RunWith(SpringRunner.class)
@org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
public class SummonerEntityTest {
    @Autowired
    private SummonerRepository summonerRepository;

    private SummonerEntity summonerEntity;

    @Before
    public void setup() throws Exception{
      summonerEntity = SummonerEntity.test(99999,"Bvest",1596,1429555,30);
    }

    @Test
    public void insertAndSelect() throws Exception{
        SummonerEntity beforeEntity = summonerRepository.save(summonerEntity);
        SummonerEntity afterEntity = summonerRepository.findBySummonerId(summonerEntity.getSummonerId());
        assertThat(beforeEntity.getName(),is(afterEntity.getName()));
    }
}
