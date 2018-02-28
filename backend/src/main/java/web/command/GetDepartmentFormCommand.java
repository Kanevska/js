package web.command;

import metadata.Path;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("/getDepartmentForm")
public class GetDepartmentFormCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Path.EDIT_DEPARTMENT).forward(request, response);
    }
}
/*
public class GetDepartmentFormCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Path.EDIT_DEPARTMENT).forward(request, response);
    }
}
*/