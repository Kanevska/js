package web.controllers;

import bean.DepartmentFormBean;
import entity.Department;
import exception.ValidationException;
import metadata.Path;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.DepartmentService;
import util.Converter;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static metadata.Path.EDIT_DEPARTMENT;


@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private Converter converter = new Converter();
    private static final Logger LOGGER = Logger.getLogger(DepartmentController.class);

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;


    @GetMapping(value = "/departmentList",produces = "application/json; charset=utf-8")
    public @ResponseBody String showHomePage() {
        List<Department> departments = this.departmentService.getAllDepartments();
        return converter.departmentListToJSON(departments);
    }




    @GetMapping(value = "/editDepartment/{id}",produces = "application/json; charset=utf-8")
    public @ResponseBody String editDepartment(@PathVariable("id") Integer id) {
        Department department = departmentService.getDepartmentById(id);
        return converter.departmentToJSON(department);
    }

    @PostMapping(value = "/addDepartment")
    public void addDepartment(HttpServletResponse response, DepartmentFormBean departmentFormBean) {
        try {
            departmentService.insertUpdateDepartment(departmentFormBean);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (ValidationException e) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);//409
        }
    }


    @DeleteMapping(value = "/deleteDepartment/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable("id") Integer id,HttpServletResponse response) {
        try {
            departmentService.deleteDepartment(id);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception e) {
            LOGGER.error("Department delete error",e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);//400
        }
    }
}
