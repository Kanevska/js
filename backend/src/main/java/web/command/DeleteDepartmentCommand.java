package web.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("/deleteDepartment")
public class DeleteDepartmentCommand implements Command {

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //DepartmentService departmentService = (DepartmentService) request.getServletContext().getAttribute(AppConst.DEPARTMENT_SERVICE);
       Integer departmentId = Integer.parseInt(request.getParameter("departmentId"));
        departmentService.deleteDepartment(departmentId);
        response.sendRedirect("/");

    }
}
