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
import java.util.List;


@Component("home")
public class NoCommand implements Command {

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("From execute Department");


        List<Department> departments = this.departmentService.getAllDepartments();
        request.setAttribute("departments", departments);
        request.getRequestDispatcher(Path.MAIN_PAGE).forward(request, response);
    }
}

/*public class NoCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DepartmentService departmentService = (DepartmentService) request.getServletContext().getAttribute(AppConst.DEPARTMENT_SERVICE);
        List<Department> departments = departmentService.getAllDepartments();
        request.setAttribute("departments", departments);

        request.getRequestDispatcher(Path.MAIN_PAGE).forward(request, response);

    }
}
*/