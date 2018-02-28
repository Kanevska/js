package web.command;

import bean.EmployeeFormBean;
import entity.Department;
import exception.ValidationException;
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
import java.util.Map;

import static metadata.Path.EDIT_EMPLOYEE;

@Component("/addEmployee")
public class AddUpdateEmployeeCommand implements Command {

    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //EmployeeService employeeService = (EmployeeService) request.getServletContext().getAttribute(AppConst.EMPLOYEE_SERVICE);
        //DepartmentService departmentService = (DepartmentService) request.getServletContext().getAttribute(AppConst.DEPARTMENT_SERVICE);

        List<Department> departments = departmentService.getAllDepartments();
        request.setAttribute("departments", departments);

        try {
            EmployeeFormBean employeeFormBean = employeeService.setEmployeeFormBean(request);
            employeeService.updateInsertEmployee(employeeFormBean);
            response.sendRedirect("/");
        } catch (ValidationException e) {
            Map<String, List<String>> errorList = e.getErrotMap();
            request.setAttribute("errorList", errorList);
            request.getRequestDispatcher(EDIT_EMPLOYEE).forward(request, response);
        }

    }

}
