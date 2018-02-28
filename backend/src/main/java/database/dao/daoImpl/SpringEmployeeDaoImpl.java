package database.dao.daoImpl;


import database.dao.EmployeeDao;
import database.dao.daoFactory.MysqlDaoFactory;
import entity.Employee;
import exception.DatabaseException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static metadata.ErrorMessage.CAN_NOT_ADD_OR_UPDATE;

@Repository("employeeDao")
public class SpringEmployeeDaoImpl implements EmployeeDao {

    private static final Logger LOGGER = Logger.getLogger(SpringEmployeeDaoImpl.class);
    private static SpringEmployeeDaoImpl instance = null;

    private ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    private SessionFactory sessionFactory = (SessionFactory) ctx.getBean("hibernateSessionFactory");


    public static synchronized SpringEmployeeDaoImpl getInstance() {
        if (instance == null) {
            instance = new SpringEmployeeDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Employee> getEmployeeListByDepartmentId(Integer departmentId) {
        try {
            HibernateDepartmentDaoImpl hd = (HibernateDepartmentDaoImpl) new MysqlDaoFactory().getDepartmentDao();
            return hd.getDepartmentById(departmentId).getEmployees();
        } catch (HibernateException e) {
            LOGGER.error("Department error", e);
        }
        return null;
    }

    @Override
    public void deleteEmployeeById(Integer employeeId) {
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Employee employee = getEmployeeById(employeeId);
            System.out.println();
            if (employee != null) {
                session.delete(employee);
            } else {
                throw new DatabaseException("Employee doesn't exist");
            }
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error(CAN_NOT_ADD_OR_UPDATE, e);
        }
    }


    @Override
    public Employee getEmployeeById(Integer employeeId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Employee.class, employeeId);
        } catch (HibernateException e) {
            LOGGER.error("Employee getting error", e);
        }
        return null;

    }

    @Override
    public void updateInsertEmployee(Employee employee) {
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error(CAN_NOT_ADD_OR_UPDATE, e);
        }
    }


    @Override
    public Employee getEmployeeByEmail(String email) {
        Employee employee = new Employee();
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Employee where  email = ?1");
            System.out.println();
            query.setParameter(1, email);
            employee = (Employee) query.uniqueResult();
        } catch (HibernateException e) {
            LOGGER.error("employee getting error", e);
        }
        return employee;
    }
}
