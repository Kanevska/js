package web.listener;

import database.connectionFactory.ConnectionFactory;
import database.dao.DepartmentDao;
import database.dao.EmployeeDao;
import database.dao.daoFactory.DaoFactory;
import database.dao.daoFactory.MysqlDaoFactory;
import metadata.AppConst;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import service.serviceImpl.DepartmentServiceImpl;
import service.serviceImpl.EmployeeServiceImpl;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static metadata.LoggerConst.*;

public class ContextListener  implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(ContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        initConnectionFactory(context);
        log4jInit();
        DaoFactory daoFactory = new MysqlDaoFactory();
        initDepartmentService(context,daoFactory);
        initEmployeeService(context,daoFactory);
        LOGGER.debug(CONTEXT_INIT);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.debug(CONTEXT_DESTROY);
    }



    private void initConnectionFactory(final ServletContext context) {
        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        context.setAttribute(AppConst.CONNECTION_FACTORY, connectionFactory);
        LOGGER.debug(CONTEXT_INIT);

    }

    private void initDepartmentService(final ServletContext context, DaoFactory daoFactory) {

        DepartmentDao departmentDao = daoFactory.getDepartmentDao();
        context.setAttribute(AppConst.DEPARTMENT_DAO, departmentDao);

        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(context);
        context.setAttribute(AppConst.DEPARTMENT_SERVICE, departmentService);
        LOGGER.debug(String.format(SERVICE_INIT, "Department service"));


    }


    private void initEmployeeService(final ServletContext context, DaoFactory daoFactory) {

        EmployeeDao employeeDao = daoFactory.getEmployeeDao();
        context.setAttribute(AppConst.EMPLOYEE_DAO, employeeDao);

        EmployeeServiceImpl employeeService = new EmployeeServiceImpl(context);
        context.setAttribute(AppConst.EMPLOYEE_SERVICE, employeeService);
        LOGGER.debug(String.format(SERVICE_INIT, "Employee service"));

    }

    private void log4jInit() {

        Properties properties = new Properties();
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream("/home/akanievska/IdeaProjects/departments/src/main/resources/log4j.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.error(CONNECTION_ERROR, e);

        }finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                LOGGER.error("Close error");
            }
        }

        PropertyConfigurator.configure(properties);

        LOGGER.debug(LOG4J_INIT);

    }
}

