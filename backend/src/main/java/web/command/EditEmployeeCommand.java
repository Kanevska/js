package web.command;

import entity.Department;
import entity.Employee;
import metadata.AppConst;
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
@Component("/editEmployee")
public class EditEmployeeCommand implements Command {

    @Autowired
    @Qualifier("employeeService")
   private EmployeeService employeeService;

    @Autowired
    @Qualifier("departmentService")
   private DepartmentService departmentService;



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // EmployeeService employeeService = (EmployeeService) request.getServletContext().getAttribute(AppConst.EMPLOYEE_SERVICE);
        //DepartmentService departmentService = (DepartmentService) request.getServletContext().getAttribute(AppConst.DEPARTMENT_SERVICE);

        List<Department> departments = departmentService.getAllDepartments();
        request.setAttribute("departments", departments);

        Employee employee = employeeService.getEmployeeById(Integer.parseInt(request.getParameter("employeeId")));
        request.setAttribute("employee", employee);
        request.getRequestDispatcher(Path.EDIT_EMPLOYEE).forward(request, response);
    }
}
