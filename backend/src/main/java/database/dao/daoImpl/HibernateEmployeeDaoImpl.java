package database.dao.daoImpl;

import database.connectionFactory.HibernateConnectionFactory;
import database.dao.EmployeeDao;
import database.dao.daoFactory.MysqlDaoFactory;
import entity.Employee;
import exception.DatabaseException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static metadata.ErrorMessage.CAN_NOT_ADD_OR_UPDATE;

public class HibernateEmployeeDaoImpl implements EmployeeDao {

    private static final Logger LOGGER = Logger.getLogger(HibernateEmployeeDaoImpl.class);

    private static HibernateEmployeeDaoImpl instance = null;


    public static synchronized HibernateEmployeeDaoImpl getInstance() {
        if (instance == null) {
            instance = new HibernateEmployeeDaoImpl();
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

        Transaction transaction = null;
        try (Session session = HibernateConnectionFactory.getSession()) {
            transaction = session.beginTransaction();
            Employee employee = getEmployeeById(employeeId);
            if(employee!= null) {
                session.delete(employee);
                transaction.commit();
            }
            else{
                throw new DatabaseException("Employee doesn't exist");
            }
        } catch (HibernateException e) {
            LOGGER.error(CAN_NOT_ADD_OR_UPDATE, e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    @Override
    public Employee getEmployeeById(Integer employeeId) {
        try (Session session = HibernateConnectionFactory.getSession()) {
            return session.get(Employee.class, employeeId);
        } catch (HibernateException e) {
            LOGGER.error("Employee getting error", e);
        }
        return null;

    }

    @Override
    public void updateInsertEmployee(Employee employee) {


        Transaction transaction = null;
        try (Session session = HibernateConnectionFactory.getSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error(CAN_NOT_ADD_OR_UPDATE, e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    @Override
    public Employee getEmployeeByEmail(String email) {
        Employee employee  = new Employee();
        try (Session session = HibernateConnectionFactory.getSession()) {
            Query query = session.createQuery("from Employee where  email = ?1");
            query.setParameter(1, email);
            employee= (Employee) query.uniqueResult();
        } catch (HibernateException e) {
            LOGGER.error("employee getting error", e);
        }
      return employee;
    }
}