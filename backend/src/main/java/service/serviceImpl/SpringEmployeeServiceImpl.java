package service.serviceImpl;

import bean.EmployeeFormBean;
import database.dao.EmployeeDao;
import entity.Employee;
import exception.ValidationException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.EmployeeService;
import util.DateFormater;
import util.ValidationUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

import static metadata.LoggerConst.SUCCESSFULLY_UPDATE;

@Service("employeeService")
@Transactional
public class SpringEmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    private static final Logger LOGGER = Logger.getLogger(SpringEmployeeServiceImpl.class);

    @Transactional(readOnly = true)
    @Override
    public List<Employee> getEmployeeListByDepartmentId(Integer departmentId) {
        return employeeDao.getEmployeeListByDepartmentId(departmentId);
    }

    @Override
    public void deleteEmployeeById(Integer employeeId) {
        employeeDao.deleteEmployeeById(employeeId);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployeeById(Integer employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployeeByEmail(String name) {
        return employeeDao.getEmployeeByEmail(name);
    }

    @Override
    public void updateInsertEmployee(EmployeeFormBean employeeFormBean) throws ValidationException {

        ValidationUtil validationUtil = new ValidationUtil();
        validationUtil.validate(employeeFormBean);
        employeeDao.updateInsertEmployee(getEmployee(employeeFormBean));
        LOGGER.debug(String.format(SUCCESSFULLY_UPDATE, "employee", ""));

    }


    public EmployeeFormBean setEmployeeFormBean(HttpServletRequest request) {

        String fullName = request.getParameter("fullName");
        EmployeeFormBean employeeFormBean = new EmployeeFormBean();
        String email = request.getParameter("email");
        String number = request.getParameter("phoneNumber");
        String salary = (request.getParameter("salary"));
        Integer departmentId = Integer.parseInt(request.getParameter("departmentId"));
        String birthday = request.getParameter("birthday");
        try {
            Integer employeeId = Integer.parseInt(request.getParameter("employeeId"));
            employeeFormBean.setId(employeeId);
        } catch (Exception e) {
            LOGGER.debug("Adding employee");
        }
        employeeFormBean.setFullName(fullName);
        Date birthdayTime = new Date(DateFormater.dateFormater(birthday).getTime());
        employeeFormBean.setBirthday(birthdayTime);
        employeeFormBean.setEmail(email);
        employeeFormBean.setPhoneNumber(number);
        employeeFormBean.setSalary(salary);
        employeeFormBean.setDepartmentId(departmentId);

        return employeeFormBean;
    }

    private Employee getEmployee(EmployeeFormBean employeeFormBean) {
        Employee employee = new Employee();
        employee.setId(employeeFormBean.getId());
        employee.setEmail(employeeFormBean.getEmail());
        employee.setPhoneNumber(employeeFormBean.getPhoneNumber());
        employee.setFullName(employeeFormBean.getFullName());
        employee.setBirthday(employeeFormBean.getBirthday());
        employee.setSalary(Float.parseFloat(employeeFormBean.getSalary()));
        employee.setDepartmentId(employeeFormBean.getDepartmentId());
        return employee;
    }
}
