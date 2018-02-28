package service.serviceImpl;

import bean.EmployeeFormBean;
import database.dao.EmployeeDao;
import entity.Employee;
import exception.ValidationException;
import metadata.AppConst;
import org.apache.log4j.Logger;
import service.EmployeeService;
import util.ValidationUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import static metadata.LoggerConst.INVALID_DATE_ENTER;
import static metadata.LoggerConst.SUCCESSFULLY_UPDATE;

public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);
    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl(final ServletContext context) {

        this.employeeDao = (EmployeeDao) context.getAttribute(AppConst.EMPLOYEE_DAO);

    }

    @Override
    public List<Employee> getEmployeeListByDepartmentId(Integer departmentId) {
        return employeeDao.getEmployeeListByDepartmentId(departmentId);
    }

    @Override
    public void deleteEmployeeById(Integer employeeId) {
        employeeDao.deleteEmployeeById(employeeId);
    }

    @Override
    public Employee getEmployeeById(Integer employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public Employee getEmployeeByEmail(String name) {
        return null;
    }


    public void updateInsertEmployee(EmployeeFormBean employeeFormBean) throws ValidationException {

        ValidationUtil validationUtil = new ValidationUtil();
        validationUtil.validate(employeeFormBean);
        employeeDao.updateInsertEmployee(getEmployee(employeeFormBean));
        LOGGER.debug(String.format(SUCCESSFULLY_UPDATE, "employee", ""));

    }





    public EmployeeFormBean setEmployeeFormBean(HttpServletRequest request) {
        EmployeeFormBean employeeFormBean = new EmployeeFormBean();

        try {
            employeeFormBean.setId(Integer.parseInt(request.getParameter("employeeId")));
        } catch (Exception e) {
            LOGGER.debug("Adding employee");
        }

        employeeFormBean.setFullName(request.getParameter("fullName"));

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        java.util.Date date = new java.util.Date();
        try {
            date = format.parse(request.getParameter("birthday"));

        } catch (ParseException e) {
            try {
                date = format.parse("1800-01-01");
            } catch (ParseException e1) {
                LOGGER.error(INVALID_DATE_ENTER);
            }
        }

        employeeFormBean.setBirthday(new Date(date.getTime()));
        employeeFormBean.setEmail(request.getParameter("email"));
        employeeFormBean.setPhoneNumber(request.getParameter("phoneNumber"));
        employeeFormBean.setSalary((request.getParameter("salary")));

        employeeFormBean.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));

        return employeeFormBean;
    }

    public Employee getEmployee(EmployeeFormBean employeeFormBean) {
        Employee employee = new Employee();
        employee.setId(employeeFormBean.getId());
        employee.setFullName(employeeFormBean.getFullName());
        employee.setEmail(employeeFormBean.getEmail());
        employee.setPhoneNumber(employeeFormBean.getPhoneNumber());
        employee.setBirthday(employeeFormBean.getBirthday());
        employee.setSalary(Float.parseFloat(employeeFormBean.getSalary()));
        employee.setDepartmentId(employeeFormBean.getDepartmentId());
        return employee;
    }
}
