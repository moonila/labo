package org.moonila.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Décrire le fichier
 *
 * @author MINAGRI
 * @version $Id: $
 */

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class ApplicationContextTest  {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationTest.class, args);
    }


}
