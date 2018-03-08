package web.controllers;

import bean.EmployeeFormBean;
import entity.Department;
import entity.Employee;
import exception.ValidationException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.DepartmentService;
import service.EmployeeService;
import util.Converter;
import util.DateFormater;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private Converter converter = new Converter();
    private static final Logger LOGGER = Logger.getLogger(EmployeeController.class);

    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;
    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;


    @GetMapping(value = "/editEmployee/{id}", produces = "application/json; charset=utf-8")
    public @ResponseBody
    String editDepartment(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        List<Department> departments = this.departmentService.getAllDepartments();
        String json = converter.employeeToJSON(employee);
        json = json.substring(0, json.length() - 1) + "," + converter.departmentListToJSON(departments).substring(1);
        return json;
    }

    @DeleteMapping(value = "/deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable("id") Integer id, HttpServletResponse response) {

        try {
            employeeService.deleteEmployeeById(id);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (Exception e) {
            LOGGER.error("Employee delete error", e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);//400
        }


    }


    @GetMapping(value = "/employeeList/{id}", produces = "application/json; charset=utf-8")
    public @ResponseBody
    String showHomePage(@PathVariable("id") Integer id) {
        List<Employee> employees = employeeService.getEmployeeListByDepartmentId(id);
        return converter.employeeListToJSON(employees);
    }

    @PostMapping(value = "/addEmployee")
    public void addEmployee(EmployeeFormBean employeeFormBean, BindingResult bindingResult, HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            Date date = new Date(DateFormater.dateFormater("1800-01-01").getTime());
            employeeFormBean.setBirthday(date);
        }
        try {
            employeeService.updateInsertEmployee(employeeFormBean);
            response.setStatus(HttpServletResponse.SC_OK);
            System.out.println("success");
        } catch (ValidationException e) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }

    @InitBinder
    public void allowEmptyDateBinding(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
    }
}
