package org.moonila.common;

import org.junit.runner.RunWith;
import org.moonila.common.user.dao.persistence.RoleDAO;
import org.moonila.common.user.dao.persistence.RoleGroupDAO;
import org.moonila.common.user.dao.persistence.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Classe de base pour lancer les tests d'intégration
 *
 * @author MINAGRI
 * @version $Id: ApplicationTest.java 49779 2018-07-05 13:39:14Z florian.girault $
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationContextTest.class})
@ComponentScan(basePackages = {"org.moonila.common"})
public abstract class ApplicationTest {


    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private RoleGroupDAO roleGroupDAO;


    public UserDAO getUserDao() {
        return userDAO;
    }
}
