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
        String json = converter.departmentListToJSON(departments);
        return json;
    }

    @GetMapping(value = "getDepartmentForm")
    public String getDepartmentForm() {
        return Path.EDIT_DEPARTMENT;
    }



    @GetMapping(value = "/editDepartment/{id}",produces = "application/json; charset=utf-8")
    public @ResponseBody String editDepartment(@PathVariable("id") Integer id) {
        Department department = departmentService.getDepartmentById(id);
        String json = converter.departmentToJSON(department);
        return json;
    }

    @PostMapping(value = "addDepartment")
    public String addDepartment(Model model, DepartmentFormBean departmentFormBean) {
        try {
            departmentService.insertUpdateDepartment(departmentFormBean);
            return Path.MAIN_PAGE;
        } catch (ValidationException e) {
            Map<String, List<String>> errorList = e.getErrotMap();
            model.addAttribute("errorList", errorList);
            return EDIT_DEPARTMENT;
        }
    }


    @GetMapping(value = "/deleteDepartment/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable("id") Integer id) {
        try {
            departmentService.deleteDepartment(id);
        } catch (Exception e) {
            LOGGER.error("Department delete error",e);
        }
    }
}
