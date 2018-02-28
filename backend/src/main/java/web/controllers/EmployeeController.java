package web.controllers;

import bean.EmployeeFormBean;
import entity.Department;
import entity.Employee;
import exception.ValidationException;
import metadata.Path;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.DepartmentService;
import service.EmployeeService;
import util.DateFormater;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private static final Logger LOGGER = Logger.getLogger(EmployeeController.class);

    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;
    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;


    @GetMapping(value = "getEmployeeForm")
    public String getEmployeeForm(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return Path.EDIT_EMPLOYEE;
    }

    @GetMapping(value = "editEmployee")
    public String editEmployee(Integer id, Model model) {

        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);

        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return Path.EDIT_EMPLOYEE;
    }


    @GetMapping(value = "deleteEmployee")
    public String deleteEmployee(Integer id, Model model) {

        Employee employee = employeeService.getEmployeeById(id);
        Integer departmentId = null;
        if (employee != null) {
            departmentId = employee.getDepartmentId();
            try {
                employeeService.deleteEmployeeById(id);
            } catch (Exception e) {
                LOGGER.error("Employee delete error",e);
            }
        }
        employeeList(departmentId, model);
        return ("employeeList?id=" + departmentId);
    }

    @RequestMapping(value = "employeeList")
    public String employeeList(Integer id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        List<Employee> employees = employeeService.getEmployeeListByDepartmentId(id);
        model.addAttribute("employees", employees);
        model.addAttribute("dep", department);
        return Path.EMPLOYEE_LIST;
    }

    @PostMapping(value = "addEmployee")
    public String addEmployee(Model model, EmployeeFormBean employeeFormBean, BindingResult bindingResult) {

        List<Department> departments = departmentService.getAllDepartments();
        if (bindingResult.hasErrors()) {
            Date date = new Date(DateFormater.dateFormater("1800-01-01").getTime());
            employeeFormBean.setBirthday(date);
        }
        model.addAttribute("departments", departments);
        try {
            employeeService.updateInsertEmployee(employeeFormBean);
            Integer departmentId = employeeFormBean.getDepartmentId();
            employeeList(departmentId, model);
            return "employeeList?id=" + departmentId;
        } catch (ValidationException e) {
            Map<String, List<String>> errorList = e.getErrotMap();
            model.addAttribute("errorList", errorList);
            return Path.EDIT_EMPLOYEE;
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
