package web.command;

import entity.Department;
import metadata.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("/editDepartment")
public class EditDepartmentCommand implements Command {

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //DepartmentService departmentService = (DepartmentService) request.getServletContext().getAttribute(AppConst.DEPARTMENT_SERVICE);
        Department department = departmentService.getDepartmentById(Integer.parseInt(request.getParameter("departmentId")));
        request.setAttribute("department", department);
        request.getRequestDispatcher(Path.EDIT_DEPARTMENT).forward(request, response);
    }
}
