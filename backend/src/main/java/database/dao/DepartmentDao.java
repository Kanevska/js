package database.dao;

import entity.Department;
import java.util.ArrayList;
import java.util.List;

public interface DepartmentDao {

   List<Department> getAllDepartments();

    void deleteDepartment(Integer departmentId);

    Department getDepartmentById(Integer departmentId);

    void insertUpdateDepartment(Department department);

    Department getDepartmentByName(String name);
}
