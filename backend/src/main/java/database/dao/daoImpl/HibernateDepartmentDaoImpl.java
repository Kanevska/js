package database.dao.daoImpl;

import database.connectionFactory.HibernateConnectionFactory;
import database.dao.DepartmentDao;
import entity.Department;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static metadata.ErrorMessage.CAN_NOT_ADD_OR_UPDATE;
import static metadata.ErrorMessage.DELETE_ERROR;


public class HibernateDepartmentDaoImpl implements DepartmentDao {

    private static final Logger LOGGER = Logger.getLogger(HibernateDepartmentDaoImpl.class);

    private static HibernateDepartmentDaoImpl instance = null;


    public static synchronized HibernateDepartmentDaoImpl getInstance() {
        if (instance == null) {
            instance = new HibernateDepartmentDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> list = new ArrayList<>();
        try (Session session = HibernateConnectionFactory.getSession()) {
            Query q = session.createQuery("from Department ", Department.class);
            list = (List<Department>) q.list();
        } catch (HibernateException e) {
            LOGGER.error("Department getting error", e);
        }
        return list;
    }

    @Override
    public void deleteDepartment(Integer departmentId) {

        Transaction transaction = null;
        try (Session session = HibernateConnectionFactory.getSession()) {
            transaction = session.beginTransaction();
            Department department = getDepartmentById(departmentId);
            session.delete(department);
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error(CAN_NOT_ADD_OR_UPDATE, e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        Department department = new Department();
        try (Session session = HibernateConnectionFactory.getSession()) {
            department = session.get(Department.class, departmentId);
        } catch (HibernateException e) {
            LOGGER.error("Department getting error", e);
        }
        return department;

    }

    @Override
    public void insertUpdateDepartment(Department department) {

        Transaction transaction = null;
        try (Session session = HibernateConnectionFactory.getSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(department);
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error(DELETE_ERROR);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Department getDepartmentByName(String name) {
        Department department = new Department();
        try (Session session = HibernateConnectionFactory.getSession()) {
            Query query = session.createQuery("from Department where  departmentName = ?1");
            query.setParameter(1, name);
            department = (Department) query.uniqueResult();
        } catch (HibernateException e) {
            LOGGER.error("Department getting error", e);
        }
        return department;
    }


}
