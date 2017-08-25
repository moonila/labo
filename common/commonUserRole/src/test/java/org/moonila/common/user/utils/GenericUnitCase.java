package org.moonila.common.user.utils;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:commonUserRole-spring-test.xml","classpath*:commonUserRole-hibernate-test.xml","classpath*:genericDao-spring.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@Ignore
public class GenericUnitCase extends Assert {

    public GenericUnitCase() {
        super();
    }

    

}