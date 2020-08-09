package hibernate.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Configuration {
    private static Logger logger = LoggerFactory.getLogger(Configuration.class);
    private EntityManagerFactory entityManagerFactory;

    public Configuration() {
        entityManagerFactory = Persistence.createEntityManagerFactory("hibernate.entity");
    }

    public void close() {
        entityManagerFactory.close();
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}