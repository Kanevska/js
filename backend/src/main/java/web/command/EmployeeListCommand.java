package web.command;

import entity.Department;
import entity.Employee;
import metadata.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import service.DepartmentService;
import service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component("/employeeList")
public class EmployeeListCommand implements Command {

    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;
    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer departmentId = Integer.parseInt(request.getParameter("departmentId"));
        Department department = departmentService.getDepartmentById(departmentId);
        List<Employee> employees = employeeService.getEmployeeListByDepartmentId(departmentId);
        request.setAttribute("employees", employees);
        request.setAttribute("dep", department);
        request.getRequestDispatcher(Path.EMPLOYEE_LIST).forward(request, response);

    }
}
