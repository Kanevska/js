package database.connectionFactory;

import org.apache.log4j.Logger;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static metadata.ErrorMessage.CONNECTION_FACTORY_ERROR;


public class HibernateConnectionFactory {

    private static final Logger LOGGER = Logger.getLogger(HibernateConnectionFactory.class);
    private static SessionFactory sessionFactory = null;

    public static void initConnectionFactory(){

        try{
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        }catch (MappingException e){
             LOGGER.error(CONNECTION_FACTORY_ERROR,e);
        }
    }

    public static Session getSession(){
        initConnectionFactory();
        if (sessionFactory == null){
            initConnectionFactory();
        }
        return sessionFactory.openSession();
    }

    public static void destroy(){
        sessionFactory.close();
        sessionFactory=null;
    }
}
