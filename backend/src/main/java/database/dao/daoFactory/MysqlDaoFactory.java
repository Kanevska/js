package database.dao.daoFactory;


import database.dao.DepartmentDao;

import database.dao.EmployeeDao;
import database.dao.daoImpl.HibernateDepartmentDaoImpl;
import database.dao.daoImpl.HibernateEmployeeDaoImpl;


public class MysqlDaoFactory implements DaoFactory {



    @Override
    public DepartmentDao getDepartmentDao() {
        return HibernateDepartmentDaoImpl.getInstance();
    }



    @Override
    public EmployeeDao getEmployeeDao() {
        return HibernateEmployeeDaoImpl.getInstance();
    }
}
