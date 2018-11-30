package org.moonila.common.user.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.moonila.common.user.core.service.UserManager;
import org.moonila.common.user.core.to.BaseUserTO;
import org.moonila.common.user.dao.bo.BaseUserBO;
import org.moonila.common.user.dao.persistence.UserDAO;
import org.moonila.common.user.utils.GenericUnitCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class UserManagerImplTest extends GenericUnitCase{

	
	@Autowired
	@Qualifier("userDao")
	private UserDAO userDao;

	@Autowired
	@Qualifier("userManager")
	private UserManager userManager;
	
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

		userDao.save(user1);
		userDao.save(user2);
	}
	
	@Test
	public void getAllUsersTest(){
		List<BaseUserTO> allUsers = userManager.getAllObject();
		assertEquals(2,allUsers.size());
	}
}
