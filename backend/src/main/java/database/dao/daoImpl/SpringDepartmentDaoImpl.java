package database.dao.daoImpl;

import database.dao.DepartmentDao;
import entity.Department;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static metadata.ErrorMessage.CAN_NOT_ADD_OR_UPDATE;
import static metadata.ErrorMessage.DELETE_ERROR;


@Repository("departmentDao")
public class SpringDepartmentDaoImpl implements DepartmentDao {

    private static final Logger LOGGER = Logger.getLogger(SpringDepartmentDaoImpl.class);
    private static SpringDepartmentDaoImpl instance = null;

    private ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    private SessionFactory sessionFactory = (SessionFactory) ctx.getBean("hibernateSessionFactory");


    public static synchronized SpringDepartmentDaoImpl getInstance() {
        if (instance == null) {
            instance = new SpringDepartmentDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> list = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            list = session.createQuery("from Department ", Department.class).list();
        } catch (HibernateException e) {
            LOGGER.error("Department getting error", e);
        }
        return list;
    }

    @Override
    public void deleteDepartment(Integer departmentId) {


        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Department department = getDepartmentById(departmentId);
            session.delete(department);
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error(CAN_NOT_ADD_OR_UPDATE, e);
        }
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        Department department = new Department();
        try (Session session = sessionFactory.openSession()) {
            department = session.get(Department.class, departmentId);
        } catch (HibernateException e) {
            LOGGER.error("Department getting error", e);
        }
        return department;

    }

    @Override
    public void insertUpdateDepartment(Department department) {
        Transaction transaction;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(department);
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error(DELETE_ERROR);
        }
    }

    @Override
    public Department getDepartmentByName(String name) {
        Department department = new Department();
        System.out.println("From getDepartmentByName");
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Department where  departmentName = ?1");
            query.setParameter(1, name);
            department = (Department) query.uniqueResult();
        } catch (HibernateException e) {
            LOGGER.error("Department getting error", e);
            e.printStackTrace();
        }
        return department;
    }


}
