package database.dao.daoFactory;

import database.dao.DepartmentDao;
import database.dao.EmployeeDao;

public interface DaoFactory {

    DepartmentDao getDepartmentDao();

    EmployeeDao getEmployeeDao();

}
