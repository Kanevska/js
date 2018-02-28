package service;

import bean.EmployeeFormBean;
import entity.Employee;
import exception.ValidationException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<Employee> getEmployeeListByDepartmentId(Integer departmentId);

    void deleteEmployeeById(Integer employeeId);

    Employee getEmployeeById(Integer employeeId);

    Employee getEmployeeByEmail(String name);

    void updateInsertEmployee(EmployeeFormBean employeeFormBean) throws ValidationException;

    EmployeeFormBean setEmployeeFormBean(HttpServletRequest request);

}
