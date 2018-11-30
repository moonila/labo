package org.moonila.common.user.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.moonila.common.ApplicationTest;
import org.moonila.common.user.dao.bo.BaseUserBO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@Transactional
public class UserDaoImplTest extends ApplicationTest {


	@Before
	public void init() throws Exception {
		BaseUserBO user1 = new BaseUserBO();
		user1.setLogin("toto");
		user1.setPassword("toto");
		user1.setName("toto");
		BaseUserBO user2 = new BaseUserBO();
		user2.setLogin("titi");
		user2.setPassword("toto");
		user2.setName("titi");

		getUserDao().save(user1);
		getUserDao().save(user2);
	}

	@After
	public void destroy() {
	
	}
	
	@Test
	public void testGetALLKeyWords() {
		BaseUserBO toto =  getUserDao().getUserByLogin("toto");
		System.out.println("je regarde si toto à quelque chose " +toto.getLogin());
		List<BaseUserBO> getAllUsers = getUserDao().findAll();
		assertEquals(getAllUsers.size(), 2);
	}

	public long nbj(int size) {
		if (size > 1) {
			return size - 1;
		} else {
			return 28;
		}
	}

	@Test
	public void testGetSize() {

		long toto = nbj(1);
		System.out.println("toto : " + toto);

		toto = nbj(0);
		System.out.println("toto : " + toto);

		toto = nbj(2);
		System.out.println("toto : " + toto);

	}

}
