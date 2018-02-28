package web.command;

import entity.Department;
import entity.Employee;
import metadata.AppConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component("/deleteEmployee")
public class DeleteEmployeeCommand implements Command {

    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //EmployeeService employeeService = (EmployeeService) request.getServletContext().getAttribute(AppConst.EMPLOYEE_SERVICE);
        Integer employeeId = Integer.parseInt((request.getParameter("employeeId")));
        Employee employee = employeeService.getEmployeeById(employeeId);
        Integer departmentId = null;
        if(employee!=null){
            departmentId = employee.getDepartmentId();
            employeeService.deleteEmployeeById(employeeId);
        }
        response.sendRedirect("/employeeList?departmentId="+departmentId);
    }
}
