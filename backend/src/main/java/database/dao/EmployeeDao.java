package database.dao;

import entity.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> getEmployeeListByDepartmentId(Integer departmentId);

    void deleteEmployeeById(Integer employeeId);

    Employee getEmployeeById(Integer employeeId);

    void updateInsertEmployee(Employee employee);

    Employee getEmployeeByEmail(String email);
}
