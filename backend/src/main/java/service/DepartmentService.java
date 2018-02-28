package service;

import bean.DepartmentFormBean;
import entity.Department;
import exception.ValidationException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface DepartmentService {

    List<Department> getAllDepartments();

    void deleteDepartment(Integer departmentId);

    Department getDepartmentById(Integer departmentId);

    void insertUpdateDepartment(DepartmentFormBean departmentFormBean) throws ValidationException;

    Department getDepartmentByName(String name);

    DepartmentFormBean setDepartmentFormBean(HttpServletRequest request);

}

