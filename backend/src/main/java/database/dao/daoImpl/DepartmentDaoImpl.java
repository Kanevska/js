package database.dao.daoImpl;

import database.dao.DepartmentDao;
import database.dao.util.QueryBuilder;
import entity.Department;
import metadata.DBMetadata.DepartmentsMetadata;
import metadata.DBMetadata.EmployeesMetadata;
import metadata.DBMetadata.TablesNames;
import metadata.ErrorMessage;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DepartmentDaoImpl extends BaseDaoWrapper<Department> implements DepartmentDao {

    private static final Logger LOGGER = Logger.getLogger(DepartmentDaoImpl.class);
    private static DepartmentDaoImpl instance = null;

    public static synchronized DepartmentDaoImpl getInstance() {
        if (instance == null) {
            instance = new DepartmentDaoImpl();
        }
        return instance;
    }

    public ArrayList<Department> getAllDepartments() {
        QueryBuilder qb = new QueryBuilder();
        qb.addTable(TablesNames.DEPARTMENT);
        return getByQuery(qb.buildSelectRequest());
    }

    @Override
    public void deleteDepartment(Integer departmentId) {
        QueryBuilder qb = new QueryBuilder();
        qb.addTable(TablesNames.EMPLOYEES);
        qb.addWhereParams(qb.setQuartletThreeParams(EmployeesMetadata.DEPARTMENT_ID, QueryBuilder.Sign.EQUAL, departmentId.toString()));
        addUpdateEntity(qb.buildDeleteRequest());
        qb = new QueryBuilder();
        qb.addTable(TablesNames.DEPARTMENT);
        qb.addWhereParams(qb.setQuartletThreeParams(DepartmentsMetadata.DEPARTMENT_ID, QueryBuilder.Sign.EQUAL, departmentId.toString()));
        addUpdateEntity(qb.buildDeleteRequest());
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {

        QueryBuilder qb = new QueryBuilder();
        qb.addTable(TablesNames.DEPARTMENT);
        qb.addWhereParams(qb.setQuartletThreeParams(DepartmentsMetadata.DEPARTMENT_ID, QueryBuilder.Sign.EQUAL, departmentId.toString()));
        return getByQuery(qb.buildSelectRequest()).get(0);
    }


    @Override
    public void insertUpdateDepartment(Department department) {
        QueryBuilder qb = new QueryBuilder();
        qb.addTable(TablesNames.DEPARTMENT);
        if (department.getId() != null) {
            qb.addWhereParams(qb.setQuartletThreeParams(DepartmentsMetadata.DEPARTMENT_ID, QueryBuilder.Sign.EQUAL, department.getId().toString()));
            addUpdateEntity(qb.buildUpdateRequest(entityToLinkedHashMap(department)));
        } else {
            qb.addColumn(DepartmentsMetadata.DEPARTMENT_NAME, DepartmentsMetadata.DESCRIPTION, DepartmentsMetadata.ADDRESS);
            addUpdateEntity(qb.buildInsertRequest(getValues(department)));
        }
    }

    @Override
    public Department getDepartmentByName(String name) {
        QueryBuilder qb = new QueryBuilder();
        qb.addTable(TablesNames.DEPARTMENT);
        qb.addWhereParams(qb.setQuartletThreeParams(DepartmentsMetadata.DEPARTMENT_NAME, QueryBuilder.Sign.EQUAL, "'" + name + "'"));
        ArrayList<Department> departments = getByQuery(qb.buildSelectRequest());
        if (!departments.isEmpty()) {

            return departments.get(0);
        } else {
            LOGGER.debug(String.format(ErrorMessage.NO_AVAIlABLE, "department", name));
            return null;
        }
    }


    @Override
    protected Department getEntity(ResultSet rs) throws SQLException {

        Department department = new Department();

        department.setId(rs.getInt(DepartmentsMetadata.DEPARTMENT_ID_WITHOUT));
        department.setDepartmentName(rs.getString(DepartmentsMetadata.DEPARTMENT_NAME_WITHOUT));
        department.setDescription(rs.getString(DepartmentsMetadata.DESCRIPTION_WITHOUT));
        department.setAddress(rs.getString(DepartmentsMetadata.ADDRESS));


        return department;
    }

    @Override
    public LinkedHashMap<String, String> entityToLinkedHashMap(Department entity) {
        LinkedHashMap<String, String> list = new LinkedHashMap<>();
        list.put(DepartmentsMetadata.DEPARTMENT_NAME, "'" + entity.getDepartmentName() + "'");
        list.put(DepartmentsMetadata.DESCRIPTION, "'" + entity.getDescription() + "'");
        list.put(DepartmentsMetadata.ADDRESS, "'" + entity.getAddress() + "'");
        return list;
    }

    @Override
    public String getValues(Department entity) {
        return "'" + entity.getDepartmentName() +
                "', '" +
                entity.getDescription() +
                "', '" +
                entity.getAddress() +
                "'";
    }
}
