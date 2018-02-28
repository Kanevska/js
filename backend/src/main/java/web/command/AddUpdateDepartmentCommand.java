package web.command;

import bean.DepartmentFormBean;
import exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static metadata.Path.EDIT_DEPARTMENT;

@Component("/addDepartment")
public class AddUpdateDepartmentCommand implements Command {

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //DepartmentService departmentService = (DepartmentService) request.getServletContext().getAttribute(AppConst.DEPARTMENT_SERVICE);
        try {
            DepartmentFormBean departmentFormBean = departmentService.setDepartmentFormBean(request);
            departmentService.insertUpdateDepartment(departmentFormBean);
            response.sendRedirect("/");
        } catch (ValidationException e) {
            Map<String, List<String>> errorList = e.getErrotMap();
            request.setAttribute("errorList", errorList);
            request.getRequestDispatcher(EDIT_DEPARTMENT).forward(request, response);
        }

    }
}
