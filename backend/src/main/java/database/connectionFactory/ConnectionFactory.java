package database.connectionFactory;


import exception.ApplicationException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static metadata.LoggerConst.CONNECTION_ERROR;
import static metadata.LoggerConst.ERR_CAN_NOT_OBTAIN_FILE_WITH_DATABASE_PROPERTIES;

public class ConnectionFactory {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);

    private static ConnectionFactory instance = null;
    private MysqlDataSource dataSource = new MysqlDataSource();



    public static synchronized ConnectionFactory getInstance(){
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }

    private ConnectionFactory() {
        Properties properties = new Properties();
        FileInputStream fileInputStream;

        try {
            fileInputStream = new FileInputStream("/home/akanievska/IdeaProjects/departments/src/main/resources/db.properties");
        } catch (FileNotFoundException ex) {
            LOGGER.error(ERR_CAN_NOT_OBTAIN_FILE_WITH_DATABASE_PROPERTIES, ex);
            throw new ApplicationException(ERR_CAN_NOT_OBTAIN_FILE_WITH_DATABASE_PROPERTIES, ex);
        }

        try {
            properties.load(fileInputStream);
            dataSource.setURL((properties.getProperty("MYSQL_DB_URL")));
            dataSource.setUser((properties.getProperty("MYSQL_DB_USERNAME")));
            dataSource.setPassword((properties.getProperty("MYSQL_DB_PASSWORD")));
        } catch (Exception e) {
            LOGGER.error(CONNECTION_ERROR, e);
        }finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                LOGGER.error("Close error");
            }
        }

    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException ex) {
            LOGGER.error(CONNECTION_ERROR, ex);
            ex.printStackTrace();

        }
        return con;
    }

}