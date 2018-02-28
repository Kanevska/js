package database.dao.daoImpl;

import database.dao.EmployeeDao;
import database.dao.util.QueryBuilder;
import entity.Employee;
import metadata.DBMetadata.EmployeesMetadata;
import metadata.DBMetadata.TablesNames;
import metadata.ErrorMessage;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class EmployeeDaoImpl extends BaseDaoWrapper<Employee> implements EmployeeDao {

    private static final Logger LOGGER = Logger.getLogger(EmployeeDaoImpl.class);
    private static EmployeeDaoImpl instance = null;

    public static synchronized EmployeeDaoImpl getInstance(){
        if (instance == null) {
            instance = new EmployeeDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Employee> getEmployeeListByDepartmentId(Integer departmentId) {

        QueryBuilder qb = new QueryBuilder();
        qb.addTable(TablesNames.EMPLOYEES);
        qb.addWhereParams(qb.setQuartletTwoParams(EmployeesMetadata.DEPARTMENT_ID, QueryBuilder.Sign.EQUAL));
        return getByQueryAndParams(qb.buildSelectRequest(), departmentId.toString());

    }

    @Override
    public void deleteEmployeeById(Integer employeeId) {
        QueryBuilder qb = new QueryBuilder();
        qb.addTable(TablesNames.EMPLOYEES);
        qb.addWhereParams(qb.setQuartletThreeParams(EmployeesMetadata.EMPLOYEE_ID, QueryBuilder.Sign.EQUAL, employeeId.toString()));
        addUpdateEntity(qb.buildDeleteRequest());
    }

    @Override
    public Employee getEmployeeById(Integer employeeId) {
        QueryBuilder qb = new QueryBuilder();
        qb.addTable(TablesNames.EMPLOYEES);
        qb.addWhereParams(qb.setQuartletThreeParams(EmployeesMetadata.EMPLOYEE_ID, QueryBuilder.Sign.EQUAL, employeeId.toString()));
        return getByQuery(qb.buildSelectRequest()).get(0);
    }

    @Override
    public void updateInsertEmployee(Employee employee) {
        QueryBuilder qb = new QueryBuilder();
        qb.addTable(TablesNames.EMPLOYEES);
        if(employee.getId()!= null){
            qb.addWhereParams(qb.setQuartletThreeParams(EmployeesMetadata.EMPLOYEE_ID, QueryBuilder.Sign.EQUAL, employee.getId().toString()));
            addUpdateEntity(qb.buildUpdateRequest(entityToLinkedHashMap(employee)));

        }else{
            qb.addColumn(EmployeesMetadata.FULL_NAME, EmployeesMetadata.BIRTHDAY, EmployeesMetadata.EMAIL, EmployeesMetadata.SALARY,
                    EmployeesMetadata.PHONE_NUMBER, EmployeesMetadata.DEPARTMENT_ID);
            addUpdateEntity(qb.buildInsertRequest(getValues(employee)));
        }
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        QueryBuilder qb = new QueryBuilder();
        qb.addTable(TablesNames.EMPLOYEES);
        qb.addWhereParams(qb.setQuartletThreeParams(EmployeesMetadata.EMAIL, QueryBuilder.Sign.EQUAL, "'" + email + "'"));
        ArrayList<Employee> employees = getByQuery(qb.buildSelectRequest());
        if (!employees.isEmpty()) {
            return employees.get(0);
        } else {
            LOGGER.debug(String.format(ErrorMessage.NO_AVAIlABLE, "employee", email));
            return null;
        }

    }

    @Override
    protected Employee getEntity(ResultSet rs) throws SQLException {
        Employee employee = new Employee();

        employee.setId(rs.getInt(EmployeesMetadata.EMPLOYEE_ID_WITHOUT));
        employee.setFullName(rs.getString(EmployeesMetadata.FULL_NAME_WITHOUT));
        employee.setSalary(rs.getFloat(EmployeesMetadata.SALARY_WITHOUT));
        employee.setBirthday(rs.getDate(EmployeesMetadata.BIRTHDAY_WITHOUT));
        employee.setPhoneNumber(rs.getString(EmployeesMetadata.PHONE_NUMBER_WITHOUT));
        employee.setEmail(rs.getString(EmployeesMetadata.EMAIL_WITHOUT));
        employee.setDepartmentId(rs.getInt(EmployeesMetadata.DEPARTMENT_ID_WITHOUT));

        return employee;
    }

    @Override
    public LinkedHashMap<String, String> entityToLinkedHashMap(Employee entity) {
        LinkedHashMap<String, String> list = new LinkedHashMap<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        list.put(EmployeesMetadata.FULL_NAME, "'" + entity.getFullName() + "'");
        list.put(EmployeesMetadata.PHONE_NUMBER, "'" + entity.getPhoneNumber() + "'");
        list.put(EmployeesMetadata.SALARY, entity.getSalary() + "");
        list.put(EmployeesMetadata.EMAIL, "'" + entity.getEmail() + "'");
        list.put(EmployeesMetadata.DEPARTMENT_ID, "'" + entity.getDepartmentId() + "'");
        list.put(EmployeesMetadata.BIRTHDAY, "'" + dateFormat.format(entity.getBirthday()) + "'");
        return list;
    }

    @Override
    public String getValues(Employee entity) {
        StringBuffer sb = new StringBuffer();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        sb.append("'")
                .append(entity.getFullName()).append("', '")
                .append(dateFormat.format(entity.getBirthday())).append("', '")
                .append(entity.getEmail()).append("', ")
                .append(entity.getSalary()).append(", '")
                .append(entity.getPhoneNumber()).append("', ")
                .append(entity.getDepartmentId());
        return sb.toString();
    }

}
