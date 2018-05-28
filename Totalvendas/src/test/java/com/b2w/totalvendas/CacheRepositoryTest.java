package com.b2w.totalvendas;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.b2w.totalvendas.cache.CacheTotalvendas;
import com.b2w.totalvendas.cache.CacheTotalvendasRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheRepositoryTest {

	@Autowired
	CacheTotalvendasRepository repository;

	@Test
	public void saveCache() {
		CacheTotalvendas cache = new CacheTotalvendas(new Long(1), 15151.5);

		repository.save(cache);

		CacheTotalvendas cacheResponse = repository.findOne(new Long(1));

		assertEquals(cache.getId(), cacheResponse.getId());
	}

}
