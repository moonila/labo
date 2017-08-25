package org.moonila.common.user.dao;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.moonila.common.user.dao.bo.BaseUserBO;
import org.moonila.common.user.dao.persistence.UserDAO;
import org.moonila.common.user.utils.GenericUnitCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserDaoImplTest extends GenericUnitCase {

	@Autowired
	@Qualifier("userDao")
	private UserDAO userDao;

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

		userDao.saveOrUpdate(user1);
		userDao.saveOrUpdate(user2);
	}

	@After
	public void destroy() {
	
	}
	
	@Test
	public void testGetALLKeyWords() {
		BaseUserBO toto =  userDao.getUserByLogin("toto");
		System.out.println("je regarde si toto à quelque chose " +toto.getLogin());
		List<BaseUserBO> getAllUsers = userDao.getAll();
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
